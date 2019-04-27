package com.exam.constant;

import java.util.regex.Pattern;

/**
 * 正则表达式常量
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/4/17 0017 下午 8:46
 */
public class PatternConstant {

    /**
     * 三个下划线的正则
     */
    public static final Pattern THREE_UNDER_LINE_PATTERN = Pattern.compile("_{3,}");

    /**
     * A-Z正则
     */
    public static final Pattern A2Z_PATTERN = Pattern.compile("[A-Z]");

    /**
     * 微信号正则
     */
    public static final Pattern WE_CHAT_PATTERN = Pattern.compile("^[a-zA-Z][-_a-zA-Z0-9]{4,19}+$");

    /**
     * IP正则
     */
    public static final Pattern IP_PATTERN = Pattern.compile("(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})");


}
