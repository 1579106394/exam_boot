package com.exam.vo;

import com.exam.pojo.TypeDO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用于组卷的题目视图
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/4/21 0021 上午 9:53
 */
@Data
public class QuestionVO implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 题干
     */
    private String title;

    /**
     * 分值
     */
    private BigDecimal score;

    /**
     * 难度系数
     */
    private Integer difficulty;

    /**
     * 题型Id
     */
    private Integer typeId;

    /**
     * 题型
     */
    private TypeDO type;

}
