package com.yoflying.drivingschool.admin.today;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.admin.adapter.TodayCourseAdapter;
import com.yoflying.drivingschool.base.BaseFragment;
import com.yoflying.drivingschool.entity.TodayCourse;

import java.util.List;

/**今日课程页面
 * Created by yaojiulong on 2017/1/7.
 */

public class TodayFragment extends BaseFragment implements ITodayView ,SwipeRefreshLayout.OnRefreshListener{
    private TodayMangePresenter mPresenter;
    private RecyclerView mCoureseView;
    private SwipeRefreshLayout mRefreshLayout;
    private TodayCourseAdapter mAdapter;
    private List<TodayCourse> mTodayCourse;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_today_admin,container,false);
        mPresenter=new TodayMangePresenter(this);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {
        mCoureseView= (RecyclerView) view.findViewById(R.id.today_courese_rlv);
        mRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.today_refresh_layout);
        mCoureseView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRefreshLayout.setColorSchemeResources(R.color.refresh_color_1
                , R.color.refresh_color_2, R.color.refresh_color_3, R.color.refresh_color_4);
        mRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }


    @Override
    public void initData() {
        super.initData();
        mRefreshLayout.setRefreshing(true);
        mPresenter.getTodayCourse();

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void cancelDialog() {

    }

    @Override
    public void toastMeassager(String msg) {
        if (getView()!=null){
            showSnackView(getView(),msg);
        }

    }

    @Override
    public void showTodayCourse(List<TodayCourse> data) {

        mAdapter=new TodayCourseAdapter(data);
        mCoureseView.setAdapter(mAdapter);
        mRefreshLayout.setRefreshing(false);
        mTodayCourse=data;

    }

    @Override
    public void closeRefresh() {
        mRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onRefresh() {
        mPresenter.getTodayCourse();
    }
}
