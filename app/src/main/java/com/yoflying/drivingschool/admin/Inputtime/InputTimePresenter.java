package com.yoflying.drivingschool.admin.Inputtime;

import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.yoflying.drivingschool.base.BasePresenter;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.student.ui.OrderFragment;


/**添加请假  输入时间控制器
 * Created by yaojiulong on 2017/2/6.
 */

public class InputTimePresenter extends BasePresenter<InPutTimeView> {
    private InPutTimeView mInputTimeView;
    String mTime="";

    public InputTimePresenter(InPutTimeView inputView) {
        this.mInputTimeView = inputView;
        attachView(mInputTimeView);
    }

    public void setInputStartDate(InputTimeListener listener,CalendarDay date,String tag){
        String time="";
        int month=date.getMonth()+1;
        time=""+String.valueOf(date.getYear())+"-"+""+String.valueOf(month)+"-"+String.valueOf(date.getDay());
        mTime=time;

        if (tag.equals(Config.TAG_FRAGMENT_START_TIME)){
            listener.onGetInputStartTime(mTime);
        }else if (tag.equals(Config.TAG_FRAGMENT_END_TIME)){
            listener.onGetInputEndTime(mTime);
        }else if (tag.equals(Config.TAG_FRAGMENT_ORDER)){
            OrderFragment.mInputListener.onGetInputStartTime(mTime);
        }
    }



}
