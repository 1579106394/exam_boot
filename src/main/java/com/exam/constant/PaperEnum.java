package com.exam.constant;

import lombok.Getter;

/**
 * 是否启用枚举
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/3/28 0028 上午 10:53
 */
@Getter
public enum PaperEnum {

    USE(1, "已启用"),
    NOT_USE(0, "未启用");
    private Integer code;
    private String msg;

    PaperEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
