package com.yoflying.drivingschool.student.myteacher;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseFragment;

/**
 * 我的教练页面
 * Created by yaojiulong on 2017/3/29.
 */

public class MyTeacherFragment extends BaseFragment {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_my_teacher, container, false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {

    }
}
