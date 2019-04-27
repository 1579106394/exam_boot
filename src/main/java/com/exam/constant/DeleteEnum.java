package com.exam.constant;

import lombok.Getter;

/**
 * 删除相关、枚举
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/3/28 0028 上午 10:49
 */
@Getter
public enum DeleteEnum {
    /**
     * 逻辑删除状态值
     */
    DELETE(0, "已删除"),
    NOT_DELETE(1, "未删除")
    ;

    private Integer code;
    private String msg;

    DeleteEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
