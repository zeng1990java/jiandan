package com.github.zeng1990java.jiandan.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/24 下午3:59
 */
public class TimeUtil {
    public static final String getTimelineTime(final long time){
        return toShowTimeStr("yyyy-MM-dd", "MM-dd", time);
    }

    private static final String toShowTimeStr(String fullDate, String monthDayDate, final long time) {
        SimpleDateFormat fullDateFormat = new SimpleDateFormat(fullDate);
        SimpleDateFormat monthDayDateFormat = new SimpleDateFormat(monthDayDate);

        Calendar timeNow = Calendar.getInstance();
        timeNow.setTime(new Date());

        long now = timeNow.getTimeInMillis();
        final long durationInMinutes = (now - time) / (60 * 1000) + 1;

        if (durationInMinutes < 0) {
            return fullDateFormat.format(time);
        }

        if (durationInMinutes <= 1) {
            return "1分钟前";
        }

        if (durationInMinutes < 60) {
            return durationInMinutes + "分钟前";
        }

        long durationInHour = durationInMinutes / 60;

        if (durationInHour <= 6) {
            return durationInHour + "小时前";
        }

        Calendar timeTreehole = Calendar.getInstance();
        timeTreehole.setTimeInMillis(time);
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        if (isSameDay(timeNow, timeTreehole)) {
            int hourOfDay = timeTreehole.get(Calendar.HOUR_OF_DAY);
            if (hourOfDay < 6) {
                return "凌晨" + hourFormat.format(time);
            }

            if (hourOfDay < 11) {
                return "上午" + hourFormat.format(time);
            }

            if (hourOfDay < 13) {
                return "中午" + hourFormat.format(time);
            }

            if (hourOfDay < 18) {
                return "下午" + hourFormat.format(time);
            }

            return "晚上" + hourFormat.format(time);
        } else if (isSameYear(timeNow, timeTreehole)) {
            int nowDayOfYear = timeNow.get(Calendar.DAY_OF_YEAR);
            int treeholeDayOfYear = timeTreehole.get(Calendar.DAY_OF_YEAR);
            if (nowDayOfYear - treeholeDayOfYear == 1) {
                // 昨天
                return "昨天" + hourFormat.format(time);
            }else{
                return monthDayDateFormat.format(time);
            }
        }

        return fullDateFormat.format(time);
    }

    private static boolean isSameDay(Calendar left, Calendar right) {
        return isSameYear(left, right)
                && left.get(Calendar.DAY_OF_YEAR) == right
                .get(Calendar.DAY_OF_YEAR);
    }

    private static boolean isSameYear(Calendar left, Calendar right) {
        return left.get(Calendar.YEAR) == right.get(Calendar.YEAR);
    }

    public static final long HOURS = 60*60*1000;
    public static final long MINUTES = 60*1000;
    public static final long SECONDS = 1000;
    private static DecimalFormat sTimeFormat = new DecimalFormat("00");

    public static String formatCountdownTime(long millis){

        long hours = millis / HOURS;
        long minutes = (millis % HOURS) / MINUTES;
        long seconds = (millis%MINUTES)/SECONDS;

        return String.format("%s:%s:%s", sTimeFormat.format(hours),sTimeFormat.format(minutes),sTimeFormat.format(seconds));
    }
}
