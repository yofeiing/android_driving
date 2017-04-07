package com.yoflying.drivingschool.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseActivity;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.modules.login.IUserLoginView;
import com.yoflying.drivingschool.modules.login.UserLoginPresenter;
import com.yoflying.drivingschool.widget.CircularAnim;


/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements IUserLoginView{
    private UserLoginPresenter mUserLoginPresenter=new UserLoginPresenter(this);
    private AutoCompleteTextView mUserName;
    private EditText mPassword;
    private RadioGroup mRadioGroup;
    private RadioButton mAdmin,mTeacher,mStudent;
//    private ProgressDialog mDialog;
    private Button mSingIn;
    private int mUserType= Config.USER_TYPE_ADMIN;
    private LinearLayout mLoginLayout;
    private boolean isLogin;
    private FrameLayout mFramlayout;
    private ProgressBar mProgressbar;


    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_login);
//        addToolbar();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    protected void findViewId() {
        super.findViewId();
        mLoginLayout=findView(R.id.longin_layout);
        mUserName=findView(R.id.login_username);
        mPassword=findView(R.id.login_password);
        mRadioGroup=findView(R.id.login_group);
        mAdmin=findView(R.id.login_admin);
        mTeacher=findView(R.id.login_teacher);
        mStudent=findView(R.id.login_student);
        mSingIn=findView(R.id.login_sing_in);
        mFramlayout = (FrameLayout) findViewById(R.id.fram_button);
        mProgressbar = (ProgressBar) findViewById(R.id.progress);

    }



    @Override
    protected void initData() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.login_admin:
                        mUserType=Config.USER_TYPE_ADMIN;
                        break;
                    case R.id.login_teacher:
                        mUserType=Config.USER_TYPE_TEACHER;
                        break;
                    case R.id.login_student:
                        mUserType=Config.USER_TYPE_STUDENT;
                        break;
                    default:

                        break;

                }
            }
        });

        mSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mUserLoginPresenter.login();

            }
        });

        mAdmin.setChecked(true);
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
    public String getUseName() {

        return mUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPassword.getText().toString();
    }

    @Override
    public void showDialog() {

//        mDialog=ProgressDialog.show(LoginActivity.this,"提示","正在登录...");
        mProgressbar.setVisibility(View.VISIBLE);
            CircularAnim.hide(mSingIn).go();



    }

    @Override
    public void cancelDialog() {
//        mDialog.cancel();

        mProgressbar.setVisibility(View.GONE);
        //CircularAnim.hide(mSingIn).go();
    }

    @Override
    public void toastMeassager(String msg) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CircularAnim.show(mSingIn).go();

            }
        },500);
        showSnackView(getView(),msg);
    }

    @Override
    public void toMainActivity() {
        startActivityWithFull(this,HomeActivity.class,mFramlayout);

    }

    @Override
    public void showFailedError() {

    }

    /**
     * 获取用户类型
     * @return
     */
    @Override
    public int getUserType() {
        return mUserType;
    }

    @Override
    public void userOrPwdIsNull() {
        isLogin = false;
        Toast.makeText(LoginActivity.this,"用户名或者密码不能为空",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserLoginPresenter.destory();
    }
}

