package com.yoflying.drivingschool.admin.addleave;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.yoflying.drivingschool.app.DriverApplication;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.admin.adapter.BindTeachersAdapter;
import com.yoflying.drivingschool.admin.Inputtime.InputTimeFragment;
import com.yoflying.drivingschool.admin.Inputtime.InputTimeListener;

import com.yoflying.drivingschool.base.BaseActivity;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.entity.Person;

import java.util.List;

/**添加教练请假页面
 * Created by yaojiulong on 2017/2/5.
 */

public class AddLeaveActivity extends BaseActivity implements View.OnClickListener ,ILeaveView,InputTimeListener{
    private TextView mTeacherName,mStartTime,mEndTime;
    private PopupWindow mPopWindow;
    private RecyclerView mTeachersView;
    private BindTeachersAdapter mBindAdapter;
    private List<Person> mTeachers;
    private DriverApplication mApplication;
    private LeavePresenter mPresenter;
    private InputTimeFragment mInputTimeFragment;
    private Button mSubmit;
    private int mTeacherId,mTeacherStatus;
    private RadioGroup mStartGroup,mEndGroup;
    private RadioButton mStartMorning,mStartAfternoon,mEndMorning,mEndAfternoon;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_add_leave);
        addToolbar();
        mApplication= (DriverApplication) getApplication();
        mPresenter=new LeavePresenter(this);
    }

    @Override
    protected void findViewId() {
        super.findViewId();
        mTeacherName=findView(R.id.add_leave_teacher_name_et);
        mStartTime=findView(R.id.add_leave_start_time);
        mEndTime=findView(R.id.add_leave_end_time);
        mSubmit=findView(R.id.add_leave_submit_btn);
        mStartGroup=findView(R.id.add_leave_start_time_group);
        mEndGroup=findView(R.id.add_leave_end_time_group);
        mStartMorning=findView(R.id.add_leave_start_time_morning);
        mStartAfternoon=findView(R.id.add_leave_start_time_afternoon);
        mEndMorning=findView(R.id.add_leave_end_time_morning);
        mEndAfternoon=findView(R.id.add_leave_end_time_afternoon);
    }

    @Override
    protected void initData() {
        super.initData();

        if (mTeachers==null){
            mPresenter.getTeachersInfo();
        }
        mStartMorning.setChecked(true);
        mEndMorning.setChecked(true);

    }

    @Override
    protected void setLinstener() {
        super.setLinstener();
        mTeacherName.setOnClickListener(this);
        mStartTime.setOnClickListener(this);
        mEndTime.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
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
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_leave_teacher_name_et:
                showTeachersView();
                break;
            case R.id.add_leave_start_time:
                mInputTimeFragment =new InputTimeFragment();
                mInputTimeFragment.show(getSupportFragmentManager(), Config.TAG_FRAGMENT_START_TIME);
                break;
            case R.id.add_leave_end_time:
                mInputTimeFragment =new InputTimeFragment();
                mInputTimeFragment.show(getSupportFragmentManager(),Config.TAG_FRAGMENT_END_TIME);
                break;
            case R.id.add_leave_submit_btn:
                    mPresenter.addLeave();
                break;
        }
    }

    /**
     *显示选择教练 PopubWindow
     */
    private void showTeachersView(){
        View popView=this.getLayoutInflater().inflate(R.layout.pop_bind_layout,null);
        mPopWindow=new PopupWindow(popView,150, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        mTeachersView= (RecyclerView) popView.findViewById(R.id.bind_teachers_rlv);
        mTeachersView.setLayoutManager(new LinearLayoutManager(this));
        mBindAdapter=new BindTeachersAdapter(mTeachers);
        mTeachersView.setAdapter(mBindAdapter);

        mPopWindow.setTouchable(true);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setFocusable(true);
        mPopWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        mPopWindow.showAsDropDown(mTeacherName, 0, 20);

        mTeachersView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.item_bind_teacher_name:
                        mTeacherId=mTeachers.get(position).getCoachId();
                        mTeacherStatus=mTeachers.get(position).getStatus();
                        mPresenter.setTeacherName(mTeachers.get(position).getName());
                        break;
                }
            }
        });
    }

    @Override
    public void getTeachersInfo(List<Person> list) {
        mTeachers=list;
    }

    @Override
    public void setTeacherName(String name) {
        mTeacherName.setText(name);
        mPopWindow.dismiss();
    }

    @Override
    public int getTeacherId() {
        return mTeacherId;
    }

    @Override
    public String getStartTime() {
        if (mStartGroup.getCheckedRadioButtonId()==mStartMorning.getId()){
            return mStartTime.getText().toString()+" 10:00";
        }else {
            return mStartTime.getText().toString()+" 14:00";
        }

    }

    @Override
    public String getEndTime() {
        if (mEndGroup.getCheckedRadioButtonId() == mEndMorning.getId()){
            return mEndTime.getText().toString()+" 10:00";
        }else {
            return mEndTime.getText().toString()+" 14:00";
        }


    }

    @Override
    public int getStatus() {
        return mTeacherStatus;
    }

    @Override
    public String getTeacherName() {
        return mTeacherName.getText().toString();
    }


    @Override
    public void onGetInputStartTime(String time) {
        Log.e("dandy","获取到了时间 "+time);
        mInputTimeFragment.dismiss();
        mStartTime.setText(time);
        mStartTime.setTextColor(Color.BLACK);

    }

    @Override
    public void onGetInputEndTime(String time) {
        mInputTimeFragment.dismiss();
        mEndTime.setText(time);
        mEndTime.setTextColor(Color.BLACK);
    }

    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void cancelDialog() {
        dimssDialog();
    }

    @Override
    public void toastMeassager(String msg) {
        View rootView=getWindow().getDecorView();
        showSnackView(rootView,msg);
        mTeacherName.setText("");
        mStartTime.setText("");
        mEndTime.setText("");
    }
}
