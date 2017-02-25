package com.yoflying.drivingschool.admin.search;

import com.yoflying.drivingschool.base.BaseView;
import com.yoflying.drivingschool.entity.Person;

import java.util.List;

/**
 * Created by yaojiulong on 2017/1/10.
 */

public interface ISearchView extends BaseView {
    String getInputContent();
    void setSearchResult(List<Person> data);
}
