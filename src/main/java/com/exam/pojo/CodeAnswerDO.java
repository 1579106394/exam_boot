package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 编程题-答案
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-17
 */
@TableName("ex_code_answer")
@Data
public class CodeAnswerDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "answer_id", type = IdType.AUTO)
    private String answerId;

    /**
     * 答案内容
     */
    private String answerContent;

    private String answerCode;

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
        ", answerContent=" + answerContent +
        ", answerCode=" + answerCode +
        ", answerResolve=" + answerResolve +
        ", answerVersion=" + answerVersion +
        ", answerDelete=" + answerDelete +
        "}";
    }
}
