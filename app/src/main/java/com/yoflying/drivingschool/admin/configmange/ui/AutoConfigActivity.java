package com.yoflying.drivingschool.admin.configmange.ui;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseActivity;

/**自动预约配置页面
 * Created by yaojiulong on 2017/2/20.
 */

public class AutoConfigActivity extends BaseActivity{
    @Override
    protected void initView() {
        setContentView(R.layout.activity_auto_config);
        addToolbar();
        setTitle("自动预约配置");
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
}
