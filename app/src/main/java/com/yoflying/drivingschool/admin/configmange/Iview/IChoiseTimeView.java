package com.yoflying.drivingschool.admin.configmange.Iview;

import com.yoflying.drivingschool.entity.Person;

import java.util.List;

/**
 * Created by yaojiulong on 2017/2/22.
 */

public interface IChoiseTimeView {

    void getTeachersInfo(List<Person> teachers);
    void stopRefresh();
}
