package com.yoflying.drivingschool;

import android.app.Application;
import android.content.Context;

import com.yoflying.drivingschool.entity.Person;

import java.util.List;

/**
 * Created by yaojiulong on 2016/12/26.
 */

public class DriverApplication extends Application {
    private static Context mContext;
    private  List<Person> mTeachersList;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();

    }
    //返回
    public static Context getContextObject(){
        return mContext;
    }

    public  List<Person> getTeachersList() {
        return mTeachersList;
    }

    public void setTeachersList(List<Person> data){
        this.mTeachersList=data;
    }





}
