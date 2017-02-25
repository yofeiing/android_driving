package com.yoflying.drivingschool.admin.teacher;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.admin.adapter.TeachersManageAdapter;
import com.yoflying.drivingschool.admin.create.CreateActivity;
import com.yoflying.drivingschool.base.BaseFragment;
import com.yoflying.drivingschool.entity.Person;
import com.yoflying.drivingschool.config.Config;

import java.util.List;

/**教练管理界面
 * Created by yaojiulong on 2017/1/13.
 */

public class TeacherManageFragment extends BaseFragment implements ITeacherManageView,SwipeRefreshLayout.OnRefreshListener,View.OnClickListener{
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mTeachers;
    private FloatingActionButton mAddFab;
    private TeacherManagePresenter mPresenter;
    private TeachersManageAdapter mAdapter;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_manage_tacher,container,false);
        mPresenter=new TeacherManagePresenter(this);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {
        mRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.teacher_manage_refresh_layout);
        mTeachers= (RecyclerView) view.findViewById(R.id.teacher_manage_rlv);
        mAddFab= (FloatingActionButton) view.findViewById(R.id.teacher_manage_add_fab);
        mTeachers.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRefreshLayout.setColorSchemeResources(R.color.refresh_color_1
                , R.color.refresh_color_2, R.color.refresh_color_3, R.color.refresh_color_4);
        mAddFab.setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getTeachersInfo("1");
    }

    @Override
    public void showTeachersInfo(List<Person> mLists) {
        mAdapter=new TeachersManageAdapter(mLists);
        mTeachers.setAdapter(mAdapter);
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
    public void onRefresh() {
        mPresenter.getTeachersInfo("1");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.teacher_manage_add_fab:
                Intent intent=new Intent(getActivity(), CreateActivity.class);
                intent.putExtra(Config.CREATE_USER_TYPE,Config.USER_TYPE_TEACHER);
                getActivity().startActivity(intent);
        }
    }
}
