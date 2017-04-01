package com.yoflying.drivingschool.modules.welcome;

import android.os.Handler;

import com.yoflying.drivingschool.app.DriverApplication;
import com.yoflying.drivingschool.base.BasePresenter;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.utils.UtilSharedPreferences;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by yaojiulong on 2016/12/26.
 */

public class WelcomePresenter extends BasePresenter<IWelcomeView> {
    private IWelcomeView mWecomeView;
    private boolean mIsNew=true;

    public WelcomePresenter(IWelcomeView wecomeView) {
        this.mWecomeView=wecomeView;
    }

    public void checkData(){
        String token= UtilSharedPreferences.getStringData(DriverApplication.getContextObject(), Config.KEY_TOKEN);
        if (token.equals("")){
            mIsNew=true;
        }else {
            mIsNew=false;
        }
        delayed();
    }

    private void delayed(){
        Observable.timer(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).map(new Func1<Long, Object>() {
            @Override
            public Object call(Long aLong) {
                if (mIsNew){
                    mWecomeView.toLoginActivity();
                }else {
                    mWecomeView.toAdminActivity();
                }
                return null;
            }
        }).subscribe();

    }


}
