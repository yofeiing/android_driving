package com.yoflying.drivingschool.admin.configmange.ui;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.admin.adapter.OneDayCourseConfigAdapter;
import com.yoflying.drivingschool.admin.listener.ManualDialogListener;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.entity.CourseConfig;

import java.util.List;
import java.util.Map;

/**
 * Created by yaojiulong on 2017/2/28.
 */

public class ManualConfigFrament extends DialogFragment implements View.OnClickListener{

    private RecyclerView mConfigsView;
    private TextView mCancel,mSubmit;
    private Map<String,List<CourseConfig>> mData;
    private OneDayCourseConfigAdapter mAdapter;
    private List<CourseConfig> mDataList;
    private String mKey;
    private ManualDialogListener mListener;
    private int mPos;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        mData= (Map<String, List<CourseConfig>>) args.getSerializable(Config.KEY_COURSE_CONFIG);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置宽度
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.dialog_manual_config,container);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mConfigsView= (RecyclerView) view.findViewById(R.id.dialog_manual_rlv);
        mCancel= (TextView) view.findViewById(R.id.dialog_manual_cancel);
        mCancel.setOnClickListener(this);
        mSubmit= (TextView) view.findViewById(R.id.dialog_manual_submit);
        mSubmit.setOnClickListener(this);
        mConfigsView.setLayoutManager(new LinearLayoutManager(getContext()));
        initData();
        
    }

    private void initData() {
        Log.e("dandy","dialog "+mData.toString());
        for (String key : mData.keySet()){
            mDataList=mData.get(key);
            mKey=key;

        }
        Log.e("dandy","list size "+mDataList.size());
        mAdapter=new OneDayCourseConfigAdapter(mDataList);
        mConfigsView.setAdapter(mAdapter);
        initRecyclerView();
    }

    private void initRecyclerView(){
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(mConfigsView);
        // 开启滑动删除
        mAdapter.enableSwipeItem();
        mAdapter.setOnItemSwipeListener(onItemSwipeListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_manual_cancel:
                dismiss();
                break;
            case R.id.dialog_manual_submit:
                mPos=Integer.valueOf(getTag());
                mData.put(mKey,mDataList);

                mListener= (ManualDialogListener) getActivity();
                mListener.onDialogListener(mData,mPos);
                dismiss();
                break;
        }
    }

    OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
        @Override
        public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
            Log.e("dandy","onItemSwipeStart  "+pos);
        }
        @Override
        public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
            Log.e("dandy","clearView  "+pos);


        }
        @Override
        public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
            Log.e("dandy","onItemSwiped  "+pos);
           //  mDataList.remove(pos);
        }

        @Override
        public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

        }
    };
}
