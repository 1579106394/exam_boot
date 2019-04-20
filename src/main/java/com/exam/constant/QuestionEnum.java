package com.exam.constant;

import lombok.Getter;

/**
 * 正确错误
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/3/28 0028 上午 10:53
 */
@Getter
public enum QuestionEnum {

    /**
     * 题目正确错误状态值
     */
    TRUE(1, "正确"),
    FALSE(0, "错误")
    ;
    private Integer code;
    private String msg;

    QuestionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
