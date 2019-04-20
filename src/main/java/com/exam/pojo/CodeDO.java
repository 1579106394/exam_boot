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
 * 编程题
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-17
 */
@TableName("ex_code")
@Data
public class CodeDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "code_id", type = IdType.INPUT)
    private String codeId;

    /**
     * 题干
     */
    private String codeTitle;

    /**
     * 图片
     */
    private String codeImgs;

    /**
     * 所属题型
     */
    private String codeType;

    /**
     * 分值
     */
    private BigDecimal codeScore;

    /**
     * 难度系数
     */
    private Integer codeDifficulty;

    /**
     * 所属题库
     */
    private String codeBank;

    /**
     * 所属知识点
     */
    private String codeKnow;

    /**
     * 解析
     */
    private String codeResolve;

    /**
     * 编译器，Java、C/C++、Python等
     */
    private String codeCompile;

    /**
     * 乐观锁
     */
    @Version
    private Integer codeVersion;

    /**
     * 1正常0删除
     */
    @TableLogic
    private Integer codeDelete;

    @TableField(exist = false)
    private List<CodeAnswerDO> answerList;

    @TableField(exist = false)
    private CompileDO compileDO;

    @Override
    public String toString() {
        return "CodeDO{" +
        "codeId=" + codeId +
        ", codeTitle=" + codeTitle +
        ", codeImgs=" + codeImgs +
        ", codeType=" + codeType +
        ", codeScore=" + codeScore +
        ", codeDifficulty=" + codeDifficulty +
        ", codeBank=" + codeBank +
        ", codeKnow=" + codeKnow +
        ", codeCompile=" + codeCompile +
        ", codeVersion=" + codeVersion +
        ", codeDelete=" + codeDelete +
        "}";
    }
}
