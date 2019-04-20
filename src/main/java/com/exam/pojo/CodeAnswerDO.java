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
 * 编程题-答案
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-19
 */
@TableName("ex_code_answer")
@Data
public class CodeAnswerDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "answer_id", type = IdType.INPUT)
    private String answerId;

    /**
     * 小题
     */
    private String answerProblem;

    /**
     * 答案内容
     */
    private String answerContent;

    /**
     * 答案编号
     */
    private Integer answerNumber;

    /**
     * 对应题目编号
     */
    private String answerCode;

    /**
     * 分值
     */
    private BigDecimal answerScore;

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
     * 0正常1删除
     */
    @TableLogic
    private Integer answerDelete;

    @Override
    public String toString() {
        return "CodeAnswerDO{" +
                "answerId=" + answerId +
                ", answerProblem=" + answerProblem +
                ", answerContent=" + answerContent +
                ", answerNumber=" + answerNumber +
                ", answerCode=" + answerCode +
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
        CodeAnswerDO that = (CodeAnswerDO) o;
        return Objects.equals(answerId, that.answerId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(answerId);
    }
}
