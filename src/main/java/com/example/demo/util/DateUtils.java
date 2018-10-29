package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by qzj on 2018/6/5
 */
public class DateUtils {

    /**
     * 将长时间格式时间转换为字符串
     * @return 当前时间的时间戳
     */
    public static String timestamp(Date date,String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 将时间格式时间转换为字符串
     * @param dateDate
     * @return
     */
    public static String dateToStr(Date dateDate,String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    public static Date future(Integer monhth) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime future = localDateTime.plusMonths(monhth);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = future.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }
}
