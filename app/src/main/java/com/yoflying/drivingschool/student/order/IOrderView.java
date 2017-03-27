package com.yoflying.drivingschool.student.order;

import com.yoflying.drivingschool.base.BaseView;
import com.yoflying.drivingschool.student.bean.OrderInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by yaojiulong on 2017/1/7.
 */

public interface IOrderView  extends BaseView {
    void getDataSuccess(OrderInfo info);

    void showTimeTag(List<String> mTimes);

    void getDataMap(Map<String,List<OrderInfo.DataBean>> map);

}

