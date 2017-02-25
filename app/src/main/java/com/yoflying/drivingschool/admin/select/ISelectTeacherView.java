package com.yoflying.drivingschool.admin.select;

import com.yoflying.drivingschool.entity.Person;

import java.util.List;

/**
 * Created by yaojiulong on 2017/2/23.
 */

public interface ISelectTeacherView {
    void getTeachersInfo(List<Person> mTeachers);
    void stopRefresh();
}
