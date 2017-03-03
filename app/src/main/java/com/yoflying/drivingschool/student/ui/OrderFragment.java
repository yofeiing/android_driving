package com.yoflying.drivingschool.student.ui;

import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.admin.adapter.CoachOrderAdapter;
import com.yoflying.drivingschool.base.BaseFragment;
import com.yoflying.drivingschool.student.bean.OrderInfo;
import com.yoflying.drivingschool.student.order.IOrderView;
import com.yoflying.drivingschool.student.order.OrderPresenter;
import com.yoflying.drivingschool.utils.LogUtil;

/**学生预约
 * Created by yaojiulong on 2017/1/4.
 */

public class OrderFragment extends BaseFragment implements IOrderView {
    private OrderPresenter mOrderPresenter;
    private RecyclerView mOrderList;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_order_student,container,false);
    }

    @Override
    protected void initFindViewById(View view) {
        mOrderList = (RecyclerView) view.findViewById(R.id.order_list);

    }

    @Override
    public void initData() {
        super.initData();
        mOrderPresenter=new OrderPresenter(this);
        mOrderPresenter.getOrderInfo();
    }

    @Override
    public void getDataSuccess(OrderInfo info) {
        LogUtil.e(this,info.toString());
        CoachOrderAdapter adapter = new CoachOrderAdapter(info.getData());
        mOrderList.setLayoutManager(new LinearLayoutManager(mActivity));
        mOrderList.setAdapter(adapter);


    }
}
