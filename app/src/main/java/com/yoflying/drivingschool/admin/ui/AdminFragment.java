package com.yoflying.drivingschool.admin.ui;

import android.content.Context;
import android.os.Bundle;

import com.yoflying.drivingschool.admin.manage.ManageFragment;
import com.yoflying.drivingschool.admin.message.MessageFragment;
import com.yoflying.drivingschool.admin.today.TodayFragment;
import com.yoflying.drivingschool.base.BaseFragment;
import com.yoflying.drivingschool.base.BaseHomeFragment;

import java.util.ArrayList;
import java.util.List;

/**管理员主页
 * Created by yaojiulong on 2016/12/29.
 */

public class AdminFragment extends BaseHomeFragment {

    private List<String> mTitles;
    private List<BaseFragment> mFragments;
    private TodayFragment mTodayFragment;
    private MessageFragment mMsgFragment;
    private ManageFragment mManageFragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mTitles=new ArrayList<>();
        mTitles.add("今日课程");
        mTitles.add("信息");
        mTitles.add("管理");
        mFragments=new ArrayList<>();
        mTodayFragment=new TodayFragment();
        mMsgFragment=new MessageFragment();
        mManageFragment=new ManageFragment();

        mFragments.add(mTodayFragment);
        mFragments.add(mMsgFragment);
        mFragments.add(mManageFragment);

        setData(mFragments,mTitles);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


}
