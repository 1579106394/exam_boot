package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 选项表
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@TableName("ex_choice_answer")
@Data
public class ChoiceAnswerDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "answer_id", type = IdType.INPUT)
    private String answerId;

    /**
     * 选项，ABCDEFG
     */
    private String answerNumber;

    /**
     * 选项内容
     */
    private String answerContent;

    /**
     * 所属选择题id
     */
    private String answerChoice;

    /**
     * 1正确0错误
     */
    private Boolean answerTrue;

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
        return "ChoiceAnswerDO{" +
                "answerId=" + answerId +
                ", answerNumber=" + answerNumber +
                ", answerContent=" + answerContent +
                ", answerChoice=" + answerChoice +
                ", answerTrue=" + answerTrue +
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
        ChoiceAnswerDO that = (ChoiceAnswerDO) o;
        return Objects.equals(answerId, that.answerId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(answerId);
    }
}
