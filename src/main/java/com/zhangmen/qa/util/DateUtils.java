package com.zhangmen.qa.util;

import com.zhangmen.qa.common.Utils;
import com.zhangmen.qa.exception.HttpProcessException;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class DateUtils {


    public static final String STR_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String FULL_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd HH:mm ---> Date
     * @param dateString
     * @return
     */
    public static Date strYMDHMToDate(String dateString) {
        return fromLocalDateTime(strYMDHMToDateTime(dateString));
    }

    /**
     * yyyy-MM-dd HH:mm ---> LocalDateTime
     * @param dateString
     * @return
     */
    public static LocalDateTime strYMDHMToDateTime(String dateString) {
        LocalDateTime date = null;
        try {
            date = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            Utils.error("日期解析错误：dateString:"+dateString);
        }
        return date;
    }

    /**
     * 获取当天开始时间
     * @return
     */
    public static Date getTodayStart(){
        return fromLocalDateTime(LocalDate.now().atTime(LocalTime.MIN));
    }

    /**
     * 获取当天结束时间
     * @return
     */
    public static Date getTodayEnd(){
        return fromLocalDateTime(LocalDate.now().atTime(LocalTime.MAX));
    }

    /**
     * 获取本月第一天
     * @return
     */
    public static Date getMonthStartDay(){
        return fromLocalDateTime(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atTime(LocalTime.MIN));
    }

    /**
     * 获取本月最后一天
     * @return
     */
    public static Date getMonthEndDay(){
        return fromLocalDateTime(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).atTime(LocalTime.MAX));
    }

    /**
     * LocalDateTime --> Date
     * @param localDateTime
     * @return
     */
    public static Date fromLocalDateTime(LocalDateTime localDateTime) {
        if(localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date parseDate(String strDate, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String format(Date date, String format) {
        if(date == null || StringUtils.isBlank(format)) {
            return null;
        }
        return DateTimeFormatter.ofPattern(format).format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }

    public static String format(Date date, DateTimeFormatter formatter) {
        if (date == null || formatter == null) {
            return null;
        }
        return formatter.format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }

    public static LocalDateTime toLocalDateTime(Long timestamp) {
        return timestamp == null ? null : LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.ofHours(8));
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date == null ? null : LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.ofHours(8));
    }

    public static LocalDateTime toLocalDateTime(String datetime) {
        return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static LocalDate toLocalDate(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(STR_YYYY_MM_DD));
    }

    public static Long toTimestamp(LocalDateTime time) {
        return time == null ? null : time.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    public static LocalDateTime defaultTime() {
        return LocalDateTime.of(1900, Month.JANUARY,1,0,0,0,0);
    }

    public static boolean between(Date d, Date begin, Date end) {
        if(d == null || begin == null || end == null) {
            return false;
        }

        return d.compareTo(begin) >= 0 && d.compareTo(end) <= 0;
    }

    public static boolean gt(Date d1, Date d2) throws HttpProcessException {
        if(d1 == null || d2 == null) {
            throw new HttpProcessException("参数无效");
        }
        return d1.compareTo(d2) > 0;
    }

    public static boolean gte(Date d1, Date d2) throws HttpProcessException {
        if(d1 == null || d2 == null) {
            throw new HttpProcessException("参数无效");
        }
        return d1.compareTo(d2) >= 0;
    }
}