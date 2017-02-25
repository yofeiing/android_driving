package com.yoflying.drivingschool.admin.create;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseActivity;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.admin.create.CreatePresenter;
import com.yoflying.drivingschool.admin.create.ICreateView;

/**
 * 创建学员or教练界面
 */
public class CreateActivity extends BaseActivity implements ICreateView {
    private Intent mIntent;
    private RadioGroup mTypeGroup,mSexGroup;
    private RadioButton mManRbtn,mWomanRbtn,mTeacherRbtn,mStudentRbtn;
    private int mUserType;//创建的用户类别
    private int mSexType=1;
    private EditText mUserName,mIdNum,mPassword,mPhone,mWechat;
    private int mCourse=1;
    private Spinner mCourseView;
    private String[] mCourses;
    private CreatePresenter mPresenter;
    private Button mSubmit;
    private LinearLayout mContentLayout;

    @Override
    protected void initView() {
        mContentLayout=findView(R.id.create_layout);
        setContentView(R.layout.activity_create);
        mTypeGroup=findView(R.id.create_type_group);
        mSexGroup=findView(R.id.create_sex_group);
        mManRbtn=findView(R.id.create_sex_man);
        mWomanRbtn=findView(R.id.create_sex_woman);
        mTeacherRbtn=findView(R.id.create_type_teacher);
        mStudentRbtn=findView(R.id.create_type_student);
        mUserName=findView(R.id.create_name);
        mIdNum=findView(R.id.create_identity);
        mPassword=findView(R.id.create_psd);
        mPhone=findView(R.id.create_phone);
        mWechat=findView(R.id.create_wechat);
        mCourseView=findView(R.id.create_course);
        mSubmit=findView(R.id.create_submit);
        addToolbar();
        mPresenter=new CreatePresenter(this);
    }

    @Override
    protected void initData() {
        mIntent=getIntent();
        mUserType=mIntent.getExtras().getInt(Config.CREATE_USER_TYPE);
        if (mUserType==Config.USER_TYPE_STUDENT){

            mStudentRbtn.setChecked(true);
        }else {

            mTeacherRbtn.setChecked(true);
        }

        mSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.create_sex_man:
                        mSexType=Config.SEX_MAN;
                        break;
                    case R.id.create_sex_woman:
                        mSexType=Config.SEX_WOMAN;
                        break;
                }
            }
        });

        mTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.create_type_student:
                        mUserType=Config.USER_TYPE_STUDENT;
                        break;
                    case R.id.create_type_teacher:
                        mUserType=Config.USER_TYPE_TEACHER;
                        break;
                }
            }
        });
        mCourses= getResources().getStringArray(R.array.course_array);
        mCourseView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("dandy","选中了 "+mCourses[position]);
                mCourse=position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getInfo();
            }
        });

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
    public String getUserName() {
        return mUserName.getText().toString();
    }

    @Override
    public String getIDnum() {
        return mIdNum.getText().toString();
    }

    @Override
    public int getSex() {
        return mSexType;
    }

    @Override
    public int getUserType() {
        return mUserType;
    }

    @Override
    public String getPassword() {
        return mPassword.getText().toString();
    }

    @Override
    public String getPhone() {
        return mPhone.getText().toString();
    }

    @Override
    public String getWechat() {
        return mWechat.getText().toString();
    }

    @Override
    public int getCourse() {
        return mCourse;
    }

    @Override
    public void toastMeassage(String msg) {
        showSnackView(getView(),msg);
        mUserName.setText("");
        mPassword.setText("");
        mPhone.setText("");
        mWechat.setText("");
        mIdNum.setText("");
    }
}
