package com.yoflying.drivingschool.admin.configmange.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseFragment;

/**预约配置界面
 * Created by yaojiulong on 2017/1/16.
 */

public class ConfigManageFragment extends BaseFragment implements View.OnClickListener{
    private TextView mMaualtv;//自动textview
    private TextView mAutotv;//手动textview

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_manage_config,container,false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {
        mMaualtv= (TextView) view.findViewById(R.id.admin_config_manual_tv);
        mAutotv= (TextView) view.findViewById(R.id.admin_config_auto_tv);
        mMaualtv.setOnClickListener(this);
        mAutotv.setOnClickListener(this);
    }

    @Override
    public void setLinstener() {
        super.setLinstener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.admin_config_auto_tv:
                Intent autoIntent=new Intent(getActivity(),AutoConfigActivity.class);
                getActivity().startActivity(autoIntent);
                break;
            case R.id.admin_config_manual_tv:
                Intent intent=new Intent(getActivity(),ManualConfigActivity.class);
                getActivity().startActivity(intent);


                break;
            default:
                break;

        }
    }
}
