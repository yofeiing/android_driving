package com.yoflying.drivingschool.student.adapter;

import android.os.Handler;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.JsonArray;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.student.bean.OrderInfo;
import com.yoflying.drivingschool.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**可预约课程适配器
 * Created by PLED on 2017/3/3.
 */

public class CoachOrderAdapter extends BaseQuickAdapter<OrderInfo.DataBean,BaseViewHolder>{
    private String[] mIds;

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
            if (item.getStudentsIds()!=null){

                String s= (String) item.getStudentsIds();

                JSONArray array=new JSONArray(s);

                int num=object.getInt("size")-array.length();
               // Log.e("dandy","array  "+num);
                helper.setText(R.id.remain_num,String.valueOf(num));
                if (array.length()>0){
                    Log.e("dandy","l  "+array.length());
                    for (int i=0;i<array.length();i++){
                        Log.e("dandy","i ");
                    }
                }


            }else {
                helper.setText(R.id.remain_num,object.getString("size"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


}
