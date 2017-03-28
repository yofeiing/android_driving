package com.yoflying.drivingschool.app;

import android.app.Application;
import android.content.Context;

import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.entity.Person;
import com.yoflying.drivingschool.utils.CrashHandler;
import com.yoflying.drivingschool.widget.CircularAnim;

import java.util.List;

/**
 * Created by yaojiulong on 2016/12/26.
 */

public class DriverApplication extends Application {
    private static Context mContext;
    private  List<Person> mTeachersList;
    private  Person person;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        CircularAnim.init(700,500, R.color.colorAccent);
       /* CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());*/
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


}
