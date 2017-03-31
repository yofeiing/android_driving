package com.yoflying.drivingschool.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by arvin on 2016/12/23.
 */
public class TimeUtils {

    public final static String YYYY_MM_DD = "yyyy-MM-dd";
    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String getBackTime(String f, int day) {
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat(f);
        String date = format.format(d);
//        System.out.println("现在的日期是：" + date);

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, day);     // 30为增加的天数，可以改变的
        d = ca.getTime();
        String backTime = format.format(d);
//        System.out.println("增加天数以后的日期：" + backTime);
        return backTime;
    }

    public static int getAMorPM(String time) {
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        GregorianCalendar ca = new GregorianCalendar();
        try {
            ca.setTime(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
        return ca.get(GregorianCalendar.AM_PM);
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date     日期
     * @param parttern 日期格式
     * @return 日期字符串
     */
    public static String DateToString(Date date, String parttern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat(parttern).format(date);
            } catch (Exception e) {
            }
        }
        return dateString;
    }

    /**
     * 获取SimpleDateFormat
     *
     * @param parttern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
        return new SimpleDateFormat(parttern);
    }

    /**
     * 判断两个时间是否在同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

    public static Date StringToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
