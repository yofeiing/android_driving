package com.yoflying.drivingschool.admin.configmange.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.admin.adapter.ManualConfigAdapter;
import com.yoflying.drivingschool.admin.configmange.IView.IMaualConfigView;
import com.yoflying.drivingschool.admin.configmange.presenter.MaualConfigPresenter;
import com.yoflying.drivingschool.admin.listener.ManualDialogListener;
import com.yoflying.drivingschool.base.BaseActivity;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.entity.CourseConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**手动预约配置页面 七天之内的
 * Created by yaojiulong on 2017/2/20.
 */

public class ManualConfigActivity extends BaseActivity implements View.OnClickListener ,IMaualConfigView
    ,ManualDialogListener{
    private RecyclerView mAllDataView;
    private ImageView mAddImg;
    private ImageView mRightMenu;
    private Map<String,List<CourseConfig>> mDataMap=new HashMap<>();
    private ManualConfigAdapter mAdapter;
    private List<Map<String,List<CourseConfig>>> mDataList;
    private List<String> mDates;
    private MaualConfigPresenter mPresenter;
    private ManualConfigFrament mConfigFragment;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_manual_config);
        addToolbar();
        setTitle("手动预约配置");
        mRightMenu=getToolbarmenu();
        mRightMenu.setImageResource(R.drawable.icon_submit);
        mPresenter=new MaualConfigPresenter(this);
    }

    @Override
    protected void findViewId() {
        super.findViewId();
        mAllDataView=findView(R.id.auto_config_list);
        mAddImg=findView(R.id.auto_config_add_img);
    }

    @Override
    protected void initData() {
        super.initData();
        mAllDataView.setLayoutManager(new LinearLayoutManager(this));
        mDataList=new ArrayList<>();
        mDates=new ArrayList<>();
        mAdapter=new ManualConfigAdapter(mDataList);
        mAllDataView.setAdapter(mAdapter);
    }

    @Override
    protected void setLinstener() {
        super.setLinstener();
        mAddImg.setOnClickListener(this);
        mRightMenu.setOnClickListener(this);
        mAllDataView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Map<String,List<CourseConfig>> map=mDataList.get(position);
              //  Log.e("dandy","点击了  "+position);
                for (String key:map.keySet()){
                    List<CourseConfig> list=map.get(key);
                  //  Log.e("dandy","点击的数据 "+list.toString());
                }

                mConfigFragment=new ManualConfigFrament();
                Bundle bundle=new Bundle();
                bundle.putSerializable(Config.KEY_COURSE_CONFIG, (Serializable) map);
                mConfigFragment.setArguments(bundle);
                mConfigFragment.show(getSupportFragmentManager(),""+String.valueOf(position));
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.auto_config_add_img:
                Intent intent=new Intent(ManualConfigActivity.this, ChoiseTimeActivity.class);
                startActivityForResult(intent,Config.MANULA_REQUEST_CODE);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

            break;
            case R.id.toolbar_right_menu:
                mPresenter.submitMaualConfigData(mDataList);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null){
            return;
        }
       // Log.e("dandy","code "+requestCode+"  "+resultCode);
        Bundle bundle=data.getBundleExtra(Config.KEY_INTENT_CONFIG);
        mDataMap= (Map<String, List<CourseConfig>>) bundle.getSerializable(Config.KEY_BUNDLE_CONFIG);
        String date="";
        for (String key:mDataMap.keySet()){
            date=key;
        }

        int p=checkData(date);
        if (p>-1){
             mDataList.get(p).get(date).addAll(mDataMap.get(date));
        }else {
            mDates.add(date);
            mDataList.add(mDataMap);
        }
        setRecyclerViewData(mDataList);

    }



    /**
     * 检查是否已有这一天的数据，有，则返回索引，索引等于-1表示没有
     * @param date
     * @return
     */
    private int checkData(String date){
        int r=-1;

        for (int i = 0; i < mDates.size(); i++) {
            if (mDates.get(i).equals(date)){
                return i;
            }
        }
        return r;
    }


    /**
     * 设置recyclerview数据
     * @param data 数据
     */
    private void setRecyclerViewData(List<Map<String,List<CourseConfig>>> data){
        mDataList=data;
        if (mAdapter==null){
            mAdapter=new ManualConfigAdapter(data);

            mAllDataView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void cancelDialog() {
        dimssDialog();
    }

    @Override
    public void toastMeassager(String msg) {
        showSnackView(getView(),msg);
    }

    @Override
    public void onDialogListener(Map<String, List<CourseConfig>> map,int pos) {
        mDataList.set(pos,map);

    }
}
