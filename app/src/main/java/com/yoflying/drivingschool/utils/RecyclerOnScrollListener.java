package com.yoflying.drivingschool.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by yaojiulong on 2016/12/27.
 */

public abstract class RecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    private int mPreviousTotal = 0;
    private boolean mLoading = true;
    int mFirstVisibleItem, mVisibleItemCount, mTotalItemCount;//第一个item，显示的item总数，整个item总数

    private int currentPage = 1;

    private LinearLayoutManager mLinearLayoutManager;

    public RecyclerOnScrollListener(
            LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        mVisibleItemCount = recyclerView.getChildCount();
        mTotalItemCount = mLinearLayoutManager.getItemCount();
        mFirstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (mLoading) {
            if (mTotalItemCount > mPreviousTotal) {
                mLoading = false;
                mPreviousTotal = mTotalItemCount;
            }
        }

        if (!mLoading
                && (mTotalItemCount - mVisibleItemCount) <= mFirstVisibleItem) {
            currentPage++;
            onLoadMore(currentPage);
            mLoading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);
}
