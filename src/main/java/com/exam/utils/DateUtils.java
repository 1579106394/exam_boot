package com.exam.utils;

import com.exam.constant.OtherConstant;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author
 */
public abstract class DateUtils {

    /**
     * 默认日期格式
     * @return
     */
    public static String newDate() {
        SimpleDateFormat sdf = OtherConstant.DEFAULT_DATETIME_FORMATTER;
        String time = sdf.format(new Date());
        return time;
    }

    /**
     * 指定日期格式
     * @return
     */
    public static String newDateByFormat(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String time = sdf.format(new Date());
        return time;
    }

    /**
     * 计算日期相差天数
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public static long diffTime(String startDate, String endDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long a = sdf.parse(endDate).getTime()-sdf.parse(startDate).getTime();
        Long count = a/1000/60/60/24;
        return count;
    }

}
