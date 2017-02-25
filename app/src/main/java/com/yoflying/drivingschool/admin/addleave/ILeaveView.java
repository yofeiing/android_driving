package com.yoflying.drivingschool.admin.addleave;

import com.yoflying.drivingschool.base.BaseView;
import com.yoflying.drivingschool.entity.Person;

import java.util.List;

/**
 * Created by yaojiulong on 2017/1/16.
 */

public interface ILeaveView  extends BaseView{
    void getTeachersInfo(List<Person> list);
    void setTeacherName(String name);


    int getTeacherId();
    String getStartTime();
    String getEndTime();
    int getStatus();
    String getTeacherName();

}
