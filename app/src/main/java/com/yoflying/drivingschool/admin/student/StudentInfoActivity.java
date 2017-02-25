package com.yoflying.drivingschool.admin.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseActivity;
import com.yoflying.drivingschool.config.Config;

/**
 * 学员详情页面
 */
public class StudentInfoActivity extends BaseActivity {
    private String mStudentName;
    private int mStudentId;
    private Intent mIntent;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_student_info);
        addToolbar();
    }

    @Override
    protected void initData() {
        super.initData();
        mIntent=getIntent();
        mStudentName=mIntent.getStringExtra(Config.KEY_STUDENT_NAME);
        mStudentId=mIntent.getIntExtra(Config.KEY_STUDENT_ID,0);
        setTitle(mStudentName);
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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade,R.anim.hold);
    }
}
