package com.yoflying.drivingschool.student.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseFragment;

/**历史约车（约车记录）界面
 * Created by yaojiulong on 2017/3/28.
 */
public class HistoryOrderFragment extends BaseFragment{
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_history_order,container,false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {

    }

}
