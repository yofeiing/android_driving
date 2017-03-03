package com.yoflying.drivingschool.admin.configmange.ui;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yoflying.drivingschool.DriverApplication;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.admin.Inputtime.InputTimeFragment;
import com.yoflying.drivingschool.admin.Inputtime.InputTimeListener;
import com.yoflying.drivingschool.admin.adapter.CourseConfigAdapter;
import com.yoflying.drivingschool.admin.configmange.Iview.IChoiseTimeView;
import com.yoflying.drivingschool.admin.configmange.presenter.ChoiseTimePresenter;
import com.yoflying.drivingschool.admin.select.SelectTeacherFragment;
import com.yoflying.drivingschool.base.BaseActivity;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.entity.CourseConfig;
import com.yoflying.drivingschool.entity.CourseTimeBean;
import com.yoflying.drivingschool.entity.Person;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**一天手动选择配置页面
 * Created by yaojiulong on 2017/2/20.
 */

public class ChoiseTimeActivity extends BaseActivity
        implements InputTimeListener ,View.OnClickListener,IChoiseTimeView,CourseConfigAdapter.GetItemSomeInfo
        ,SelectTeacherFragment.GetTeacherLinstener{
    private TextView mDate;
    private ImageView mAddItem,mMenuRight;
    private RecyclerView mConfigView;
    private InputTimeFragment mInputTimeFragment;
    private ChoiseTimePresenter mPresenter;
    private List<CourseConfig> mCourses;
    private CourseConfigAdapter mAdapter;
    public List<Person> mTeachers;
    private DriverApplication mApplication;
    private List<String> mTeacherNames;
    private TextView mAddDate;
    private Gson mGson;
    private Map<String,List<CourseConfig>> mConfigMap;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_choise_time);
        addToolbar();
        setTitle(getResources().getString(R.string.auto_choise_time_title));
        mMenuRight=getToolbarmenu();
        mMenuRight.setImageResource(R.drawable.icon_submit);
        mPresenter=new ChoiseTimePresenter(this);
    }

    @Override
    protected void findViewId() {
        super.findViewId();
        mAddDate=findView(R.id.choise_time_add_date);
        mAddItem=findView(R.id.choise_time_add_item);
        mConfigView=findView(R.id.choise_time_list);
        mDate=findView(R.id.choie_time_date_tv);
        //添加下划线
        mAddDate.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

    }

    @Override
    protected void initData() {
        super.initData();
        mApplication= (DriverApplication) getApplication();
        mCourses=new ArrayList<>();
        mTeacherNames=new ArrayList<>();
        mAdapter=new CourseConfigAdapter(this,mCourses,mTeacherNames);
        mConfigView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mConfigView.setAdapter(mAdapter);
        mPresenter.getTeachersInfo();
        mGson=new Gson();

    }

    @Override
    protected void setLinstener() {
        super.setLinstener();
        mAddDate.setOnClickListener(this);
        mAddItem.setOnClickListener(this);
        mMenuRight.setOnClickListener(this);
    }

    @Override
    protected String[] getNeedPermissions() {
        return new String[0];
    }

    @Override
    protected void permissionGrantedSuccess() {

    }

    @Override
    protected void permissionGrantedFail() {

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

    }

    @Override
    public void onGetInputStartTime(String time) {
        mDate.setText(time);
        mInputTimeFragment.dismiss();
    }

    @Override
    public void onGetInputEndTime(String time) {

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choise_time_add_date:
                if (mInputTimeFragment==null){
                    mInputTimeFragment=new InputTimeFragment();
                }
                mInputTimeFragment.show(getSupportFragmentManager(), Config.TAG_FRAGMENT_START_TIME);
                break;
            case R.id.choise_time_add_item:

                if (mDate.getText().toString().equals("")){
                    showSnackView(getView(),"日期还没有添加，请先添加日期");
                    return;
                }

                if (mCourses.size()<7){
                    CourseConfig config=setDefaultData();

                    mTeacherNames.add("");
                    mCourses.add(config);
                    mAdapter.notifyDataSetChanged();
                }
                if (mCourses.size()==7){
                    mAddItem.setVisibility(View.GONE);
                }
                break;

            case R.id.toolbar_right_menu:
                mConfigMap=new HashMap<>();
                mConfigMap.put(mDate.getText().toString(),mCourses);
                Intent intent=new Intent(this,ManualConfigActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable(Config.KEY_BUNDLE_CONFIG, (Serializable) mConfigMap);
                intent.putExtra(Config.KEY_INTENT_CONFIG,bundle);
                setResult(Config.MANULA_REQUEST_CODE,intent);
                this.finish();
                break;

        }
    }


    /**
     * 设置默认数据
     * @return
     */
    private CourseConfig setDefaultData(){
        CourseConfig config=new CourseConfig();

        CourseTimeBean bean=new CourseTimeBean();
        CourseTimeBean.TimeBean timeBean=new CourseTimeBean.TimeBean();
        bean.setSize(10);
        timeBean.setStart(mDate.getText().toString()+" "+"09:00"+":00");
        timeBean.setStop(mDate.getText().toString()+" "+"11:00"+":00");
        bean.setTime(timeBean);
        Gson gson=new Gson();
        config.setAppointmentDate(gson.toJson(bean));
        config.setTestAddress("民乐");
        config.setStatus(1);

        return config;
    }

    @Override
    public void getCourse(int position, int course) {

        course=course+2;
        mCourses.get(position).setTestCourse(course);
    }

    /**
     * 回调获取recyclerview里面的人数
     * @param positon
     * @param size
     */
    @Override
    public void getNumber(int positon, String size) {
        // Log.e("dandy","postion "+positon+"  size "+size);
        if (!size.equals("")){
            int s=Integer.valueOf(size);
            // mCourses.get(positon).getAppointmentDate().setSize(s);
            CourseTimeBean bean=mGson.fromJson(mCourses.get(positon).getAppointmentDate(),CourseTimeBean.class);
            bean.setSize(s);
            mCourses.get(positon).setAppointmentDate(mGson.toJson(bean));

        }

    }

    @Override
    public void getStartTime(int postion, String time) {
        CourseTimeBean timeBean=mGson.fromJson(mCourses.get(postion).getAppointmentDate(),CourseTimeBean.class);
        timeBean.getTime().setStart(mDate.getText().toString()+" "+time+":00");
        mCourses.get(postion).setAppointmentDate(mGson.toJson(timeBean));
        Log.e("dandy","log "+mCourses.get(postion).toString());
        mAdapter.notifyDataSetChanged();


    }

    @Override
    public void getEndTime(int postion, String time) {
        CourseTimeBean timeBean=mGson.fromJson(mCourses.get(postion).getAppointmentDate(),CourseTimeBean.class);
        timeBean.getTime().setStop(mDate.getText().toString()+" "+time+":00");
        mCourses.get(postion).setAppointmentDate(mGson.toJson(timeBean));
        Log.e("dandy","log "+mCourses.get(postion).toString());
        mAdapter.notifyDataSetChanged();

    }

    /**
     * 网络请求获取驾校全部教练
     * @param teachers
     */
    @Override
    public void getTeachersInfo(List<Person> teachers) {
        mTeachers=teachers;
        mApplication.setTeachersList(mTeachers);
        // Log.e("dandy","数据过来了 "+mTeachers.toString());
    }

    @Override
    public void stopRefresh() {

    }

    @Override
    public void getTeacherinfo(int postion, int teacherpos) {
        Person person=mTeachers.get(teacherpos);
        mTeacherNames.set(postion,person.getName());
        mCourses.get(postion).setCoachId(person.getId());
        mAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("dandy","onResume");
    }
}
