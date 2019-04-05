package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 填空题表
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@Data
@TableName("ex_completion")
public class CompletionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "comp_id", type = IdType.AUTO)
    private String compId;

    /**
     * 内容
     */
    private String compContent;

    /**
     * 分值
     */
    private BigDecimal compScore;

    /**
     * 难度系数
     */
    private Integer compDifficulty;

    /**
     * 所属题库
     */
    private String compBank;

    /**
     * 乐观锁
     */
    @Version
    private Integer compVersion;

    /**
     * 0删除1正常
     */
    @TableLogic
    private Integer compDelete;

    @Override
    public String toString() {
        return "CompletionDO{" +
        "compId=" + compId +
        ", compContent=" + compContent +
        ", compScore=" + compScore +
        ", compDifficulty=" + compDifficulty +
        ", compBank=" + compBank +
        ", compVersion=" + compVersion +
        ", compDelete=" + compDelete +
        "}";
    }
}
