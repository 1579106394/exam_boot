package com.exam.constant;

import java.text.SimpleDateFormat;

/**
 * 其他常量
 * @author 杨德石
 */
public class OtherConstant {

    private OtherConstant() {}

    /**
     * 默认日期格式化
     */
    public final static SimpleDateFormat DEFAULT_DATETIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 年月日格式化
     */
    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-d");

    /**
     * localhost
     */
    public final static String LOCAL_HOST = "localhost";

}
