package com.exam.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 其他题型
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-12
 */
@TableName("ex_question")
public class QuestionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "question_id", type = IdType.AUTO)
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
     * 乐观锁
     */
    private Integer questionVersion;

    /**
     * 0删除1正常
     */
    private Integer questionDelete;


    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionImg() {
        return questionImg;
    }

    public void setQuestionImg(String questionImg) {
        this.questionImg = questionImg;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public BigDecimal getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(BigDecimal questionScore) {
        this.questionScore = questionScore;
    }

    public Integer getQuestionDifficulty() {
        return questionDifficulty;
    }

    public void setQuestionDifficulty(Integer questionDifficulty) {
        this.questionDifficulty = questionDifficulty;
    }

    public String getQuestionBank() {
        return questionBank;
    }

    public void setQuestionBank(String questionBank) {
        this.questionBank = questionBank;
    }

    public Integer getQuestionStyle() {
        return questionStyle;
    }

    public void setQuestionStyle(Integer questionStyle) {
        this.questionStyle = questionStyle;
    }

    public String getQuestionKnow() {
        return questionKnow;
    }

    public void setQuestionKnow(String questionKnow) {
        this.questionKnow = questionKnow;
    }

    public Integer getQuestionVersion() {
        return questionVersion;
    }

    public void setQuestionVersion(Integer questionVersion) {
        this.questionVersion = questionVersion;
    }

    public Integer getQuestionDelete() {
        return questionDelete;
    }

    public void setQuestionDelete(Integer questionDelete) {
        this.questionDelete = questionDelete;
    }

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
