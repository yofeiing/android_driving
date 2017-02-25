package com.yoflying.drivingschool.admin.student;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yoflying.drivingschool.DriverApplication;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.admin.adapter.BindTeachersAdapter;
import com.yoflying.drivingschool.admin.adapter.StudentsManageAdapter;
import com.yoflying.drivingschool.admin.create.CreateActivity;
import com.yoflying.drivingschool.base.BaseFragment;
import com.yoflying.drivingschool.entity.BindInfo;
import com.yoflying.drivingschool.entity.Person;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.view.RecycleViewDivider;

import java.util.List;

/**学员管理界面
 * Created by yaojiulong on 2017/1/13.
 */

public class StudentManageFragment extends BaseFragment  implements IStudentManageView,View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView mStudentsRlv;
    private FloatingActionButton mAddFab;
    private SwipeRefreshLayout mRefreshLayout;
    private StudentsManageAdapter mAdapter;
    private StudentManagePresenter mPresenter;
    private PopupWindow mPopWindow;
    private View mRootView;
    private List<Person> mTeachers;//教练
    private BindTeachersAdapter mBindAdapter;

    private RecyclerView mTeachersView;
    private List<Person> mStudents;
    private DriverApplication mApplication;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_manage_student,container,false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {
        mStudentsRlv = (RecyclerView) view.findViewById(R.id.student_manage_rlv);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.student_manage_refresh_layout);
        mAddFab = (FloatingActionButton) view.findViewById(R.id.student_manage_fab);
        mPresenter = new StudentManagePresenter(this);
        mRefreshLayout.setColorSchemeResources(R.color.refresh_color_1
                , R.color.refresh_color_2, R.color.refresh_color_3, R.color.refresh_color_4);
        mAddFab.setOnClickListener(this);
        mRootView=view;
        mStudentsRlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRefreshLayout.setOnRefreshListener(this);
      //  mApplication= (DriverApplication) getActivity().getApplication();
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getStudentsInfo("1");
        if (mTeachers==null){
            mPresenter.getTeachersInfo();
        }

    }

    @Override
    public void setLinstener() {
        super.setLinstener();
        mStudentsRlv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(getActivity(),StudentInfoActivity.class);
                Person person=mStudents.get(position);
                intent.putExtra(Config.KEY_STUDENT_NAME,person.getName());
                intent.putExtra(Config.KEY_STUDENT_ID,person.getId());
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade,R.anim.hold);
            }
        });
        mStudentsRlv.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.item_student_bind_btn:
                        Person person=mStudents.get(position);

                        if (person.getCoachId()>0){
                            showUnBindDialog(person);

                        }else {
                            showPopWindow(view,person);

                        }

                        break;
                }
            }
        });
    }


    private void showUnBindDialog(final Person student){
        final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("提示");
        builder.setTitle("是否确定要解除该学员与教练的绑定");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BindInfo info=new BindInfo(student.getId(),0,student.getCourse());
                mPresenter.aboutBind(info);
            }
        });
        builder.show();
    }


    @Override
    public void showStudentsInfo(List<Person> mLists) {
        mStudents=mLists;
        mAdapter=new StudentsManageAdapter(mStudents);
        mStudentsRlv.setLayoutManager(new LinearLayoutManager(getContext()));

        mStudentsRlv.setAdapter(mAdapter);
        mStudentsRlv.addItemDecoration(new RecycleViewDivider(getContext(),RecycleViewDivider.VERTICAL_LIST));
    }

    @Override
    public void setRefresh() {
        mRefreshLayout.setRefreshing(true);

    }

    @Override
    public void stopRefresh() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void getTeachersInfo(List<Person> list) {
        mTeachers=list;

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.student_manage_fab:
                Intent intent=new Intent(getActivity(), CreateActivity.class);
                intent.putExtra(Config.CREATE_USER_TYPE,Config.USER_TYPE_STUDENT);
                getActivity().startActivity(intent);
                break;
        }
    }

    /**
     * 手动触发下拉刷新
     */
    @Override
    public void onRefresh() {
        mPresenter.getStudentsInfo("1");
    }

    /**
     * 显示教练列表
     * @param view
     * @param student
     */
    private void showPopWindow(View view, final Person student){
        View popView=getActivity().getLayoutInflater().inflate(R.layout.pop_bind_layout,null);
        mPopWindow=new PopupWindow(popView,150, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        mTeachersView= (RecyclerView) popView.findViewById(R.id.bind_teachers_rlv);
        mTeachersView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBindAdapter=new BindTeachersAdapter(mTeachers);
        mTeachersView.setAdapter(mBindAdapter);

        mPopWindow.setTouchable(true);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setFocusable(true);
        mPopWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        mPopWindow.showAsDropDown(view, 0, 20);

        mTeachersView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.item_bind_teacher_name:
                        Person persn=mTeachers.get(position);
                        BindInfo info = new BindInfo(student.getId(),persn.getId(),student.getCourse());
                        showBindDialog(persn.getName(),info);

                        break;
                }
            }
        });

    }

    /**
     * 显示是否确定绑定dialog
     * @param name
     * @param info
     */
    private void showBindDialog(String name, final BindInfo info){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("提示")
                .setMessage("是否要将"+name+"教练与该学员绑定")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       mPopWindow.dismiss();
                    }
                });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mPresenter.aboutBind(info);
            }
        });
        builder.show();
    }


    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void cancelDialog() {
        dimssDialog();
        if (mPopWindow != null){
            mPopWindow.dismiss();
        }
    }

    @Override
    public void toastMeassager(String msg) {

        showSnackView(mRootView,msg);

    }


    @Override
    public void statusChange() {
        mPresenter.getStudentsInfo("1");
    }
}
