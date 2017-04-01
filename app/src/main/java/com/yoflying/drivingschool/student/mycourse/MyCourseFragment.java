package com.yoflying.drivingschool.student.mycourse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseFragment;

/**我的课程页面
 * Created by yaojiulong on 2017/3/29.
 */

public class MyCourseFragment extends BaseFragment {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_my_course,container,false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {

    }
}
