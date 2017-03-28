package com.yoflying.drivingschool.student.schedule;

import com.yoflying.drivingschool.base.BasePresenter;
import com.yoflying.drivingschool.entity.HttpsResult;
import com.yoflying.drivingschool.retrofit.ApiCallBack;
import com.yoflying.drivingschool.student.bean.FutureCourse;

import java.util.List;

/**日程 控制器{@link com.yoflying.drivingschool.student.ui.ScheduleFragment}
 * Created by yaojiulong on 2017/3/28.
 */

public class SchedulePresenter extends BasePresenter<IScheduleView> {
    private IScheduleView mView;

    public SchedulePresenter(IScheduleView mView) {
        this.mView = mView;
        attachView(mView);

    }

    public void getFutureCourse(){
        ApiCallBack<HttpsResult<List<FutureCourse>>> subscriber=new ApiCallBack<HttpsResult<List<FutureCourse>>>() {
            @Override
            public void onSuccess(HttpsResult<List<FutureCourse>> model) {

            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onFinish() {

            }
        };
        addSubscription(mApiStore.getFutureCourse(),subscriber);
    }
}
