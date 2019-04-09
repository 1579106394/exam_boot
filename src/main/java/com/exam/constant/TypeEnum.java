package com.exam.constant;

import lombok.Getter;

/**
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/3/30 0030 下午 2:11
 */
@Getter
public enum TypeEnum {

    ONE_CHOICE(1, "单项选择题"),
    MANY_CHOICE(2, "多项选择题"),
    COMPLETION(4, "填空题"),
    JUDGEMENT(3, "判断题"),
    PROGRAMMING(5, "编程题"),
    OTHER(6, "其他题")
    ;
    private Integer code;
    private String msg;

    TypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
