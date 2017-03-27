package com.yoflying.drivingschool.admin.manage;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yoflying.drivingschool.app.DriverApplication;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.admin.ui.PersonManageActivity;
import com.yoflying.drivingschool.base.BaseFragment;
import com.yoflying.drivingschool.config.Config;

/**管理员 管理fragment
 * Created by yaojiulong on 2017/1/7.
 */

public class ManageFragment extends BaseFragment implements View.OnClickListener{
    private RelativeLayout mTeacherLayout,mStudentLayout,mLeaveLayout,mOrderLayout;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_admin_manage,container,false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {
        mTeacherLayout= (RelativeLayout) view.findViewById(R.id.admin_manage_teacher_layout);
        mStudentLayout= (RelativeLayout) view.findViewById(R.id.admin_manage_student_layout);
        mLeaveLayout= (RelativeLayout) view.findViewById(R.id.admin_manage_leave_layout);
        mOrderLayout= (RelativeLayout) view.findViewById(R.id.admin_order_config_layout);
    }

    @Override
    public void setLinstener() {
        super.setLinstener();
        mTeacherLayout.setOnClickListener(this);
        mStudentLayout.setOnClickListener(this);
        mLeaveLayout.setOnClickListener(this);
        mOrderLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //教练管理
            case R.id.admin_manage_teacher_layout:
                Intent intent=new Intent(DriverApplication.getContextObject(),PersonManageActivity.class);
                intent.putExtra(Config.CREATE_USER_TYPE,Config.USER_TYPE_TEACHER);
                startActivity(intent);
                break;
            //学员管理
            case R.id.admin_manage_student_layout:
                Intent student=new Intent(DriverApplication.getContextObject(),PersonManageActivity.class);
                student.putExtra(Config.CREATE_USER_TYPE,Config.USER_TYPE_STUDENT);
                startActivity(student);
                break;
            case R.id.admin_manage_leave_layout:
                Intent leave=new Intent(DriverApplication.getContextObject(),PersonManageActivity.class);
                leave.putExtra(Config.CREATE_USER_TYPE,Config.TYPE_LEAVE);
                startActivity(leave);
                break;
            case R.id.admin_order_config_layout:
                Intent config=new Intent(DriverApplication.getContextObject(),PersonManageActivity.class);
                config.putExtra(Config.CREATE_USER_TYPE,Config.TYPE_CONFIG);
                startActivity(config);
                break;
        }
    }
}
