package com.yoflying.drivingschool.admin.student;

import com.yoflying.drivingschool.base.BaseView;
import com.yoflying.drivingschool.entity.Person;

import java.util.List;

/**
 * Created by yaojiulong on 2017/1/13.
 */

public interface IStudentManageView extends BaseView{
    void showStudentsInfo(List<Person> mLists);
    void setRefresh();
    void stopRefresh();
    void getTeachersInfo(List<Person> list);
    void statusChange();
}
