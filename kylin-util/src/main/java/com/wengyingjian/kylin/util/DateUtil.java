package com.wengyingjian.kylin.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 根据Unix时间戳计算那一天开始的时间戳
     *
     * @param time 给定任意一点时间, 精确到毫秒
     * @return 那一天开始的时间, 精确到秒
     */
    public static int getStartTimeOfOneDay(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return (int) (cal.getTimeInMillis() / 1000);
    }

    /**
     * 日期转字符串
     *
     * @param date    日期
     * @param pattern 格式
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        return simpleDateFormat.format(date);
    }

    /**
     * 字符串转日期
     *
     * @param date    字符串
     * @param pattern 格式
     */
    public static Date format(String date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(date, new ParsePosition(0));
    }

}
