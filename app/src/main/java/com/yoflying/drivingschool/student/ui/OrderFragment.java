package com.yoflying.drivingschool.student.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.student.adapter.CoachOrderAdapter;
import com.yoflying.drivingschool.base.BaseFragment;
import com.yoflying.drivingschool.student.bean.OrderInfo;
import com.yoflying.drivingschool.student.order.IOrderView;
import com.yoflying.drivingschool.student.order.OrderPresenter;
import com.yoflying.drivingschool.utils.LogUtil;

import java.util.Calendar;

/**学生预约界面
 * Created by yaojiulong on 2017/1/4.
 */

public class OrderFragment extends BaseFragment implements IOrderView ,View.OnClickListener{
    private OrderPresenter mOrderPresenter;
    private RecyclerView mOrderList;
    private ImageView mChoiseTime;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_order_student,container,false);
    }

    @Override
    protected void initFindViewById(View view) {
        mOrderList = (RecyclerView) view.findViewById(R.id.order_list);
        mChoiseTime= (ImageView) view.findViewById(R.id.student_order_choice_time);
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

    }


    @Override
    public void getDataSuccess(OrderInfo info) {
        LogUtil.e(this,info.toString());
        CoachOrderAdapter adapter = new CoachOrderAdapter(info.getData());
        mOrderList.setLayoutManager(new LinearLayoutManager(mActivity));
        mOrderList.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.student_order_choice_time:
        }
    }
}
