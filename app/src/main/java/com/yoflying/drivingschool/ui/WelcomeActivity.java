package com.yoflying.drivingschool.ui;

import android.content.Intent;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseActivity;
import com.yoflying.drivingschool.modules.welcome.IWelcomeView;
import com.yoflying.drivingschool.modules.welcome.WelcomePresenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 欢迎页面
 */
public class WelcomeActivity extends BaseActivity implements IWelcomeView {
    Logger logger = LoggerFactory.getLogger(WelcomeActivity.class);
    private WelcomePresenter mPresenter;

    @Override
    protected void initView() {
        /*
        logger.trace("angcyo-->{}", "trace");
        logger.debug("angcyo-->{}", "debug");
        logger.info("angcyo-->{}", "info");
        logger.warn("angcyo-->{}", "warn");
        logger.error("angcyo-->{}", "error");
        Logger logtest = LoggerFactory.getLogger("logtest");
        //以下日志会在BASE_ROLL_FILE声明的文件中输出,并且也会在控制台输出
        logtest.trace("logtest-->{}", "trace");
        logtest.debug("logtest-->{}", "debug");
        logtest.info("logtest-->{}", "info");
        logtest.warn("logtest-->{}", "warn");
        logtest.error("logtest-->{}", "error");
        */
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
