package com.yoflying.drivingschool.admin.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.admin.addleave.AddLeaveActivity;
import com.yoflying.drivingschool.admin.configmange.ui.ConfigManageFragment;
import com.yoflying.drivingschool.admin.leaveinfo.LeaveManageFragment;
import com.yoflying.drivingschool.admin.student.StudentManageFragment;
import com.yoflying.drivingschool.admin.teacher.TeacherManageFragment;
import com.yoflying.drivingschool.base.BaseActivity;
import com.yoflying.drivingschool.config.Config;

/**
 * 人员管理界面 分为教练和学员
 */
public class PersonManageActivity extends BaseActivity {
    private StudentManageFragment mStudentFragment;
    private TeacherManageFragment mTeacherFragment;
    private FrameLayout mContentLayout;
    private LeaveManageFragment mLeaveFragment;
    private ConfigManageFragment mConfigFragment;
    private Intent mIntent;
    private int mType;
    private FragmentManager mManager;
    private android.support.v4.app.FragmentTransaction mTransaction;
    private ImageView mRightMenu;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_person_manage);
        addToolbar();
    }

    @Override
    protected void findViewId() {
        super.findViewId();
        mContentLayout=findView(R.id.person_manage_layout);
    }

    @Override
    protected void initData() {
        super.initData();
        mIntent=getIntent();
        mType=mIntent.getExtras().getInt(Config.CREATE_USER_TYPE);
        mManager=getSupportFragmentManager();
        mTransaction=mManager.beginTransaction();

        switch (mType){
            case Config.USER_TYPE_STUDENT:
                mStudentFragment=new StudentManageFragment();
                mTransaction.replace(R.id.person_manage_layout,mStudentFragment);
                mTransaction.commit();
                setTitle(getResources().getString(R.string.admin_student_manage));
                break;
            case Config.USER_TYPE_TEACHER:
                mTeacherFragment=new TeacherManageFragment();
                mTransaction.replace(R.id.person_manage_layout,mTeacherFragment);
                mTransaction.commit();
                setTitle(getResources().getString(R.string.admin_teacher_manage));
                break;
            case Config.TYPE_LEAVE:
                mLeaveFragment=new LeaveManageFragment();
                mTransaction.replace(R.id.person_manage_layout,mLeaveFragment);
                mTransaction.commit();
                setTitle(getResources().getString(R.string.admin_teacher_leave));
                mRightMenu=getToolbarmenu();
                mRightMenu.setBackgroundResource(R.drawable.icon_add);
                startAddLeave();
                break;
            case Config.TYPE_CONFIG:
                mConfigFragment=new ConfigManageFragment();
                mTransaction.replace(R.id.person_manage_layout,mConfigFragment);
                mTransaction.commit();
                setTitle(getResources().getString(R.string.admin_order_config));
                break;

        }


    }

    @Override
    protected String[] getNeedPermissions() {
        return new String[0];
    }

    @Override
    protected void permissionGrantedSuccess() {

    }

    @Override
    protected void permissionGrantedFail() {

    }

    private void startAddLeave(){
        mRightMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PersonManageActivity.this, AddLeaveActivity.class);
                startActivity(intent);
            }
        });
    }
}
