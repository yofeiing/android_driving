package com.yoflying.drivingschool.ui;

import android.content.Intent;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseActivity;
import com.yoflying.drivingschool.modules.welcome.IWelcomeView;
import com.yoflying.drivingschool.modules.welcome.WelcomePresenter;


/**
 * 欢迎页面
 */
public class WelcomeActivity extends BaseActivity implements IWelcomeView {
    private WelcomePresenter mPresenter;


    @Override
    protected void initView() {
        // setContentView(R.layout.activity_wecome);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter=new WelcomePresenter(this);
        mPresenter.checkData();
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
    public void toLoginActivity() {
        Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void toAdminActivity() {
        Intent intent=new Intent(WelcomeActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
