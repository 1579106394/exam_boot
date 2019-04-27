package com.exam.constant;

import lombok.Getter;

/**
 * 组卷方式
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/3/28 0028 上午 10:55
 */
@Getter
public enum TestEnum {

    /**
     * 组卷方式状态码
     */
    NO(0, "未组卷"),
    MANUAL(1, "手动组卷"),
    INTELLIGENCE(2, "智能组卷")
    ;
    private Integer code;
    private String msg;

    TestEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
