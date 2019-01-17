package com.zx.api.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Name: DateUtil
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-28 14:47
 */
public class DateUtil {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    // 评论日期格式
    public static final String YEAR_MONTH_DAY_PATTERN = "yyyy-MM-dd";
    public static final String MONTH_DAY_HOUR_MINUTE_PATTERN = "MM-dd HH:mm";
    public static final String HOUR_MINUTE_PATTERN = "HH:mm";
    public static final String MINUTE_SECOND_SIMPLE_TIME = "mm分ss秒后";
    public static final String SIMPLE_DATE_TIME = "MM月dd日HH时mm分";

    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年


    public static Date parseStringToDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_PATTERN);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 根据指定格式还原日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date parse(String date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINESE);
        try {
            return format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 根据日期生成指定的格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINESE);
            return format.format(date);
        } catch (Exception ex) {
        }
        return "";
    }

    public static String formatTimeToStr(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_PATTERN);
        String timeStr = "";
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = new Date().getTime() - date.getTime();
        if (diff >= 0) {
            timeStr = formatHistoryTime(date);
        } else {
            timeStr = formatFutureTime(date);
        }
        return timeStr;
    }

    private static String formatFutureTime(Date date) {
        if (date == null) {
            return null;
        }
        long diff = date.getTime() - new Date().getTime();
        if (diff < 5 * minute) {
            SimpleDateFormat sdf = new SimpleDateFormat(MINUTE_SECOND_SIMPLE_TIME);
            String dateString = sdf.format(diff);
            return dateString;
        }

        Calendar currentCD = Calendar.getInstance();
        Calendar dateCD = Calendar.getInstance();
        dateCD.setTime(date);
        int intervalDay = getDaysBetween(currentCD, dateCD);
        if (intervalDay < 1) {
            SimpleDateFormat sdf = new SimpleDateFormat(HOUR_MINUTE_PATTERN);
            String dateString = sdf.format(date);
            return "今天" + dateString;
        } else if (intervalDay < 2) {
            SimpleDateFormat sdf = new SimpleDateFormat(HOUR_MINUTE_PATTERN);
            String dateString = sdf.format(date);
            return "明天" + dateString;
        } else if (intervalDay < 3) {
            SimpleDateFormat sdf = new SimpleDateFormat(HOUR_MINUTE_PATTERN);
            String dateString = sdf.format(date);
            return "后天" + dateString;
        } else {
            if (currentCD.get(Calendar.YEAR) > dateCD.get(Calendar.YEAR)) {
                SimpleDateFormat sdf = new SimpleDateFormat(YEAR_MONTH_DAY_PATTERN);
                String dateString = sdf.format(date);
                return dateString;
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat(MONTH_DAY_HOUR_MINUTE_PATTERN);
                String dateString = sdf.format(date);
                return dateString;
            }
        }
    }


    private static String formatHistoryTime(Date date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        if (diff <= 1 * minute) {
            return "刚刚";
        } else if (diff <= 1 * hour) {
            int minutePre = (int) (diff / minute);
            return minutePre + "分钟前";
        }

        Calendar currentCD = Calendar.getInstance();
        Calendar dateCD = Calendar.getInstance();
        dateCD.setTime(date);
        int intervalDay = getDaysBetween(currentCD, dateCD);

        if (intervalDay < 1) {
            SimpleDateFormat sdf = new SimpleDateFormat(HOUR_MINUTE_PATTERN);
            String dateString = sdf.format(date);
            return "今天" + dateString;
        } else if (intervalDay < 2) {
            SimpleDateFormat sdf = new SimpleDateFormat(HOUR_MINUTE_PATTERN);
            String dateString = sdf.format(date);
            return "昨天" + dateString;
        } else if (intervalDay < 3) {
            SimpleDateFormat sdf = new SimpleDateFormat(HOUR_MINUTE_PATTERN);
            String dateString = sdf.format(date);
            return "前天" + dateString;
        } else {
            if (currentCD.get(Calendar.YEAR) > dateCD.get(Calendar.YEAR)) {
                SimpleDateFormat sdf = new SimpleDateFormat(YEAR_MONTH_DAY_PATTERN);
                String dateString = sdf.format(date);
                return dateString;
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat(MONTH_DAY_HOUR_MINUTE_PATTERN);
                String dateString = sdf.format(date);
                return dateString;
            }
        }
    }

    private static int getDaysBetween(Calendar d1, Calendar d2) {
        if (d1.after(d2)) {  // swap dates so that d1 is start and d2 is end
            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);//得到当年的实际天数
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    /**
     * @return
     */
    public static Date getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * @return
     */
    public static Date getnowEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    public static Date getTime(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -day); //向前走一天
        Date date = calendar.getTime();
        System.out.println("前" + day + "天时间为  " + format(date, YEAR_MONTH_DAY_PATTERN));
        return date;
    }
}
