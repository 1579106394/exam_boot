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
 * 填空题答案表
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-16
 */
@TableName("ex_completion_answer")
@Data
public class CompletionAnswerDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "answer_id", type = IdType.INPUT)
    private String answerId;

    /**
     * 答案编号,1,2,3...
     */
    private Integer answerNumber;

    /**
     * 答案内容
     */
    private String answerContent;

    /**
     * 解析
     */
    private String answerResolve;

    /**
     * 所属填空题
     */
    private String answerComp;

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
        return "CompletionAnswerDO{" +
        "answerId=" + answerId +
        ", answerNumber=" + answerNumber +
        ", answerContent=" + answerContent +
        ", answerResolve=" + answerResolve +
        ", answerVersion=" + answerVersion +
        ", answerDelete=" + answerDelete +
        "}";
    }
}
