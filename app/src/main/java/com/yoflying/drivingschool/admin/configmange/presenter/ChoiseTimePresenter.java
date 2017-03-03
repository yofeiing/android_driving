package com.yoflying.drivingschool.admin.configmange.presenter;

import com.yoflying.drivingschool.base.BasePresenter;
import com.yoflying.drivingschool.admin.configmange.IView.IChoiseTimeView;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.entity.HttpsResult;
import com.yoflying.drivingschool.entity.Person;
import com.yoflying.drivingschool.retrofit.ApiCallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**一天选择预约配置控制器
 * Created by yaojiulong on 2017/2/22.
 */

public class ChoiseTimePresenter extends BasePresenter<IChoiseTimeView> {
    private IChoiseTimeView mChoiseTimeView;

    public ChoiseTimePresenter(IChoiseTimeView mChoiseTimeView) {
        this.mChoiseTimeView = mChoiseTimeView;
        attachView(mChoiseTimeView);
    }



    public void sendCourseConfig(){
        ApiCallBack<HttpsResult> subscriber=new ApiCallBack<HttpsResult>() {
            @Override
            public void onSuccess(HttpsResult model) {

            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onFinish() {

            }
        };
    }

    /**
     * 获取教练列表
     */
    public void getTeachersInfo(){
        ApiCallBack<HttpsResult<List<Person>>> subscriber=new ApiCallBack<HttpsResult<List<Person>>>() {
            @Override
            public void onSuccess(HttpsResult<List<Person>> model) {
                mChoiseTimeView  .getTeachersInfo(model.getData());
            }

            @Override
            public void onFailure(String msg) {
                mChoiseTimeView.stopRefresh();
            }

            @Override
            public void onFinish() {

            }
        };
        Map<String,String> map=new HashMap<>();
        map.put(Config.PARAMS_PAGE_NUM,"1");
        addSubscription(mApiStore.getTeacherList(map),subscriber);
    }
}
