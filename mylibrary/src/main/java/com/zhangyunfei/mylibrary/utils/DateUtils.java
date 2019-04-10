package com.zhangyunfei.mylibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/10/16 13:54
 * 功能描述：日期工具类
 */
public class DateUtils {
    public static String getDate(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy/MM/dd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }


    public static String getTime(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("HH:MM");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }


    /*
     * 将时间戳转换为时间
     * "yyyy-MM-dd HH:mm:ss"
     */
    public static String stampToDate(long timeMillis,String pattern){
        if (pattern.isEmpty()){
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date(timeMillis);
        return simpleDateFormat.format(date);
    }
}
