package com.yoflying.drivingschool.admin.listener;

import com.yoflying.drivingschool.entity.CourseConfig;

import java.util.List;
import java.util.Map;

/**
 * Created by yaojiulong on 2017/3/1.
 */

public interface ManualDialogListener {
    void onDialogListener(Map<String,List<CourseConfig>> map,int pos);
}
