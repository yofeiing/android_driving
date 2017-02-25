package com.yoflying.drivingschool.admin.message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseFragment;

/**信息界面
 * Created by yaojiulong on 2017/1/7.
 */

public class MessageFragment extends BaseFragment {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_msg_admin,container,false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {

    }
}
