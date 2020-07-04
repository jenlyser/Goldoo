package com.zgjy.app.wklib.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 类型转换辅助类
 */
public  class ConvertUtils {


    //*********************时间类型转换******************

    /**
     * 获得最小时间日期
     *
     * @return
     */
    public static Date getMinDate() {
        Date dateMin = new Date(0);
        return dateMin;
    }

    /**
     * 将时间戳转换成时间对象(毫秒)
     *
     * @return 时间对象
     */
    public static Date getDate(long time_ms) {
        return new Date(time_ms);
    }

    /**
     * 将时间戳转换成时间对象(秒)
     *
     * @return 时间对象
     */
    public static Date getDateBySecond(long time_s) {
        return new Date(time_s * 1000);
    }

    /**
     * 将字符串转换为时间对象(date)
     *
     * @param value 时间字符串,支持的格式为:yyyy-MM-dd HH:mm:ss,yyyy/MM/dd HH:mm:ss
     * @return 时间对象
     */
    public static Date getDate(String value) {
        return getDate(value, getMinDate());
    }

    /**
     * 将字符串转换为时间对象(date)
     *
     * @param value        时间字符串,支持的格式为:yyyy-MM-dd HH:mm:ss,yyyy/MM/dd HH:mm:ss
     * @param defaultValue 默认时间
     * @return 时间对象
     */
    public static Date getDate(String value, Date defaultValue) {
        //尝试格式1
        Date itemDate = getDate(value, "yyyy-MM-dd HH:mm:ss");
        if (itemDate == null) {
            //尝试转换格式2
            itemDate = getDate(value, "yyyy/MM/dd HH:mm:ss");
        }
        return itemDate == null ? defaultValue : itemDate;
    }

    /**
     * 将字符串转换为时间对象(date),根据指定格式
     *
     * @param value     时间字符串
     * @param formatStr 转换格式
     * @return 时间对象
     */
    public static Date getDate(String value, String formatStr) {
        SimpleDateFormat sdFormat = new SimpleDateFormat(formatStr);
        try {
            return sdFormat.parse(value);
        } catch (ParseException e) {
            //转换失败
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将date转换为string;转换格式 :yyyy-MM-dd HH:mm:ss
     *
     * @param value 时间对象
     * @return 时间字符串
     */
    public static String getDateString(Date value) {
        return getDateString(value, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将时间戳转换成时间字符串;毫秒
     * 转换格式 :yyyy-MM-dd HH:mm
     *
     * @param time_ms 时间戳,毫秒
     * @return 时间字符串
     */
    public static String getDateString(long time_ms) {
        return getDateString(getDate(time_ms), "yyyy-MM-dd HH:mm");
    }

    /**
     * 将时间戳转换成时间字符串;秒
     * 转换格式 :yyyy-MM-dd HH:mm
     *
     * @param time_s 时间戳,秒
     * @return 时间字符串
     */
    public static String getDateStringBySecond(long time_s) {
        return getDateString(getDateBySecond(time_s), "yyyy-MM-dd HH:mm");
    }

    /**
     * 将date转换为string;毫秒字符串
     * 转换格式 :yyyy-MM-dd HH:mm
     *
     * @param time_ms 时间戳,毫秒
     * @param format  显示格式
     * @return 时间字符串
     */
    public static String getDateString(long time_ms, String format) {
        return getDateString(getDate(time_ms), format);
    }

    /**
     * 将时间字符串重新格式化
     *
     * @param timeStr 时间字符串
     * @param format  新的时间格式  如:yyyy-MM-dd HH:mm:ss
     * @return 时间字符串
     */
    public static String getDateString(String timeStr, String format) {
        return getDateString(getDate(timeStr), format);
    }

    /**
     * 将date根据格式转换为string
     *
     * @param value  时间对象
     * @param format 转换格式 如:yyyy-MM-dd HH:mm:ss
     * @return 时间字符串
     */
    public static String getDateString(Date value, String format) {
        if (value == null) {
            return "";
        }
        SimpleDateFormat sdFormat = new SimpleDateFormat(format);
        return sdFormat.format(value);
    }

    /**
     * 根据时间获取字符串,如果是今年则不显示单位年(yyyy)的部分
     *
     * @param value 判断字符串
     * @return 时间字符串
     */
    public static String getDateStringByYear(Date value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(value);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(new Date());

        if (cal.get(Calendar.YEAR) == cal.get(Calendar.YEAR)) {
            return getDateString(value, "MM--dd HH:mm");
        }
        return getDateString(value, "yyyy-MM--dd HH:mm");
    }


    //**********************************基本类型转换********************************

    /**
     * 将string转换为long
     *
     * @param value
     * @return
     */
    public static long toLong(String value) {
        return toLong(value, Long.MIN_VALUE);
    }

    /**
     * 将string转换为long
     *
     * @param value
     * @param defaultValue 默认值
     * @return
     */
    public static long toLong(String value, long defaultValue) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            // 转换失败
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 将string转换为int
     *
     * @param value
     * @return
     */
    public static int toInt(String value) {
        return toInt(value, Integer.MIN_VALUE);
    }

    /**
     * 将string转换为int
     *
     * @param value
     * @param defaultValue 默认值
     * @return
     */
    public static int toInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            //转换失败
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 将string转换为Float
     *
     * @param value
     * @return
     */
    public static Float toFloat(String value) {
        return toFloat(value, Float.MIN_VALUE);
    }

    /**
     * 将string转换为Float
     *
     * @param value
     * @param defaultValue 默认值
     * @return
     */
    public static Float toFloat(String value, Float defaultValue) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            // 转换失败
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 将string转换为double
     *
     * @param value
     * @return
     */
    public static double toDouble(String value) {
        return toDouble(value, Double.MIN_VALUE);
    }

    /**
     * 将string转换为double
     *
     * @param value
     * @param defaultValue 默认值
     * @return
     */
    public static double toDouble(String value, double defaultValue) {
        try {
            if(value!=null) {
                return Double.parseDouble(value);
            }
        } catch (NumberFormatException e) {
            // 转换失败
            e.printStackTrace();
        }
        return defaultValue;
    }

    //**************************************************常用操作*******************************

    /**
     * 格式化字符串 #,###.00
     *
     * @param data 需要格式化的数据
     * @return 返回格式化后字符串
     */
    public static String formatDouble(double data) {
        if (data < 1000 && data > -1000) {
            return formatString(data, "###.00");
        }
        return formatString(data, "#,###.00");
    }

    /**
     * 格式化字符串 #,###
     *
     * @param data 需要格式化的数据
     * @return 返回格式化后字符串
     */
    public static String formatDouble2(double data) {
        DecimalFormat df = new DecimalFormat("#,###");
        return formatString(data, "#,###");
    }

    /**
     * 格式化字符串,根据指定格式
     *
     * @param data   需要格式化的数据
     * @param format 格式
     * @return 返回格式化后字符串.
     */
    public static String formatString(double data, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(data);
    }

}
