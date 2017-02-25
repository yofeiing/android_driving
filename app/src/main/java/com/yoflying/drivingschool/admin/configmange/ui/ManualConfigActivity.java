package com.yoflying.drivingschool.admin.configmange.ui;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseActivity;

/**手动预约配置页面 七天之内的
 * Created by yaojiulong on 2017/2/20.
 */

public class ManualConfigActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView mTimeList;
    private ImageView mAddImg;


    @Override
    protected void initView() {


        setContentView(R.layout.activity_manual_config);
        addToolbar();
        setTitle("手动预约配置");
    }

    @Override
    protected void findViewId() {
        super.findViewId();
        mTimeList=findView(R.id.auto_config_list);
        mAddImg=findView(R.id.auto_config_add_img);
    }

    @Override
    protected void setLinstener() {
        super.setLinstener();
        mAddImg.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.auto_config_add_img:
                Intent intent=new Intent(ManualConfigActivity.this, ChoiseTimeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

            break;
        }
    }
}
