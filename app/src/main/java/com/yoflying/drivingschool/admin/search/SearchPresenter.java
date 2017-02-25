package com.yoflying.drivingschool.admin.search;

import android.util.Log;

import com.yoflying.drivingschool.base.BasePresenter;
import com.yoflying.drivingschool.entity.HttpsResult;
import com.yoflying.drivingschool.entity.Person;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.retrofit.ApiCallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**搜索控制器
 * Created by yaojiulong on 2017/1/10.
 */

public class SearchPresenter extends BasePresenter<ISearchView> {
    private ISearchView mSearchView;

    public SearchPresenter(ISearchView mSearchView) {
        this.mSearchView = mSearchView;
        attachView(mSearchView);
    }

    /**
     * 搜索教练
     */
    public void searchTeacher(){
        mSearchView.showDialog();
        ApiCallBack<HttpsResult<List<Person>>> subscriber=new ApiCallBack<HttpsResult<List<Person>>>() {
            @Override
            public void onSuccess(HttpsResult<List<Person>> model) {
                mSearchView.cancelDialog();

                if (model.getData().size()==0){
                    mSearchView.toastMeassager("没有搜索到");
                }else {
                    mSearchView.setSearchResult(model.getData());
                    Log.e("dandy","搜索教练成功 "+model.toString());
                }

            }
            @Override
            public void onFailure(String msg) {
                mSearchView.cancelDialog();
                Log.e("dandy","搜索教练失败 "+msg);
            }

            @Override
            public void onFinish() {

            }
        };
        Map<String,String> map=new HashMap<>();
        map.put("discern", Config.TYPE_TEACHER);
        map.put("name",mSearchView.getInputContent());
        addSubscription(mApiStore.searchPerson(map),subscriber);
    }

    /**
     * 搜索学生
     */
    public void searchStudent(){
        mSearchView.showDialog();
        ApiCallBack<HttpsResult<List<Person>>> subscriber=new ApiCallBack<HttpsResult<List<Person>>>() {
            @Override
            public void onSuccess(HttpsResult<List<Person>> model) {
                mSearchView.cancelDialog();
                if (model.getData().size()==0){
                    mSearchView.toastMeassager("没有搜索到");
                }
                mSearchView.setSearchResult(model.getData());


            }

            @Override
            public void onFailure(String msg) {
                Log.e("dandy","搜索失败 "+msg);
                mSearchView.cancelDialog();
            }

            @Override
            public void onFinish() {

            }
        };
        Map<String,String> map=new HashMap<>();
        map.put("discern",Config.TYPE_STUDENt);
        map.put("name",mSearchView.getInputContent());
        addSubscription(mApiStore.searchPerson(map),subscriber);
    }
}
