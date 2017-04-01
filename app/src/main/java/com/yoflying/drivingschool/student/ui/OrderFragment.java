package com.yoflying.drivingschool.student.ui;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.admin.Inputtime.InputTimeFragment;
import com.yoflying.drivingschool.admin.Inputtime.InputTimeListener;
import com.yoflying.drivingschool.app.DriverApplication;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.retrofit.ApiCallBack;
import com.yoflying.drivingschool.student.adapter.CoachOrderAdapter;
import com.yoflying.drivingschool.base.BaseFragment;
import com.yoflying.drivingschool.student.adapter.TimeChooseAdapter;
import com.yoflying.drivingschool.student.bean.OrderInfo;
import com.yoflying.drivingschool.student.order.IOrderView;
import com.yoflying.drivingschool.student.order.OrderPresenter;
import com.yoflying.drivingschool.utils.LogUtil;
import com.yoflying.drivingschool.utils.TimeUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**学生预约界面
 * Created by yaojiulong on 2017/1/4.
 */

public class OrderFragment extends BaseFragment implements IOrderView ,View.OnClickListener{
    private static final Logger logger = LoggerFactory.getLogger(OrderFragment.class);
    private OrderPresenter mOrderPresenter;
    private RecyclerView mOrderList;
    private ImageView mChoiceTime;
    private InputTimeFragment mInputTimeFragment;
    public static InputTimeListener mInputListener;
    public RecyclerView mTimeChoose;
    private List<String> mTimeList;
    private Map<String,List<OrderInfo.DataBean>> mDataMap;
    CoachOrderAdapter mAdapter;
    private List<OrderInfo.DataBean> mInfoList=new ArrayList<>();
    private TimeChooseAdapter mTimeAdapter;
    private List<Boolean> mIsVisible;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_order_student,container,false);
    }

    @Override
    protected void initFindViewById(View view) {
        mOrderList = (RecyclerView) view.findViewById(R.id.order_list);

        mOrderList.setNestedScrollingEnabled(false);
        mTimeChoose= (RecyclerView) view.findViewById(R.id.order_student_time_tag);

    }

    @Override
    public void initData() {
        super.initData();
        mOrderPresenter=new OrderPresenter(this);
        mOrderPresenter.getOrderInfo();


    }

    @Override
    public void setLinstener() {
        super.setLinstener();
        if (mTimeAdapter !=null && mTimeChoose !=null){

        }
    }

    @Override
    public void getDataSuccess(OrderInfo info) {
        LogUtil.e(this,info.toString());
        mInfoList=info.getData();
        mAdapter = new CoachOrderAdapter(mInfoList, (DriverApplication) DriverApplication.getContextObject());
        mOrderList.setLayoutManager(new LinearLayoutManager(mActivity));
        mOrderList.setAdapter(mAdapter);
       // mIsVisible=mAdapter.getIsVisible();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mIsVisible=mAdapter.getIsVisible();
                if (!mIsVisible.get(position)){
                    alertOrderDialog(mInfoList.get(position));
                }

            }
        });

    }

    @Override
    public void showTimeTag(final List<String> mTimes) {
        mTimeList=mTimes;

        List<String> data = new ArrayList<>();
        for (String d : mTimes) {
            data.add(" " + TimeUtils.DateToString(TimeUtils.StringToDate(d), TimeUtils.YYYY_MM_DD));
        }
        mTimeAdapter = new TimeChooseAdapter(data);

        mTimeChoose.setLayoutManager(new GridLayoutManager(getContext(),4));
        mTimeChoose.setAdapter(mTimeAdapter);
        mTimeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<OrderInfo.DataBean> dataBeen=mDataMap.get(mTimes.get(position));

                for (int i = 0; i < adapter.getItemCount(); i++) {
                    if (i == position) {
                        view.setBackgroundColor(Color.parseColor("#f59e12"));
                    } else {
                        mTimeChoose.getChildAt(i).setBackgroundColor(Color.parseColor("#53ccf2"));
                    }
                }
                mInfoList=dataBeen;
                mAdapter.setNewData(mInfoList);
                mAdapter.clearIsVisible();
                mIsVisible=mAdapter.getIsVisible();
            }
        });

    }

    @Override
    public void getDataMap(Map<String, List<OrderInfo.DataBean>> map) {
        mDataMap=map;
    }

    @Override
    public void onClick(View v) {

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mInputListener=null;
        mOrderPresenter.detachView();
    }

    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void cancelDialog() {
        dimssDialog();
    }

    @Override
    public void toastMeassager(String msg) {
        showSnackView(msg);
    }

    private void alertOrderDialog(final OrderInfo.DataBean bean){
        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setMessage("确实要预约这次课程吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mOrderPresenter.orderCourse(bean);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog=builder.create();
        dialog.show();
    }
}
