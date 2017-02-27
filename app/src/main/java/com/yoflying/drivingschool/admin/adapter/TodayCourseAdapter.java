package com.yoflying.drivingschool.admin.adapter;

import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.entity.TodayCourse;

import java.text.SimpleDateFormat;
import java.util.List;

/**今日课程适配器
 * Created by yaojiulong on 2017/1/18.
 */

public class TodayCourseAdapter extends BaseQuickAdapter<TodayCourse,BaseViewHolder> {
    public TodayCourseAdapter(List<TodayCourse> data) {
        super(R.layout.item_today_course,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TodayCourse item) {
        helper.setText(R.id.item_course_content_tv,String.valueOf(item.getTestCourse()));
        //helper.setText(R.id.item_course_teacher_name_tv,item.getCoachId());

        Gson gson=new Gson();
        TodayCourse.AppointmentDate time=new TodayCourse.AppointmentDate();
        time=gson.fromJson(item.getAppointmentDate(), TodayCourse.AppointmentDate.class);

        String starttime=getTime(time.getTime().getStart().toString());
        String endtime=getTime(time.getTime().getStop().toString());
        helper.setText(R.id.item_course_time_tv,starttime+" "+endtime);

    }





    private String getTime(String str){

        String a[] = str.split(" ");
        //Log.e("dandy","截取出来的时间 "+a[1]);

        return a[1];

    }
}
