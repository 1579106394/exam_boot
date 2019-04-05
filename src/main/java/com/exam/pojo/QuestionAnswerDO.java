package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 其他题目答案
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@Data
@TableName("ex_question_answer")
public class QuestionAnswerDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "answer_id", type = IdType.AUTO)
    private String answerId;

    /**
     * 答案
     */
    private String answerContent;

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
        ", answerContent=" + answerContent +
        ", answerQuestion=" + answerQuestion +
        ", answerResolve=" + answerResolve +
        ", answerVersion=" + answerVersion +
        ", answerDelete=" + answerDelete +
        "}";
    }
}
