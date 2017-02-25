package com.yoflying.drivingschool.base;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.adapter.TabAdapter;

import java.util.List;
import java.util.logging.XMLFormatter;

/**
 * Created by yaojiulong on 2017/1/7.
 */

public class BaseHomeFragment extends BaseFragment {
    private ViewPager mContentPager;
    private TabLayout mTab;
    private List<String> mTitles;
    private List<BaseFragment> mFragments;
    private TabAdapter mAdapter;


    @Override
    protected void initFindViewById(View view) {
        mContentPager= (ViewPager) view.findViewById(R.id.viewpager_base);
        mTab= (TabLayout) view.findViewById(R.id.tab_base);
        mContentPager.setOffscreenPageLimit(3);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_base,container,false);

        return view;
    }


    @Override
    public void initData() {
        super.initData();
        initFragment();
    }

    private void initFragment(){

        mAdapter=new TabAdapter(getChildFragmentManager(),mTitles,mFragments);
        mContentPager.setAdapter(mAdapter);
        mTab.setupWithViewPager(mContentPager);
    }

    public void setData(List<BaseFragment> fragments,List<String> titles){
      this.mFragments=fragments;
      this.mTitles=titles;
    }





}
