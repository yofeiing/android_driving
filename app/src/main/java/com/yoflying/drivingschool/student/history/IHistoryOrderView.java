package com.yoflying.drivingschool.student.history;

import com.yoflying.drivingschool.base.BaseView;
import com.yoflying.drivingschool.student.bean.HistoryRecord;

import java.util.List;

/**
 * Created by yaojiulong on 2017/4/1.
 */

public interface IHistoryOrderView extends BaseView {
    void getHistoryData(List<HistoryRecord> data);
}
