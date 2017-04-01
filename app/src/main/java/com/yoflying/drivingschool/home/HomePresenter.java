package com.yoflying.drivingschool.home;

import android.content.Context;
import android.util.Log;

import com.yoflying.drivingschool.app.DriverApplication;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BasePresenter;
import com.yoflying.drivingschool.config.LogDef;
import com.yoflying.drivingschool.entity.Admin;
import com.yoflying.drivingschool.entity.HttpsResult;
import com.yoflying.drivingschool.entity.Person;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.retrofit.ApiCallBack;
import com.yoflying.drivingschool.utils.UtilSharedPreferences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

/**首页控制者
 * {@link com.yoflying.drivingschool.ui.HomeActivity}
 * Created by yaojiulong on 2016/12/28.
 */

public class HomePresenter extends BasePresenter<IHomeView>{
    private static final Logger logger = LoggerFactory.getLogger(HomePresenter.class);
    private IHomeView mHomeView;
    private String mToken;
    private Context mContext;
    private DriverApplication mApplication;
    public HomePresenter(IHomeView adminView) {
        this.mHomeView=adminView;
        attachView(mHomeView);
        mContext=DriverApplication.getContextObject();
        mApplication= (DriverApplication) mContext.getApplicationContext();
        mToken= UtilSharedPreferences.getStringData(DriverApplication.getContextObject(), Config.KEY_TOKEN);
        switchFragment();
        showSomeInfo();

    }

    private void switchFragment() {
        String userType=UtilSharedPreferences.getStringData(DriverApplication.getContextObject(),Config.KEY_USER_TYPE);
        if (userType.equals(DriverApplication.getContextObject().getResources().getString(R.string.user_admin))){
            mHomeView.showAdminFragment();
            getAdminInfoFromServer();
        }else if (userType.equals(DriverApplication.getContextObject().getResources().getString(R.string.user_teacher))){
            mHomeView.showTeacherFragment();
            getUserInfoFromServer(mToken);
        }else if (userType.equals(DriverApplication.getContextObject().getResources().getString(R.string.user_student))){
            mHomeView.showStudentFragment();
            getUserInfoFromServer(mToken);
        }
    }

    private void showSomeInfo(){
      /*  mHomeView.showUserName(UtilSharedPreferences.getStringData
                (DriverApplication.getContextObject(), Config.KEY_USERNAME));
        mHomeView.showUserType(UtilSharedPreferences.getStringData
                (DriverApplication.getContextObject(),Config.KEY_USER_TYPE));*/
    }

    /**
     * 获取学员或者教练的信息
     * @param token
     */
    private void getUserInfoFromServer(String token){
        ApiCallBack<HttpsResult<Person>> subscriber=new ApiCallBack<HttpsResult<Person>>() {
            @Override
            public void onSuccess(HttpsResult<Person> model) {
                mHomeView.showUserName(model.getData().getName());

                if (model.getData().getDiscern()==1){
                    mHomeView.showUserType(mContext.getResources().getString(R.string.user_teacher));
                }else if (model.getData().getDiscern()==2){
                    mHomeView.showUserType(mContext.getResources().getString(R.string.user_student));
                }
                mApplication.setPerson(model.getData());

            }

            @Override
            public void onFailure(String msg) {
                logger.info(LogDef.LOG_HTTP, "HomePersenter 获取信息失败");
            }

            @Override
            public void onFinish() {

            }
        };


        addSubscription(mApiStore.getUserInfo(),subscriber);
    }

    /**
     * 获取管理员信息
     * @param
     */
    private void getAdminInfoFromServer(){
        ApiCallBack<HttpsResult<Admin>> subscriber=new ApiCallBack<HttpsResult<Admin>>() {
            @Override
            public void onSuccess(HttpsResult<Admin> model) {
                mHomeView.showUserName(model.getData().getUsername());
                mHomeView.showUserType(mContext.getResources().getString(R.string.user_admin));
                logger.info(LogDef.LOG_HTTP, "获取管理员信息成功");
            }

            @Override
            public void onFailure(String msg) {

                if (msg.equals("401")){
                    mvpView.showSnackView("用户账号过期，请重新登录");
                    delayToLogin();
                }else {

                }
            }

            @Override
            public void onFinish() {

            }
        };
        addSubscription(mApiStore.getAdminInfo(),subscriber);

    }

    private void delayToLogin(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                /**
                 *要执行的操作
                 */
                mvpView.toLogin();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 3000);//3秒后执行TimeTask的run方法
    }

}
