package com.yoflying.drivingschool.admin.addleave;

import com.google.gson.Gson;
import com.yoflying.drivingschool.base.BasePresenter;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.entity.AddLeaveInfo;
import com.yoflying.drivingschool.entity.HttpsResult;
import com.yoflying.drivingschool.entity.Person;
import com.yoflying.drivingschool.retrofit.ApiCallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * 请假管理控制器
 * Created by yaojiulong on 2017/1/16.
 */

public class LeavePresenter extends BasePresenter<ILeaveView> {
    private ILeaveView mLeaveView;

    public LeavePresenter(ILeaveView mLeaveView) {
        this.mLeaveView = mLeaveView;
        attachView(mLeaveView);
    }

    /**
     * 获取教练列表
     */
    public void getTeachersInfo(){
        ApiCallBack<HttpsResult<List<Person>>> subscriber=new ApiCallBack<HttpsResult<List<Person>>>() {
            @Override
            public void onSuccess(HttpsResult<List<Person>> model) {
                mLeaveView.getTeachersInfo(model.getData());
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onFinish() {

            }
        };
        Map<String,String> map=new HashMap<>();
        map.put(Config.PARAMS_PAGE_NUM,"1");
        addSubscription(mApiStore.getTeacherList(map),subscriber);
    }

    public void setTeacherName(String name){
        mLeaveView.setTeacherName(name);
    }

    /**
     * 添加请假
     */
    public void addLeave(){
        mLeaveView.showDialog();
        ApiCallBack<HttpsResult> subscriber=new ApiCallBack<HttpsResult>() {
            @Override
            public void onSuccess(HttpsResult model) {
                if (model.getStatus()==0){
                    mLeaveView.cancelDialog();
                    mLeaveView.toastMeassager(model.getMessage());
                }
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onFinish() {

            }
        };
        AddLeaveInfo info=new AddLeaveInfo();
        info.setCoachId(mLeaveView.getTeacherId());
        info.setLeaveDate2(mLeaveView.getStartTime());
        info.setLeaveDate3(mLeaveView.getEndTime());
        info.setStatus(mLeaveView.getStatus());
        info.setCoachName(mLeaveView.getTeacherName());
        Gson gson=new Gson();
        String route= gson.toJson(info);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
        addSubscription(mApiStore.addLeave(body),subscriber);
    }
}
