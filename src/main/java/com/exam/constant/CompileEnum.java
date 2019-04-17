package com.exam.constant;

/**
 * 编译器枚举
 * @version 1.0
 * @author:
 * @date: 2019/4/17 0017 下午 8:15
 */
public enum CompileEnum {
    JAVA(1, "JAVA编译器"),
    C(2, "C/C++编译器"),
    PYTHON(3, "Python编译器"),
    JS(4, "JavaScript编译器")
    ;

    private Integer code;
    private String msg;

    CompileEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
