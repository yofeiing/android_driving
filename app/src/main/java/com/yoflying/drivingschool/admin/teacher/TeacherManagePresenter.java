package com.yoflying.drivingschool.admin.teacher;

import com.yoflying.drivingschool.base.BasePresenter;
import com.yoflying.drivingschool.entity.HttpsResult;
import com.yoflying.drivingschool.entity.Person;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.retrofit.ApiCallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**教练管理控制器
 * Created by yaojiulong on 2017/1/16.
 */

public class TeacherManagePresenter extends BasePresenter<ITeacherManageView> {
    private ITeacherManageView mManageView;


    public TeacherManagePresenter(ITeacherManageView mManageView) {
        this.mManageView = mManageView;
        attachView(mManageView);
    }

    public void getTeachersInfo(String pageIndex){
        mManageView.setRefresh();
        ApiCallBack<HttpsResult<List<Person>>> subscriber=new ApiCallBack<HttpsResult<List<Person>>>() {
            @Override
            public void onSuccess(HttpsResult<List<Person>> model) {
                mManageView.stopRefresh();
                mManageView.showTeachersInfo(model.getData());
            }

            @Override
            public void onFailure(String msg) {
                mManageView.stopRefresh();
            }

            @Override
            public void onFinish() {

            }
        };
        Map<String,String> map=new HashMap<>();
        map.put(Config.PARAMS_PAGE_NUM,pageIndex);
        addSubscription(mApiStore.getTeacherList(map),subscriber);
    }
}
