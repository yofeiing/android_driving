package com.yoflying.drivingschool.student.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseFragment;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.student.history.HistoryOrderFragment;
import com.yoflying.drivingschool.student.protocol.MyProtocolFragment;

/** 个人信息
 * Created by yaojiulong on 2017/1/4.
 */

public class MyInfoFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout mMyTeacherLayout,mMyCourseLayout,mMyHistoryLayout,mMyProtocolLayout;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_myinfo_student,container,false);
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
                Intent teacher=new Intent(getContext(),StudentInfoActivity.class);
                teacher.putExtra(Config.KEY_STUDENT_FRAGMENT_TYPE,Config.TYPE_STUDENT_TEACHER);
                startActivity(teacher);
                break;

            case R.id.student_info_course_layout:
                Intent course =new Intent(getContext(),StudentInfoActivity.class);
                course.putExtra(Config.KEY_STUDENT_FRAGMENT_TYPE,Config.TYPE_STUDENT_COURSE);
                startActivity(course);
                break;
            case R.id.student_order_history_layout:
                Intent history=new Intent(getContext(), StudentInfoActivity.class);
                history.putExtra(Config.KEY_STUDENT_FRAGMENT_TYPE,Config.TYPE_STUDENT_HISTORY);
                startActivity(history);
                break;
            case R.id.student_info_protocol_layout:
                Intent protocol=new Intent(getContext(), StudentInfoActivity.class);
                protocol.putExtra(Config.KEY_STUDENT_FRAGMENT_TYPE,Config.TYPE_STUDENT_PROTOCOL);
                startActivity(protocol);
                break;
        }
    }
}
