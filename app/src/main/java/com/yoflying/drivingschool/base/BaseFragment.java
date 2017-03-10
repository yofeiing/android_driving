package com.yoflying.drivingschool.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoflying.drivingschool.ui.HomeActivity;
import com.yoflying.drivingschool.view.ColoredSnackbar;

/**
 * Created by yaojiulong on 2016/12/29.
 */

public abstract class BaseFragment extends android.support.v4.app.Fragment{
    protected Activity mActivity;
    private ProgressDialog mDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        View view = initView(inflater,container);
        initFindViewById(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        setLinstener();
    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container);
    protected abstract void initFindViewById(View view);

    public void initData(){

    }

    public void setLinstener(){

    }

    public void showProgressDialog(){
        mDialog=ProgressDialog.show(getContext(),"提示","请稍等...",false);
    }

    public void dimssDialog(){
        if (mDialog!=null)
            mDialog.dismiss();
    }

    /**
     * 显示Snackbar
     * @param msg 内容
     */
    public void showSnackView(String msg){
       /* Snackbar.make(view,msg,Snackbar.LENGTH_LONG)
                .setAction("Action",null)
                .show();*/
        Snackbar snackbar = Snackbar.make(getView(), msg, Snackbar.LENGTH_SHORT);
        ColoredSnackbar.alert(snackbar).show();
    }



}
