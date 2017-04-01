package com.yoflying.drivingschool.student.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.base.BaseActivity;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.student.history.HistoryOrderFragment;
import com.yoflying.drivingschool.student.mycourse.MyCourseFragment;
import com.yoflying.drivingschool.student.myteacher.MyTeacherFragment;
import com.yoflying.drivingschool.student.protocol.MyProtocolFragment;

/**学员个人 二级页面activity（承载多个fragment）
 * Created by yaojiulong on 2017/3/28.
 */

public class StudentInfoActivity extends BaseActivity {
    private FrameLayout mContactLayout;
    private Intent mIntent;
    private HistoryOrderFragment mHistoryFragment;
    private MyCourseFragment mCourseFragment;
    private MyTeacherFragment mMyTeacherFragment;
    private MyProtocolFragment mProtocolFragment;
    private int mType;
    private FragmentManager mManager;
    private android.support.v4.app.FragmentTransaction mTransaction;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_studentinfo);
        addToolbar();
    }

    @Override
    protected void initData() {
        super.initData();
        mIntent=getIntent();
        mType=mIntent.getExtras().getInt(Config.KEY_STUDENT_FRAGMENT_TYPE);
        mManager=getSupportFragmentManager();
            mTransaction=mManager.beginTransaction();
        switch (mType){
            case Config.TYPE_STUDENT_TEACHER:
                if (mMyTeacherFragment==null){
                    mMyTeacherFragment=new MyTeacherFragment();
                }
                mTransaction.replace(R.id.student_contact_layout,mMyTeacherFragment);
                mTransaction.commit();
                setTitle(getResources().getString(R.string.student_my_teacher));
                break;
            case Config.TYPE_STUDENT_COURSE:
                if (mCourseFragment==null){
                    mCourseFragment=new MyCourseFragment();
                }
                mTransaction.replace(R.id.student_contact_layout,mCourseFragment);
                mTransaction.commit();
                setTitle(getResources().getString(R.string.student_my_course));

                break;
            case Config.TYPE_STUDENT_HISTORY:
                if (mHistoryFragment==null){
                    mHistoryFragment=new HistoryOrderFragment();
                }
                mTransaction.replace(R.id.student_contact_layout,mHistoryFragment);
                mTransaction.commit();
                setTitle(getResources().getString(R.string.student_order_history));
                break;
            case Config.TYPE_STUDENT_PROTOCOL:
                if (mProtocolFragment==null){
                    mProtocolFragment=new MyProtocolFragment();
                }
                mTransaction.replace(R.id.student_contact_layout,mProtocolFragment);
                mTransaction.commit();
                setTitle(getResources().getString(R.string.student_protocol));
                break;

        }
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
}
