package com.yoflying.drivingschool.admin.search;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.admin.adapter.SearchAdapter;
import com.yoflying.drivingschool.base.BaseActivity;
import com.yoflying.drivingschool.entity.Person;
import com.yoflying.drivingschool.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * 搜索界面
 */
public class SearchActivity extends BaseActivity implements ISearchView ,View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{
    private EditText mInputText;
    private SearchPresenter mPresenter;
    private ImageView mReturn;
    private LinearLayout mSearchTeacherLayout,mSearchStudentLayout,mSearchLayout;
    private SwipeRefreshLayout mRefreshLayout;
    private SearchAdapter mAdapter;
    private RecyclerView mResultView;
    private List<Person> mData;
    private ImageView mSearchImg;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_search);
        mPresenter=new SearchPresenter(this);
    }

    @Override
    protected void findViewId() {
        super.findViewId();
        mInputText=findView(R.id.search_edit_etv);
        mReturn=findView(R.id.search_return);
        mSearchTeacherLayout=findView(R.id.search_teacher_layout);
        mSearchStudentLayout=findView(R.id.search_student_layout);
        mRefreshLayout=findView(R.id.search_refresh_layout);
        mResultView=findView(R.id.search_result_rlv);
        mSearchLayout=findView(R.id.search_layout);
        mSearchImg = findView(R.id.search_img);
        mRefreshLayout.setColorSchemeResources(R.color.refresh_color_1
                , R.color.refresh_color_2, R.color.refresh_color_3, R.color.refresh_color_4);
        mResultView.setLayoutManager(new LinearLayoutManager(this));
        mInputText.addTextChangedListener(textWatcher);

    }


    @Override
    protected void initData() {

        mData=new ArrayList<>();
        mAdapter=new SearchAdapter(mData);
        mResultView.setAdapter(mAdapter);
    }

    @Override
    protected void setLinstener() {
        super.setLinstener();
        mReturn.setOnClickListener(this);

        mSearchStudentLayout.setOnClickListener(this);
        mSearchTeacherLayout.setOnClickListener(this);
        mRefreshLayout.setOnRefreshListener(this);
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
    public String getInputContent() {
        return mInputText.getText().toString();
    }

    @Override
    public void setSearchResult(List<Person> data) {
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDialog() {
//        hideKeyboard();
        mSearchLayout.setVisibility(View.GONE);
        mRefreshLayout.setRefreshing(true);
    }

    /**
     * 隐藏软键盘，这样会导致点两次返回键才能退出当前activity
     */
    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mInputText.getWindowToken(),0);
    }

    @Override
    public void cancelDialog() {
        mRefreshLayout.setRefreshing(false);

    }

    @Override
    public void toastMeassager(String msg) {
        showSnackView(getView(),msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_return:
                finish();
                break;
            case R.id.search_teacher_layout:

                mPresenter.searchTeacher();
                break;
            case R.id.search_student_layout:

                mPresenter.searchStudent();
                break;

        }
    }

    @Override
    public void onRefresh() {
        mRefreshLayout.setRefreshing(false);
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            if (s.length()==0){
                mData.clear();
                if (mAdapter!=null){
                    mSearchLayout.setVisibility(View.VISIBLE);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade,R.anim.hold);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogUtil.e(this,"onbackpreesed");
    }
}
