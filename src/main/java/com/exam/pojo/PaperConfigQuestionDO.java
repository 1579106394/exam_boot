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
 * 试卷配置-题目表
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@Data
@TableName("ex_paper_config_question")
public class PaperConfigQuestionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 配置id
     */
    private String questionConfig;

    /**
     * 题目id
     */
    private String questionId;

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

    @Override
    public String toString() {
        return "PaperConfigQuestionDO{" +
                "id=" + id +
                ", questionConfig=" + questionConfig +
                ", questionId=" + questionId +
                ", questionVersion=" + questionVersion +
                ", questionDelete=" + questionDelete +
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
        PaperConfigQuestionDO that = (PaperConfigQuestionDO) o;
        return Objects.equals(questionConfig, that.questionConfig) &&
                Objects.equals(questionId, that.questionId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(questionConfig, questionId);
    }
}
