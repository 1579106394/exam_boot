package com.exam.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用来显示试卷有多少题目的视图类
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/4/23 0023 下午 9:00
 */
@Data
public class PaperQuestionNumVO implements Serializable {

    /**
     * 题型编号
     */
    private Integer configType;

    /**
     * 题型名
     */
    private String typeName;

    /**
     * 题目数
     */
    private Integer questionNum;

    /**
     * 总分数
     */
    private BigDecimal questionScore;

}
