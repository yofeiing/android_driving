package com.yoflying.drivingschool.student.history;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseFragment;
import com.yoflying.drivingschool.student.adapter.HistoryOrderAdapter;
import com.yoflying.drivingschool.student.bean.HistoryRecord;

import java.util.ArrayList;
import java.util.List;

/**历史约车（约车记录）界面
 * Created by yaojiulong on 2017/3/28.
 */
public class HistoryOrderFragment extends BaseFragment implements IHistoryOrderView,SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView mHistoryOrder;
    private HistoryOrderPresenter mPresenter;
    private HistoryOrderAdapter mAdapter;
    private int mPageNum=1;
    private List<HistoryRecord> mHistoryList;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_history_order,container,false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {
        mHistoryOrder= (RecyclerView) view.findViewById(R.id.student_history_order_rlv);
        mRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.student_history_refresh_layout);
        mRefreshLayout.setColorSchemeResources(R.color.refresh_color_1
                , R.color.refresh_color_2, R.color.refresh_color_3, R.color.refresh_color_4);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setEnabled(false);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter=new HistoryOrderPresenter(this);
        mHistoryOrder.setLayoutManager(new LinearLayoutManager(getContext()));
        mHistoryList=new ArrayList<>();
        mAdapter=new HistoryOrderAdapter(mHistoryList);
        mHistoryOrder.setAdapter(mAdapter);
        mPresenter.getHistoryOrder(String.valueOf(mPageNum));
        setRecyclerLinstener();
    }

    @Override
    public void showDialog() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void cancelDialog() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void toastMeassager(String msg) {
        showSnackView(msg);
    }

    @Override
    public void getHistoryData(List<HistoryRecord> data) {
        if (data.size()>0){
            mHistoryList.addAll(data);
            mAdapter.notifyDataSetChanged();
            mAdapter.loadMoreComplete();
        }else {
            mAdapter.loadMoreEnd();
        }
    }

    private void setRecyclerLinstener(){
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.e("dandy","滑动到底部");
                mPageNum++;
                mPresenter.getHistoryOrder(String.valueOf(mPageNum));
            }
        },mHistoryOrder);
    }

    @Override
    public void onRefresh() {

    }
}
