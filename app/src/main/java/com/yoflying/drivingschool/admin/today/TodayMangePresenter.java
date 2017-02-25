package com.yoflying.drivingschool.admin.today;

import android.util.Log;

import com.google.gson.Gson;
import com.yoflying.drivingschool.DriverApplication;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BasePresenter;
import com.yoflying.drivingschool.entity.HttpsResult;
import com.yoflying.drivingschool.entity.TodayCourse;
import com.yoflying.drivingschool.retrofit.ApiCallBack;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
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
