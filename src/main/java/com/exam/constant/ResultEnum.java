package com.exam.constant;

import lombok.Getter;

/**
 * 返回状态码
 *
 * @author 杨德石
 */
@Getter
public enum ResultEnum {

    SUCCESS(200, "操作成功"),
    ERROR(400, "操作失败"),;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
