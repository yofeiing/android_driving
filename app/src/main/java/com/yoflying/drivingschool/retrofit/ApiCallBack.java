package com.yoflying.drivingschool.retrofit;

import android.util.Log;

import com.yoflying.drivingschool.config.LogDef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**封装订阅者
 * Created by yaojiulong on 2016/12/27.
 */

public abstract class ApiCallBack<M> extends Subscriber<M> {
    private static final Logger logger = LoggerFactory.getLogger(ApiCallBack.class);
    public abstract void onSuccess(M model);

    public abstract void onFailure(String msg);

    public abstract void onFinish();

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            String msg = httpException.getMessage();
            logger.error(LogDef.LOG_HTTP, "code=" + code);
            onFailure(msg);
            switch (code){
                case 401:
                    onFailure("401");
                    break;
                case  404:
                    onFailure("服务器异常");
            }
        } else {
            onFailure(e.getMessage());
        }
        logger.error(LogDef.LOG_HTTP, e.toString());
        onFinish();
    }

    @Override
    public void onCompleted() {
            onFinish();
    }

    @Override
    public void onNext(M m) {
        onSuccess(m);
    }
}
