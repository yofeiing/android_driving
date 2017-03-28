package com.yoflying.drivingschool.student.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseFragment;
import com.yoflying.drivingschool.student.bean.FutureCourse;
import com.yoflying.drivingschool.student.schedule.IScheduleView;

import java.util.List;

/**日程
 * Created by yaojiulong on 2017/1/4.
 */

public class ScheduleFragment extends BaseFragment implements IScheduleView{
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mFutureCourse;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_schedule_student,container,false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {
        mRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.schedule_refresh_layout);
        mFutureCourse= (RecyclerView) view.findViewById(R.id.schedule_future_course_rlv);
    }

    @Override
    public void getFutureCourse(List<FutureCourse> data) {

    }
}
