package com.yoflying.drivingschool.student.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.student.bean.HistoryRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**历史预约记录适配器
 * Created by yaojiulong on 2017/4/1.
 */

public class HistoryOrderAdapter extends BaseQuickAdapter<HistoryRecord,BaseViewHolder> {
    private SimpleDateFormat mFormat;


    public HistoryOrderAdapter(List<HistoryRecord> data) {
        super(R.layout.item_student_history_order,data);
    }


    @Override
    protected void convert(BaseViewHolder helper, HistoryRecord item) {
        switch (item.getTestCourse()){
            case 1:
                helper.setText(R.id.item_student_history_course,"科目一");
                break;
            case 2:
                helper.setText(R.id.item_student_history_course,"科目二");
                break;
            case 3:
                helper.setText(R.id.item_student_history_course,"科目三");
                break;
        }
        helper.setText(R.id.item_student_history_time,getTime(item.getAppointmentDate()));




    }

    private String getTime(long lt){
        String time="";
        if (mFormat ==null){
            mFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }

        Date date = new Date(lt);
        time=mFormat.format(date);


        return time;
    }
}
