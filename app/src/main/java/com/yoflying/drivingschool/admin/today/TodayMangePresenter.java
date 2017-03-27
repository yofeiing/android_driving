package com.yoflying.drivingschool.admin.today;

import com.yoflying.drivingschool.app.DriverApplication;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BasePresenter;
import com.yoflying.drivingschool.entity.HttpsResult;
import com.yoflying.drivingschool.entity.TodayCourse;
import com.yoflying.drivingschool.retrofit.ApiCallBack;

import java.util.List;

/**管理员今日课程控制器{@link TodayFragment}
 * Created by yaojiulong on 2017/1/18.
 */

public class TodayMangePresenter extends BasePresenter<ITodayView> {
    private ITodayView mTodayView;

    public TodayMangePresenter(ITodayView mTodayView) {
        this.mTodayView = mTodayView;
        attachView(mTodayView);
    }

    public void getTodayCourse(){
        ApiCallBack<HttpsResult<List<TodayCourse>>> subscriber=new ApiCallBack<HttpsResult<List<TodayCourse>>>() {
            @Override
            public void onSuccess(HttpsResult<List<TodayCourse>> model) {


                mTodayView.showTodayCourse(model.getData());
                if (model.getData().size()==0){
                    mTodayView.closeRefresh();

                    mTodayView.toastMeassager(DriverApplication.getContextObject().getResources().getString(R.string.admin_today_no_course));
                }
            }

            @Override
            public void onFailure(String msg) {
                mTodayView.toastMeassager(msg);
            }

            @Override
            public void onFinish() {

            }
        };
        addSubscription(mApiStore.getTodayCourse(),subscriber);
    }




}
