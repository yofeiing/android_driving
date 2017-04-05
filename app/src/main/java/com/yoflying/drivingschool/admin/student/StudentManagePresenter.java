package com.yoflying.drivingschool.admin.student;

import android.util.Log;

import com.google.gson.Gson;
import com.yoflying.drivingschool.base.BasePresenter;
import com.yoflying.drivingschool.entity.BindInfo;
import com.yoflying.drivingschool.entity.HttpsResult;
import com.yoflying.drivingschool.entity.Person;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.retrofit.ApiCallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * 学员管理控制器
 * Created by yaojiulong on 2017/1/14.
 */

public class StudentManagePresenter extends BasePresenter<IStudentManageView> {
    private IStudentManageView mManageView;
    public static final int TYPE_BIND = 1;
    public static final int TYPE_UNBIND = 2;

    public StudentManagePresenter(IStudentManageView mManageView) {
        this.mManageView = mManageView;
        attachView(mManageView);
        //getTeachersInfo();
    }

    /**
     * 获取学员列表
     *
     * @param pageIndex
     */
    public void getStudentsInfo(String pageIndex) {
        mManageView.setRefresh();
        ApiCallBack<HttpsResult<List<Person>>> subscriber = new ApiCallBack<HttpsResult<List<Person>>>() {
            @Override
            public void onSuccess(HttpsResult<List<Person>> model) {
                mManageView.stopRefresh();
                mManageView.showStudentsInfo(model.getData());

            }

            @Override
            public void onFailure(String msg) {
                mManageView.stopRefresh();
                Log.e("dandy", "获取学生列表失败 " + msg);
            }

            @Override
            public void onFinish() {

            }
        };
        Map<String, String> map = new HashMap<>();
        map.put(Config.PARAMS_PAGE_NUM, pageIndex);
        addSubscription(mApiStore.getStudentList(map), subscriber);
    }

    /**
     * 获取教练列表
     */
    public void getTeachersInfo() {
        ApiCallBack<HttpsResult<List<Person>>> subscriber = new ApiCallBack<HttpsResult<List<Person>>>() {
            @Override
            public void onSuccess(HttpsResult<List<Person>> model) {
                mManageView.getTeachersInfo(model.getData());
            }

            @Override
            public void onFailure(String msg) {
                mManageView.stopRefresh();
            }

            @Override
            public void onFinish() {

            }
        };
        Map<String, String> map = new HashMap<>();
        map.put(Config.PARAMS_PAGE_NUM, "1");
        addSubscription(mApiStore.getTeacherList(map), subscriber);
    }

    /**
     * 有关绑定，可以绑定和取消绑定，取决于info的coachId
     *
     * @param info 绑定信息
     */
    public void aboutBind(BindInfo info) {
        mManageView.showDialog();
        ApiCallBack<HttpsResult> subscriber = new ApiCallBack<HttpsResult>() {
            @Override
            public void onSuccess(HttpsResult model) {
                Log.e("dandy", "绑定成功 " + model.getMessage());
                mManageView.cancelDialog();
                mManageView.toastMeassager(model.getMessage());
                mManageView.statusChange();

            }

            @Override
            public void onFailure(String msg) {
                Log.e("dandy", "绑定失败 " + msg);
                mManageView.toastMeassager(msg);
            }

            @Override
            public void onFinish() {

            }
        };
        Gson gson = new Gson();
        String route = gson.toJson(info);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), route);
        addSubscription(mApiStore.bindTeacher(body), subscriber);
    }


}
