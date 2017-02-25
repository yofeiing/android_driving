package com.yoflying.drivingschool.admin.leaveinfo;

import android.util.Log;

import com.yoflying.drivingschool.admin.addleave.ILeaveView;
import com.yoflying.drivingschool.base.BasePresenter;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.entity.HttpsResult;
import com.yoflying.drivingschool.entity.LeaveInfo;
import com.yoflying.drivingschool.retrofit.ApiCallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**请假列表控制器
 * Created by yaojiulong on 2017/2/7.
 */

public class LeaveInfoPresenter extends BasePresenter<ILeaveInfoView> {
    private ILeaveInfoView mLeaveInfoView;

    public LeaveInfoPresenter(ILeaveInfoView mLeaveInfoView) {
        this.mLeaveInfoView = mLeaveInfoView;
        attachView(mLeaveInfoView);
    }

    /**
     * 获取历史请假列表
     * @param pageIndex  页面
     */
    public void getLeaveList(int pageIndex){
        mLeaveInfoView.showRefresh();
        ApiCallBack<HttpsResult<List<LeaveInfo>>> subscriber=new ApiCallBack<HttpsResult<List<LeaveInfo>>>() {
            @Override
            public void onSuccess(HttpsResult<List<LeaveInfo>> model) {

                if (model.getStatus()==0){
                    mLeaveInfoView.setLeveInfoData(model.getData());

                }else {
                    mLeaveInfoView.showMessage(model.getMessage());
                }
                mLeaveInfoView.stopRefresh();
            }

            @Override
            public void onFailure(String msg) {
                mLeaveInfoView.stopRefresh();
                mLeaveInfoView.showMessage(msg);
            }

            @Override
            public void onFinish() {

            }
        };
        Map<String,String> map=new HashMap<>();
        map.put(Config.PARAMS_PAGE_NUM,String.valueOf(pageIndex));
        addSubscription(mApiStore.getLeaveList(map),subscriber);

    }
}
