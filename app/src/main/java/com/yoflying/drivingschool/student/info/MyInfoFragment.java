package com.yoflying.drivingschool.student.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseFragment;

/** 个人信息
 * Created by yaojiulong on 2017/1/4.
 */

public class MyInfoFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout mMyTeacherLayout,mMyCourseLayout,mMyHistoryLayout,mMyProtocolLayout;
    private android.support.v4.app.FragmentTransaction mTransaction;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_myinfo_student,container,false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mTransaction=getFragmentManager().beginTransaction();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initFindViewById(View view) {
        mMyTeacherLayout= (RelativeLayout) view.findViewById(R.id.student_info_teacher_layout);
        mMyCourseLayout= (RelativeLayout) view.findViewById(R.id.student_info_course_layout);
        mMyHistoryLayout= (RelativeLayout) view.findViewById(R.id.student_order_history_layout);
        mMyProtocolLayout= (RelativeLayout) view.findViewById(R.id.student_info_protocol_layout);

    }

    @Override
    public void setLinstener() {
        super.setLinstener();
        mMyCourseLayout.setOnClickListener(this);
        mMyProtocolLayout.setOnClickListener(this);
        mMyHistoryLayout.setOnClickListener(this);
        mMyTeacherLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.student_info_teacher_layout:
                break;
            case R.id.student_info_course_layout:
                break;
            case R.id.student_order_history_layout:
                break;
            case R.id.student_info_protocol_layout:
                break;
        }
    }
}
