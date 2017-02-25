package com.yoflying.drivingschool.admin.today;

import com.yoflying.drivingschool.base.BaseView;
import com.yoflying.drivingschool.entity.TodayCourse;

import java.util.List;

/**
 * Created by yaojiulong on 2017/1/18.
 */

public interface ITodayView extends BaseView {
    void showTodayCourse(List<TodayCourse> data) ;
    void closeRefresh();
}
