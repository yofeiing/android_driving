package com.yoflying.drivingschool.admin.adapter;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.admin.select.SelectTeacherFragment;
import com.yoflying.drivingschool.base.BaseActivity;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.entity.CourseConfig;
import com.yoflying.drivingschool.entity.CourseTimeBean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.http.POST;


/**单个课程配置适配器
 * Created by yaojiulong on 2017/2/22.
 */

public class CourseConfigAdapter  extends RecyclerView.Adapter<CourseConfigAdapter.CourseHolder>{
    private Context mContext;
    private List<CourseConfig> mCourseconfigs;
    private String[] mCourses;
    private GetItemSomeInfo mListener;
    private int year, monthOfYear, dayOfMonth, hourOfDay, minute;
    private SelectTeacherFragment mSelectFragment;
    private BaseActivity mActivity;
    private List<String> mTeacherNames;
    private Gson gson;

    public CourseConfigAdapter(BaseActivity activity, List<CourseConfig> mCourseconfigs,List<String> teachernames ) {
        //  this.mContext = mContext;
        this.mCourseconfigs = mCourseconfigs;

        this.mActivity=activity;
        this.mTeacherNames=teachernames;
        mListener= (GetItemSomeInfo) mActivity;
        gson=new Gson();
    }

    public interface GetItemSomeInfo{
        void getCourse(int position ,int course);
        void getNumber(int positon ,String size);
        void getStartTime(int postion,String time);
        void getEndTime(int postion,String time);
    }


    @Override
    public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CourseHolder holder=new CourseHolder(LayoutInflater.from(mActivity).inflate(R.layout.item_choise_time,parent,false));

        return holder;
    }

    @Override
    public void onBindViewHolder(CourseHolder holder, final int position) {

        if (gson==null){
            gson=new Gson();
        }
        CourseTimeBean bean=gson.fromJson(mCourseconfigs.get(position).getAppointmentDate(),CourseTimeBean.class);
       // Log.e("dandy","适配器 "+mCourseconfigs.get(position).toString());
        holder.mStartTime.setText(getTime(bean.getTime().getStart()));
        holder.mEndTime.setText(getTime(bean.getTime().getStop()));
        holder.mNum.setText(String.valueOf(bean.getSize()));
        holder.mNum.addTextChangedListener(new TextSwitcher(holder));
        holder.mNum.setTag(position);
        holder.mTeacher.setText(mTeacherNames.get(position));
        final int s=position;
        holder.mSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mListener.getCourse(s,position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        holder.mStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickDialog(position,Config.TAPY_START_TIME);
            }
        });

        holder.mEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickDialog(position,Config.TAPY_END_TIME);
            }
        });

        holder.mTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectFragment==null){
                    mSelectFragment=new SelectTeacherFragment();

                }
                Log.e("dandy","pos "+position);
                mSelectFragment.show(mActivity.getSupportFragmentManager(),""+String.valueOf(position));
            }
        });


    }

    /**
     * 字符串截取，只获取后面的时间
     * @param str
     * @return
     */
    private String getTime(String str){

        String a[] = str.split(" ");
        String r=a[1].toString().substring(0,5);
        return r;

    }


    @Override
    public int getItemCount() {
        return mCourseconfigs ==null ? 0 :mCourseconfigs.size();
    }

    private void showTimePickDialog(final int postion, final int type){
        TimePickerDialog dialog=new TimePickerDialog(mActivity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String hour="";
                String m="";
                if (hourOfDay < 10){
                    hour="0"+String.valueOf(hourOfDay);
                }else {
                    hour=String.valueOf(hourOfDay);
                }
                if (minute < 10){
                    m="0"+String.valueOf(minute);
                }else {
                    m=String.valueOf(minute);
                }

                String time=hour+":"+String.valueOf(m);
                switch (type){
                    case Config.TAPY_START_TIME:
                        mListener.getStartTime(postion,time);
                        break;
                    case Config.TAPY_END_TIME:
                        mListener.getEndTime(postion,time);
                        break;
                }
            }
        },hourOfDay,minute,true);
        dialog.show();
    }


    public class CourseHolder extends RecyclerView.ViewHolder{
        private TextView mStartTime,mEndTime;
        private AppCompatSpinner mSubject;
        private TextView mTeacher;
        private EditText mNum;

        public CourseHolder(View itemView) {
            super(itemView);
            mStartTime= (TextView) itemView.findViewById(R.id.choise_time_start_time);
            mEndTime= (TextView) itemView.findViewById(R.id.choise_time_end_time);
            mSubject= (AppCompatSpinner) itemView.findViewById(R.id.item_choise_time_course);
            mTeacher= (TextView) itemView.findViewById(R.id.item_choise_teacher);
            mNum= (EditText) itemView.findViewById(R.id.item_choise_time_number);
        }
    }

    class TextSwitcher implements TextWatcher{

        private CourseHolder mHolder;

        public TextSwitcher(CourseHolder mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (s!=null){
                mListener.getNumber(Integer.parseInt(mHolder.mNum.getTag().toString()),s.toString());
            }
        }
    }
}