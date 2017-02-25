package com.yoflying.drivingschool.admin.teacher;

import com.yoflying.drivingschool.entity.Person;

import java.util.List;

/**
 * Created by yaojiulong on 2017/1/13.
 */

public interface ITeacherManageView {
    void showTeachersInfo(List<Person> mLists);
    void setRefresh();
    void stopRefresh();
}
