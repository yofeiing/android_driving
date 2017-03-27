package com.yoflying.drivingschool.admin.configmange.presenter;

import android.util.Log;

import com.google.gson.Gson;

import com.yoflying.drivingschool.admin.configmange.Iview.IMaualConfigView;
import com.yoflying.drivingschool.base.BasePresenter;
import com.yoflying.drivingschool.entity.CourseConfig;
import com.yoflying.drivingschool.entity.HttpsResult;
import com.yoflying.drivingschool.retrofit.ApiCallBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * 手动预约配置控制器
 * Created by yaojiulong on 2017/2/20.
 */

public class MaualConfigPresenter extends BasePresenter<IMaualConfigView> {
    private IMaualConfigView mManualConfigView;

    public MaualConfigPresenter(IMaualConfigView mManualConfigView) {
        this.mManualConfigView = mManualConfigView;
        attachView(mManualConfigView);
    }

    public void submitMaualConfigData(List<Map<String,List<CourseConfig>>> data){
        mManualConfigView.showDialog();
        List<CourseConfig> allDataList=new ArrayList<>();
        for (Map<String, List<CourseConfig>> map : data) {
            for (String key:map.keySet()){
                allDataList.addAll(map.get(key));
            }
        }
        Log.e("dandy","list  "+allDataList.toString());

        ApiCallBack<HttpsResult> subscriber=new ApiCallBack<HttpsResult>() {
            @Override
            public void onSuccess(HttpsResult model) {
                mManualConfigView.cancelDialog();
                mManualConfigView.toastMeassager(model.getMessage());

            }

            @Override
            public void onFailure(String msg) {
                mManualConfigView.cancelDialog();
                mManualConfigView.toastMeassager(msg);
            }

            @Override
            public void onFinish() {

            }
        };
        Gson gson=new Gson();
        String route=gson.toJson(allDataList);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
        addSubscription(mApiStore.submitCourseConfig(body),subscriber);
    }
}
