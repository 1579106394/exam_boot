package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 其他题型
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-12
 */
@TableName("ex_question")
@Data
public class QuestionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "question_id", type = IdType.INPUT)
    private String questionId;

    /**
     * 题干
     */
    private String questionTitle;

    /**
     * 题目图片
     */
    private String questionImg;

    /**
     * 所属题型
     */
    private String questionType;

    /**
     * 分值
     */
    private BigDecimal questionScore;

    /**
     * 难度系数
     */
    private Integer questionDifficulty;

    /**
     * 所属题库
     */
    private String questionBank;

    /**
     * 题目类型，1选择，2多选，3判断，4填空，5编程，6其他
     */
    private Integer questionStyle;

    /**
     * 知识点
     */
    private String questionKnow;

    /**
     * 解析
     */
    private String questionResolve;

    /**
     * 乐观锁
     */
    @Version
    private Integer questionVersion;

    /**
     * 0删除1正常
     */
    @TableLogic
    private Integer questionDelete;

    /**
     * 问题/答案列表(简答题可能多问)
     * @return
     */
    @TableField(exist = false)
    private List<QuestionAnswerDO> answerList;

    @Override
    public String toString() {
        return "QuestionDO{" +
        "questionId=" + questionId +
        ", questionTitle=" + questionTitle +
        ", questionImg=" + questionImg +
        ", questionType=" + questionType +
        ", questionScore=" + questionScore +
        ", questionDifficulty=" + questionDifficulty +
        ", questionBank=" + questionBank +
        ", questionStyle=" + questionStyle +
        ", questionKnow=" + questionKnow +
        ", questionVersion=" + questionVersion +
        ", questionDelete=" + questionDelete +
        "}";
    }
}
