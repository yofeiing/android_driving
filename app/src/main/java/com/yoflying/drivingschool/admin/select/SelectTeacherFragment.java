package com.yoflying.drivingschool.admin.select;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yoflying.drivingschool.DriverApplication;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.admin.adapter.TeachersManageAdapter;
import com.yoflying.drivingschool.admin.configmange.ui.ChoiceTimeActivity;
import com.yoflying.drivingschool.entity.Person;

import java.util.List;

/**选择教练dialog
 * Created by yaojiulong on 2017/2/23.
 */

public class SelectTeacherFragment extends DialogFragment {
    private RecyclerView mTeachersView;
    private List<Person> mteachers;
    private Button mCancel,mSubmit;
    private ChoiceTimeActivity mActivity;
    private DriverApplication mApplication;
    private TeachersManageAdapter mAdapter;
    private Integer mPostion;
    private GetTeacherLinstener mGetTeacherLinstener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mGetTeacherLinstener= (GetTeacherLinstener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置宽度
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView=inflater.inflate(R.layout.dialog_select_teacher,container);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTeachersView= (RecyclerView) view.findViewById(R.id.dialog_teacher_rlv);

        initData();
    }

    private void initData(){
        mPostion=Integer.valueOf(getTag());
        mApplication= (DriverApplication) getActivity().getApplication();
        mteachers=mApplication.getTeachersList();

        mAdapter=new TeachersManageAdapter(mteachers);
        mTeachersView.setLayoutManager(new LinearLayoutManager(getContext()));
        mTeachersView.setAdapter(mAdapter);
        setLinstener();
    }

    private void setLinstener(){
        mTeachersView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("dandy","点击了  "+mteachers.get(position).getName()+"  m "+mPostion);

                mGetTeacherLinstener.getTeacherinfo(mPostion,position);
                dismiss();
            }
        });
    }

    public interface GetTeacherLinstener{
        void getTeacherinfo(int postion,int id);
    }

}
