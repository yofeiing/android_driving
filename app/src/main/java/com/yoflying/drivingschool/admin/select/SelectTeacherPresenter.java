package com.yoflying.drivingschool.admin.select;

import com.yoflying.drivingschool.admin.student.IStutentInfoView;
import com.yoflying.drivingschool.base.BasePresenter;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.entity.HttpsResult;
import com.yoflying.drivingschool.entity.Person;
import com.yoflying.drivingschool.retrofit.ApiCallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yaojiulong on 2017/2/23.
 */

public class SelectTeacherPresenter extends BasePresenter<ISelectTeacherView> {
    private ISelectTeacherView mSelectTeacherView;

    public SelectTeacherPresenter(ISelectTeacherView mSelectTeacherView) {
        this.mSelectTeacherView = mSelectTeacherView;
        attachView(mSelectTeacherView);
    }


    /**
     * 获取教练列表
     */
    public void getTeachersInfo(){
        ApiCallBack<HttpsResult<List<Person>>> subscriber=new ApiCallBack<HttpsResult<List<Person>>>() {
            @Override
            public void onSuccess(HttpsResult<List<Person>> model) {
                mSelectTeacherView  .getTeachersInfo(model.getData());
            }

            @Override
            public void onFailure(String msg) {
                mSelectTeacherView.stopRefresh();
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
