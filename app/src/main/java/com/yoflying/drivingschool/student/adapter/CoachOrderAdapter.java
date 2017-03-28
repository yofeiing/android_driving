package com.yoflying.drivingschool.student.adapter;

import android.os.Handler;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.app.DriverApplication;
import com.yoflying.drivingschool.student.bean.OrderInfo;
import com.yoflying.drivingschool.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**可预约课程适配器
 * Created by PLED on 2017/3/3.
 */

public class CoachOrderAdapter extends BaseQuickAdapter<OrderInfo.DataBean,BaseViewHolder>{
    private DriverApplication mApplication;
    private List<Boolean> mIsVisible;
    private boolean mIsOrded;//是否已经预约过了

    public CoachOrderAdapter(List<OrderInfo.DataBean> data,DriverApplication application) {
        super(R.layout.order_list_item,data);
        this.mApplication=application;
        mIsVisible=new ArrayList<>();
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
                Gson gson=new Gson();

                List<Integer> ids=gson.fromJson(s,new TypeToken<List<Integer>>(){}.getType());
                int num=object.getInt("size")-array.length();
                helper.setText(R.id.remain_num,String.valueOf(num));

                mIsOrded=false;
                for (Integer id : ids) {
                    if (id==mApplication.getPerson().getId()){
                        helper.setVisible(R.id.order_is_img,true);
                        mIsOrded=true;
                        break;
                    }else {
                        helper.setVisible(R.id.order_is_img,false);
                        mIsOrded = false;
                    }
                }

            }else {
                helper.setText(R.id.remain_num,object.getString("size"));
                mIsOrded=false;
            }
            mIsVisible.add(mIsOrded);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public List<Boolean> getIsVisible(){
       // Log.e("dandy","size "+mIsVisible.size());
        return mIsVisible;
    }


    public void clearIsVisible(){
        mIsVisible.clear();
    }


}
