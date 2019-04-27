package com.exam.constant;

import lombok.Getter;

/**
 * 性别
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/3/28 0028 上午 10:51
 */
@Getter
public enum SexEnum {

    /**
     * 性别状态码
     */
    MAN(1, "男"),
    WOMAN(2, "女")
    ;

    private Integer code;
    private String msg;

    SexEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
