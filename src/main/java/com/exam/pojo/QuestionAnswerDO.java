package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>
 * 其他题目答案
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-18
 */
@TableName("ex_question_answer")
@Data
public class QuestionAnswerDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "answer_id", type = IdType.INPUT)
    private String answerId;

    /**
     * 小题编号
     */
    private Integer answerNumber;

    /**
     * 问题内容
     */
    private String answerProblem;

    /**
     * 答案
     */
    private String answerContent;

    /**
     * 分值
     */
    private BigDecimal answerScore;

    /**
     * 题目id
     */
    private String answerQuestion;

    /**
     * 解析
     */
    private String answerResolve;

    /**
     * 乐观锁
     */
    @Version
    private Integer answerVersion;

    /**
     * 0删除1正常
     */
    @TableLogic
    private Integer answerDelete;

    @Override
    public String toString() {
        return "QuestionAnswerDO{" +
                "answerId=" + answerId +
                ", answerProblem=" + answerProblem +
                ", answerContent=" + answerContent +
                ", answerScore=" + answerScore +
                ", answerQuestion=" + answerQuestion +
                ", answerResolve=" + answerResolve +
                ", answerVersion=" + answerVersion +
                ", answerDelete=" + answerDelete +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionAnswerDO that = (QuestionAnswerDO) o;
        return Objects.equals(answerId, that.answerId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(answerId);
    }
}
