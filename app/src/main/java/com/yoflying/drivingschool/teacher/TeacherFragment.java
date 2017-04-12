package com.yoflying.drivingschool.teacher;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseFragment;
import com.yoflying.drivingschool.base.BaseHomeFragment;
import com.yoflying.drivingschool.teacher.message.MessageFragment;
import com.yoflying.drivingschool.teacher.person.PresonFragment;
import com.yoflying.drivingschool.teacher.todaycourse.TodayCourseFragment;

import java.util.ArrayList;
import java.util.List;

/**教练端首页
 * Created by yaojiulong on 2016/12/29.
 */

public class TeacherFragment extends BaseHomeFragment {

    private List<String> mTitles;
    private List<BaseFragment> mFragments;
    private TodayCourseFragment mCoureseFragment;
    private MessageFragment mMessageFragment;
    private PresonFragment mPresonFragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mTitles=new ArrayList<>();
        mTitles.add("今日课程");
        mTitles.add("信息");
        mTitles.add("个人");
        mFragments=new ArrayList<>();
        mCoureseFragment=new TodayCourseFragment();
        mFragments.add(mCoureseFragment);
        mMessageFragment=new MessageFragment();
        mFragments.add(mMessageFragment);
        mPresonFragment=new PresonFragment();
        mFragments.add(mPresonFragment);
        setData(mFragments,mTitles);
    }

    @Override
    public void initData() {
        super.initData();

    }
}
