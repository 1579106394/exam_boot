package com.exam.vo;

import com.exam.pojo.PaperConfigQuestionDO;
import com.exam.pojo.TypeDO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 试卷配置-视图类
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/4/24 0024 下午 2:05
 */
@Data
public class PaperConfigVO implements Serializable {

    private String configId;

    /**
     * 所属试卷
     */
    private String configPaper;

    /**
     * 题目量
     */
    private Integer configQuestionNum;

    /**
     * 分值
     */
    private BigDecimal configScore;

    /**
     * 所属题型
     */
    private TypeDO type;

    /**
     * 知识点
     */
    private String configKnow;

    /**
     * 题目id列表
     */
    private List<PaperConfigQuestionDO> questionList;

    /**
     * 题目列表
     */
    private List questionDetailList;

}
