package com.yoflying.drivingschool.admin.Inputtime;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.yoflying.drivingschool.DriverApplication;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.admin.Inputtime.InPutTimeView;
import com.yoflying.drivingschool.admin.Inputtime.InputTimePresenter;
import com.yoflying.drivingschool.config.Config;
import com.yoflying.drivingschool.entity.Person;
import com.yoflying.drivingschool.student.ui.OrderFragment;
import com.yoflying.drivingschool.ui.HomeActivity;
import com.yoflying.drivingschool.view.MySelectorDecorator;

import java.util.Calendar;
import java.util.List;

/**请假时间选择dialog
 * Created by yaojiulong on 2017/2/6.
 */

public class InputTimeFragment extends DialogFragment implements InPutTimeView {
    private MaterialCalendarView mCalendarView;
    private InputTimePresenter mPresenter;
    private InputTimeListener mInputTimeListener;
   // private TimePicker mInputTime;
    private String mTag;
    private RelativeLayout mTextLayout;
    private TextView mSubmit,mCancle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView=inflater.inflate(R.layout.dialog_add_leave,container);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTag=getTag();
        mCalendarView= (MaterialCalendarView) view.findViewById(R.id.add_leave_calendarview);
        //mInputTime= (TimePicker) view.findViewById(R.id.add_leave_input_time);
        mTextLayout= (RelativeLayout) view.findViewById(R.id.input_time_layout);
        mSubmit= (TextView) view.findViewById(R.id.input_time_submit);
        mCancle= (TextView) view.findViewById(R.id.input_time_cancle);
        mPresenter=new InputTimePresenter(this);
        //mCalendarView.addDecorator(new PrimeDayDisableDecorator());
        Calendar calendar = Calendar.getInstance();
        mCalendarView.setSelectedDate(calendar.getTime());
        Calendar instance1 = Calendar.getInstance();
        instance1.set(instance1.get(Calendar.YEAR), Calendar.JANUARY, 1);

        Calendar instance2 = Calendar.getInstance();
        instance2.set(instance2.get(Calendar.YEAR) + 2, Calendar.OCTOBER, 31);


        mCalendarView.state().edit()
                .setMinimumDate(instance1.getTime())
                .setMaximumDate(instance2.getTime())
                .commit();
        mCalendarView.addDecorators(new MySelectorDecorator(DriverApplication.getContextObject()),new PrimeDayDisableDecorator());
        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                if (mTag.equals(Config.TAG_FRAGMENT_ORDER)){
                    mPresenter.setInputStartDate(mInputTimeListener,date,mTag);
                    dismiss();
                    return;
                }else {
                    mInputTimeListener = (InputTimeListener) getActivity();
                    mPresenter.setInputStartDate(mInputTimeListener,date,mTag);
                    dismiss();
                }



               // showTimePicker();

            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTag.equals(Config.TAG_FRAGMENT_ORDER)){
                    mPresenter.setInputStartDate(mInputTimeListener, mCalendarView.getSelectedDate(),mTag);
                    return;
                }else {
                    mInputTimeListener = (InputTimeListener) getActivity();

                    mPresenter.setInputStartDate(mInputTimeListener, mCalendarView.getSelectedDate(),mTag);
                }

            }
        });

        mCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    private void showTimePicker(){

     /*   mCalendarView.setVisibility(View.GONE);
      //  mInputTime.setVisibility(View.VISIBLE);
        mTextLayout.setVisibility(View.VISIBLE);
     //   mInputTime.setIs24HourView(true);
        mInputTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {


            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hourOfDay,minute;
                hourOfDay=mInputTime.getCurrentHour();
                minute=mInputTime.getCurrentMinute();
                mInputTimeListener = (InputTimeListener) getActivity();
                mPresenter.setInputStartTime(mInputTimeListener, "" + String.valueOf(hourOfDay) + ":" +String.valueOf(minute),mTag);
            }
        });
*/
    }

    /**
     * 添加不能选择的日期 true为不能选择
     */
    private static class PrimeDayDisableDecorator implements DayViewDecorator {

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return PRIME_TABLE[day.getDay()];
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setDaysDisabled(true);
        }

        private static boolean[] PRIME_TABLE = {
                false,  // 0?
                false,
                false, // 2
                true, // 3
                false,
                true, // 5
                false,
                true, // 7
                false,
                false,
                false,
                true, // 11
                false,
                true, // 13
                false,
                false,
                false,
                true, // 17
                false,
                true, // 19
                false,
                false,
                false,
                true, // 23
                false,
                false,
                false,
                false,
                false,
                true, // 29
                false,
                true, // 31
                false,
                false,
                false, //PADDING
        };
    }
}
