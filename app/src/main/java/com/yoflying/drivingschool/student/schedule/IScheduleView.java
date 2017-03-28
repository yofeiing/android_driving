package com.yoflying.drivingschool.student.schedule;

import com.yoflying.drivingschool.base.BaseView;
import com.yoflying.drivingschool.student.bean.FutureCourse;

import java.util.List;

/**
 * Created by yaojiulong on 2017/3/28.
 */

public interface IScheduleView  {
    void getFutureCourse(List<FutureCourse> data);

}
