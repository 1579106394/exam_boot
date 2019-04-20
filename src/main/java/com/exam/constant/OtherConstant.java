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
     * localhost
     */
    public final static String LOCAL_HOST = "localhost";

    /**
     * 文件上传路径
     */
    public final static String UPLOAD_URL = "C:\\exam\\";

    /**
     * 服务器地址
     */
    public final static String SERVER_URL = "http://tn20898453.51mypc.cn:48217/file/";

    /**
     * redis过期时间
     */
    public final static Long REDIS_TIMEOUT = 3600000L;

    /**
     * 分页每页默认显示条数
     */
    public final static Integer CURRENT_COUNT = 10;

}
