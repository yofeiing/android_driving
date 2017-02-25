package com.yoflying.drivingschool.admin.configmange.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.yoflying.drivingschool.entity.Person;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**一天手动选择配置页面
 * Created by yaojiulong on 2017/2/20.
 */

public class ChoiseTimeActivity extends BaseActivity
        implements InputTimeListener ,View.OnClickListener,IChoiseTimeView,CourseConfigAdapter.GetItemSomeInfo
        ,SelectTeacherFragment.GetTeacherLinstener{
    private TextView mDate;
    private ImageView mAddDate,mAddItem,mMenuRight;
    private RecyclerView mConfigView;
    private InputTimeFragment mInputTimeFragment;
    private ChoiseTimePresenter mPresenter;
    private List<CourseConfig> mCourses;
    private CourseConfigAdapter mAdapter;
    public List<Person> mTeachers;
    private DriverApplication mApplication;
    private List<String> mTeacherNames;

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
                String date=mDate.getText().toString();
                String sTime;
                String eTime;
                for (CourseConfig mCourse : mCourses) {
                    sTime=date+" "+mCourse.getAppointmentDate().getTime().getStart();
                    mCourse.getAppointmentDate().getTime().setStart(sTime);
                    eTime=date+" "+mCourse.getAppointmentDate().getTime().getStop();
                    mCourse.getAppointmentDate().getTime().setStop(eTime);


                }

                Gson gson=new Gson();
                String result =gson.toJson(mCourses);
                Log.e("dandy","result "+ result);
                break;

        }
    }

    /**
     * 设置默认数据
     * @return
     */
    private CourseConfig setDefaultData(){
        CourseConfig config=new CourseConfig();
        CourseConfig.AppointmentDate date=new CourseConfig.AppointmentDate();
        CourseConfig.AppointmentDate.time time=new CourseConfig.AppointmentDate.time();

        time.setStart("09:00");
        time.setStop("12:00");
        date.setTime(time);
        config.setAppointmentDate(date);
        config.setTestAddress("民乐");
        config.setStatus(1);
        return config;
    }

    @Override
    public void getCourse(int position, int course) {
        //Log.e("dandy","选择了科目 "+(course+2)+"  pos "+position);
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
            mCourses.get(positon).getAppointmentDate().setSize(s);
        }

    }

    @Override
    public void getStartTime(int postion, String time) {

        mCourses.get(postion).getAppointmentDate().getTime().setStart(time);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getEndTime(int postion, String time) {

        mCourses.get(postion).getAppointmentDate().getTime().setStop(time);
        mAdapter.notifyDataSetChanged();
    }

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
