package com.exam.constant;

import lombok.Getter;

/**
 * 试卷提交状态
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/4/24 0024 下午 8:01
 */
@Getter
public enum SubmitConstant {

    /**
     * 提交状态
     */
    SUBMIT(1, "已提交"),
    NOT_SUBMIT(0, "未提交")
    ;

    private Integer code;
    private String msg;

    SubmitConstant(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
