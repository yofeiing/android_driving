package com.yoflying.drivingschool.teacher.todaycourse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseFragment;

/**教练端今日课程
 * Created by yaojiulong on 2017/4/12.
 */

public class TodayCourseFragment extends BaseFragment {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_teacher_today_course,container,false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {

    }
}
