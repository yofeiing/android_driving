package com.yoflying.drivingschool.admin.leaveinfo;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yoflying.drivingschool.DriverApplication;
import com.yoflying.drivingschool.R;

import com.yoflying.drivingschool.admin.adapter.LeaveListInfoAdapter;
import com.yoflying.drivingschool.base.BaseFragment;
import com.yoflying.drivingschool.entity.LeaveInfo;

import java.util.ArrayList;
import java.util.List;

/**请假管理页面
 * Created by yaojiulong on 2017/1/16.
 */

public class LeaveManageFragment extends BaseFragment implements View.OnClickListener,ILeaveInfoView,SwipeRefreshLayout.OnRefreshListener{
    private FloatingActionButton mAdd;
    private RecyclerView mLeavesView;
    private SwipeRefreshLayout mRefreshLayout;
    private LeaveInfoPresenter mPresenter;
    private DriverApplication mApplication;
    private LeaveListInfoAdapter mAdapter;
    private List<LeaveInfo> mLeaveInfos;
    private LinearLayoutManager mManager;
    private int mPageIndex=1;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_manage_leave,container,false);
        mPresenter=new LeaveInfoPresenter(this);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {
        mAdd= (FloatingActionButton) view.findViewById(R.id.leave_add_fab);
        mLeavesView= (RecyclerView) view.findViewById(R.id.leave_list_rlv);
        mRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.leave_refresh_layout);
        mRefreshLayout.setColorSchemeResources(R.color.refresh_color_1
                , R.color.refresh_color_2, R.color.refresh_color_3, R.color.refresh_color_4);
        mRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        mLeaveInfos=new ArrayList<>();
        initRecycler();
        mApplication= (DriverApplication) getActivity().getApplication();

        if (mLeaveInfos.size()==0){
            mPresenter.getLeaveList(mPageIndex);
        }

       // Log.e("dandy","全局教练 信息  "+mTeachers.toString());
    }

    @Override
    public void setLinstener() {
        super.setLinstener();
        mAdd.setOnClickListener(this);
        mAdd.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.leave_add_fab:

                break;
        }
    }

    @Override
    public void showRefresh() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void stopRefresh() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String msg) {
        showSnackView(getView(),msg);
    }

    @Override
    public void setLeveInfoData(List<LeaveInfo> data) {
        if (data.size()>0){
            mLeaveInfos.addAll(data);
            mAdapter.notifyDataSetChanged();
            mAdapter.loadMoreComplete();

        }else if (data.size()==0){
            mAdapter.loadMoreEnd();
        }
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler(){
        mAdapter=new LeaveListInfoAdapter(mLeaveInfos);
        mManager=new LinearLayoutManager(getContext());
        mLeavesView.setLayoutManager(mManager);
        mLeavesView.setAdapter(mAdapter);
        setRecyclerListener();
    }

    @Override
    public void onRefresh() {
        mPresenter.getLeaveList(1);
    }

    private void setRecyclerListener(){
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.e("dandy","滑动到底部");
                mPageIndex++;
                mPresenter.getLeaveList(mPageIndex);
            }
        });

    }

}
