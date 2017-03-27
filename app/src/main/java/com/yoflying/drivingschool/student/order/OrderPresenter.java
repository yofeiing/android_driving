package com.yoflying.drivingschool.student.order;

import android.util.Log;

import com.google.gson.Gson;
import com.yoflying.drivingschool.app.DriverApplication;
import com.yoflying.drivingschool.base.BasePresenter;
import com.yoflying.drivingschool.entity.HttpsResult;
import com.yoflying.drivingschool.retrofit.ApiCallBack;
import com.yoflying.drivingschool.student.bean.OrderInfo;
import com.yoflying.drivingschool.student.bean.OrderTime;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**学员预约控制器 {@link com.yoflying.drivingschool.student.ui.OrderFragment}
 * Created by yaojiulong on 2017/1/7.
 */

public class OrderPresenter extends BasePresenter<IOrderView>{
    private IOrderView mOrderView;
    private String mToken;
    private Map<String,List<OrderInfo.DataBean>> mMapData;
    private List<String> mTimeList;
    private DriverApplication mApplication;

    public OrderPresenter(IOrderView mOrderView) {
        this.mOrderView = mOrderView;
        attachView(mOrderView);
        mApplication= (DriverApplication) DriverApplication.getContextObject();

    }

    public void getOrderInfo(){
        ApiCallBack<OrderInfo> subscriber=new ApiCallBack<OrderInfo>() {
            @Override
            public void onSuccess(OrderInfo model) {
                Log.e("dandy","成功了"+model.toString());
                mOrderView.getDataSuccess(model);
                disposeData(model);

            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onFinish() {

            }
        };

        addSubscription(mApiStore.getOrderInfo(),subscriber);
    }

    /**
     * 处理数据
     * @param data
     */
    private void disposeData(OrderInfo data){
        Gson gson=new Gson();
        OrderTime orderTime;
        mTimeList=new ArrayList<>();
        List<OrderInfo.DataBean> dataBeanList=data.getData();
        mMapData=new HashMap<>();
        List<OrderInfo.DataBean> newList=new ArrayList<>();
        for (OrderInfo.DataBean dataBean : dataBeanList) {
            orderTime=gson.fromJson(dataBean.getAppointmentDate(),OrderTime.class);
            String time =orderTime.getTime().getStart();
            newList=mMapData.get(time);
            if (newList==null){
                newList=new ArrayList<>();

            }
            newList.add(dataBean);
            mMapData.put(time,newList);

        }
        for (String key : mMapData.keySet()){
           // mTimeList.add()
            mTimeList.add(key);
        }
        mOrderView.showTimeTag(mTimeList);
        mOrderView.getDataMap(mMapData);
        Log.e("dandy","map  "+mTimeList.toString());


    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {

        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);

        return res;
    }

    /**
     * 预约单个课程
     * @param bean
     */
    public void orderCourse(OrderInfo.DataBean bean){
        //Log.e("dandy","userinfo " +mApplication.getPerson().toString());
        mOrderView.showDialog();
        JSONObject object=new JSONObject();
        try {
            object.put("id",bean.getId());
            object.put("studentsId",mApplication.getPerson().getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiCallBack<HttpsResult> subscriber=new ApiCallBack<HttpsResult>() {
            @Override
            public void onSuccess(HttpsResult model) {
                Log.i("dandy","预约成功 "+model.toString());
                mOrderView.cancelDialog();
                mOrderView.toastMeassager(model.getMessage());
            }

            @Override
            public void onFailure(String msg) {
                Log.e("dandy","预约失败 "+msg);
                mOrderView.cancelDialog();
            }

            @Override
            public void onFinish() {

            }
        };
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),object.toString());
        addSubscription(mApiStore.orderCourse(body),subscriber);

    }




}
