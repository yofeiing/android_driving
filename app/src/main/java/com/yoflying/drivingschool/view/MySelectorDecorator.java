package com.yoflying.drivingschool.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.yoflying.drivingschool.R;

/**日历选中的颜色
 * Created by yaojiulong on 2017/2/6.
 */

public class MySelectorDecorator implements DayViewDecorator {
    private final Drawable drawable;

    public MySelectorDecorator(Context context) {
        drawable = context.getResources().getDrawable(R.drawable.my_selector);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return true;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }
}
