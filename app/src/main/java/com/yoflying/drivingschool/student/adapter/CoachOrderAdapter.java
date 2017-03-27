package com.yoflying.drivingschool.student.adapter;

import android.os.Handler;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.student.bean.OrderInfo;
import com.yoflying.drivingschool.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**可预约课程适配器
 * Created by PLED on 2017/3/3.
 */

public class CoachOrderAdapter extends BaseQuickAdapter<OrderInfo.DataBean,BaseViewHolder>{


    public CoachOrderAdapter(List<OrderInfo.DataBean> data) {
        super(R.layout.order_list_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderInfo.DataBean item) {
        helper.setText(R.id.order_address,item.getTestAddress());
        try {
            JSONObject object = new JSONObject(item.getAppointmentDate());
            helper.setText(R.id.order_num,object.getString("size"));
            JSONObject jsonObject = object.getJSONObject("time");
            helper.setText(R.id.order_start_time,jsonObject.getString("start"));
            helper.setText(R.id.order_end_time,jsonObject.getString("stop"));

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


}
