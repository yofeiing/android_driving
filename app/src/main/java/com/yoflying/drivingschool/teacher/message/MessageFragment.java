package com.yoflying.drivingschool.teacher.message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseFragment;

/**教练端 信息
 * Created by yaojiulong on 2017/4/12.
 */

public class MessageFragment extends BaseFragment {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_teacher_message,container,false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {

    }
}
