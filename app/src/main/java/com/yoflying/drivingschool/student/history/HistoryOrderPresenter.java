package com.yoflying.drivingschool.student.history;

import android.util.Log;

import com.yoflying.drivingschool.base.BasePresenter;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.entity.HttpsResult;
import com.yoflying.drivingschool.retrofit.ApiCallBack;
import com.yoflying.drivingschool.student.bean.HistoryRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学员历史预约记录 {@link com.yoflying.drivingschool.student.history.HistoryOrderFragment}
 * Created by yaojiulong on 2017/4/1.
 */

public class HistoryOrderPresenter extends BasePresenter<IHistoryOrderView> {
    private IHistoryOrderView mView;

    public HistoryOrderPresenter(IHistoryOrderView mView) {
        this.mView = mView;
        attachView(mView);
    }

    /**
     * 获取历史信息
     *
     * @param num 页
     */
    public void getHistoryOrder(String num) {
        mView.showDialog();
        ApiCallBack<HttpsResult<List<HistoryRecord>>> subcriber = new ApiCallBack<HttpsResult<List<HistoryRecord>>>() {
            @Override
            public void onSuccess(HttpsResult<List<HistoryRecord>> model) {
                mView.cancelDialog();
                Log.e("dandy", "历史约车记录 " + model.getData());
                if (model.getStatus() > -1) {
                    mView.getHistoryData(model.getData());
                }
            }

            @Override
            public void onFailure(String msg) {
                Log.e("dandy", "历史记录获取失败 " + msg);
                mView.toastMeassager(msg);
            }

            @Override
            public void onFinish() {

            }
        };
        Map<String, String> map = new HashMap<>();
        map.put(Config.HttpParams.PARAMS_PAGE_NUM, num);
        addSubscription(mApiStore.getHistoryRecords(map), subcriber);

    }
}
