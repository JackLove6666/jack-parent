package com.cloud.jack.core.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日志工具类
 *
 * @author 马鹿
 */
public class DateUtil {

    public final static String DATE_FORMAT_1 = "yyyy-MM-dd";
    public final static String DATE_FORMAT_2 = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_FORMAT_3 = "yyyy-MM-dd HH:mm";
    public final static String DATE_FORMAT_4 = "yyyy-MM";
    public final static String DATE_FORMAT_5 = "yyyy/MM/dd";
    public final static String DATE_FORMAT_6 = "yyyy/MM/dd HH:mm:ss";
    public final static String DATE_FORMAT_7 = "yyyy/MM/dd HH:mm";
    public final static String DATE_FORMAT_8 = "yyyy/MM";
    public final static String DATE_FORMAT_9 = "yyyy.MM.dd";
    public final static String DATE_FORMAT_10 = "yyyy.MM.dd HH:mm:ss";
    public final static String DATE_FORMAT_11 = "yyyy.MM.dd HH:mm";
    public final static String DATE_FORMAT_12 = "yyyy.MM";
    public final static String DATE_FORMAT_13 = "yyyy";
    public final static String DATE_FORMAT_14 = "yyyyMM";
    public final static String DATE_FORMAT_15 = "MM";
    public final static String DATE_FORMAT_16 = "yyyyMMdd";
    public final static String DATE_FORMAT_17 = "dd-MM-yyyy";
    public final static String DATE_FORMAT_18 = "yyyy-MM-dd'T'HH:mm:ss'Z'";


    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
            "yyyy.MM.dd HH:mm", "yyyy.MM", "yyyy", "yyyyMM", "MM", "yyyyMMdd", "dd-MM-yyyy", "yyyy-MM-dd'T'HH:mm:ss'Z'"};

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
     * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }

        try {
            Date date = DateUtils.parseDate(str.toString(), parsePatterns);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数差
     *
     * @param before
     * @param after
     * @return
     */
    public static Integer getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return new Double(Math.abs((afterTime - beforeTime) / (1000 * 60 * 60 * 24))).intValue();
    }

    /**
     * 获取两个日期之间的小时差
     *
     * @param before
     * @param after
     * @return
     */
    public static Integer getDistanceOfTwoHour(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return new Double(Math.abs(afterTime - beforeTime) / (1000 * 60 * 60)).intValue();
    }

    /**
     * 获取两个日期之间的分钟差
     *
     * @param before
     * @param after
     * @return
     */
    public static Integer getDistanceOfTwoMinute(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return new Double(Math.abs(afterTime - beforeTime) / (1000 * 60)).intValue();
    }

    /**
     * 获取两个日期之间的秒差
     *
     * @param before
     * @param after
     * @return
     */
    public static Integer getDistanceOfTwoSecond(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return new Double(Math.abs(afterTime - beforeTime) / (1000 * 60)).intValue();
    }

    /**
     * <p>Description：获取日期月份的第一天</p>
     *
     * @return Date
     * @author lidongjie
     * @date 2019年4月24日
     */
    public static Date getFirstMonthOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        //设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }


    public static Date getSomeDate(String date, Integer index) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate(date));
        calendar.add(Calendar.MONTH, 0);
        //设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.DAY_OF_MONTH, index);
        return calendar.getTime();
    }

    /**
     * 获取上个月最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastMonthofLastDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取下个月最后一天
     *
     * @param date
     * @return
     */
    public static Date getNextMonthofNextDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, +1);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }


    /**
     * <p>Description：获取日期月份的最后一天</p>
     *
     * @return Date
     * @author lidongjie
     * @date 2019年4月24日
     */
    public static Date getLastMonthOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取截止日期之前的数据
     *
     * @param day
     * @return
     */
    public static Date addDate(int day) {
        //获取三十天前日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 调整日期到天
     *
     * @param day
     * @return
     */
    public static Date addDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 调整日期到小时
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * 调整时间到秒
     *
     * @param date
     * @param second
     * @return
     */
    public static Date addSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * <p>Description：获取月份区间</p>
     *
     * @return List<String>
     * @author lidongjie
     * @date 2019年4月24日
     */
    public static List<String> getMonthRange(Date before, Date after) {
        before = parseDate(formatDate(before, "yyyy-MM"));
        after = parseDate(formatDate(after, "yyyy-MM"));

        List<String> list = new ArrayList<>();
        // 定义日期实例
        Calendar dd = Calendar.getInstance();
        // 设置日期起始时间
        dd.setTime(before);

        // 判断是否到结束日期
        while (dd.getTime().before(after) || dd.getTime().equals(after)) {
            String str = formatDate(dd.getTime(), "yyyy-MM");
            // 进行当前日期月份加1
            dd.add(Calendar.MONTH, 1);
            list.add(str);
        }

        return list;
    }

    public static Date stringToDate(String dateString, String formatType) throws ParseException {
        DateFormat df = new SimpleDateFormat(formatType);
        Date date = df.parse(dateString);
        return date;
    }

    /**
     * 日期格式转换yyyy-MM-dd'T'HH:mm:ss.SSSXXX  (yyyy-MM-dd'T'HH:mm:ss.SSSZ) TO  yyyy-MM-dd HH:mm:ss
     * 2020-04-09T23:00:00.000+08:00 TO 2020-04-09 23:00:00
     *
     * @throws ParseException
     */
    public static Date dealDateFormat(String oldDateStr) {
        return TypeUtils.StringToDate(oldDateStr);
    }

    /**
     * 设置一个整点时间 示例 2020-11-24 14:00:00
     *
     * @param date
     */
    public static Date formatTimeOnTheHour(Date date) {
        Calendar recordCalendar = Calendar.getInstance();
        recordCalendar.setTime(date);
        recordCalendar.set(Calendar.MINUTE, 0);
        recordCalendar.set(Calendar.SECOND, 0);
        recordCalendar.set(Calendar.MILLISECOND, 0);
        return recordCalendar.getTime();
    }

    /**
     * 设置一个整点时间 示例 2020-11-24 00:00:00
     *
     * @param date
     */
    public static Date formatTimeOnTheHourFrist(Date date) {
        Calendar recordCalendar = Calendar.getInstance();
        recordCalendar.setTime(date);
        recordCalendar.set(Calendar.HOUR_OF_DAY, 0);
        recordCalendar.set(Calendar.MINUTE, 0);
        recordCalendar.set(Calendar.SECOND, 0);
        recordCalendar.set(Calendar.MILLISECOND, 0);
        return recordCalendar.getTime();
    }

    /**
     * 设置一个整点时间 示例 2020-11-24 23:59:59
     *
     * @param date
     */
    public static Date formatTimeOnTheHourLast(Date date) {
        Calendar recordCalendar = Calendar.getInstance();
        recordCalendar.setTime(date);
        recordCalendar.set(Calendar.HOUR_OF_DAY, 23);
        recordCalendar.set(Calendar.MINUTE, 59);
        recordCalendar.set(Calendar.SECOND, 59);
        recordCalendar.set(Calendar.MILLISECOND, 0);
        return recordCalendar.getTime();
    }

    /**
     * 昨天时间
     *
     * @return
     */
    public static String getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return DateUtil.formatDate(calendar.getTime(), DateUtil.DATE_FORMAT_16);
    }

    public static Date getYesterdayTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    public static Integer getNextMonthDay(Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


}
