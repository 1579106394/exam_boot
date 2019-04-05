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
 * 试卷表
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@Data
@TableName("ex_paper")
public class PaperDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "paper_id", type = IdType.AUTO)
    private String paperId;

    /**
     * 试卷标题
     */
    private String paperTitle;

    /**
     * 起始年度
     */
    private String paperStartYear;

    /**
     * 结束年度
     */
    private String paperEndYear;

    /**
     * 备注
     */
    private String paperComment;

    /**
     * 学院，数据字典id
     */
    private String paperCollege;

    /**
     * 专业，数据字典id
     */
    private String paperMajor;

    /**
     * 题库id
     */
    private String paperBank;

    /**
     * 0未启用，1一起用
     */
    private Integer paperFlag;

    /**
     * 组卷类型，1手动组卷，2智能组卷
     */
    private Integer paperType;

    /**
     * 难度系数
     */
    private Integer paperDifficulty;

    /**
     * 总分
     */
    private BigDecimal paperScore;

    /**
     * 创建时间
     */
    private String paperCreateTime;

    /**
     * 乐观锁
     */
    @Version
    private Integer paperVersion;

    /**
     * 0删除1正常
     */
    @TableLogic
    private Integer paperDelete;

    @Override
    public String toString() {
        return "PaperDO{" +
        "paperId=" + paperId +
        ", paperTitle=" + paperTitle +
        ", paperStartYear=" + paperStartYear +
        ", paperEndYear=" + paperEndYear +
        ", paperComment=" + paperComment +
        ", paperCollege=" + paperCollege +
        ", paperMajor=" + paperMajor +
        ", paperBank=" + paperBank +
        ", paperFlag=" + paperFlag +
        ", paperType=" + paperType +
        ", paperDifficulty=" + paperDifficulty +
        ", paperScore=" + paperScore +
        ", paperCreateTime=" + paperCreateTime +
        ", paperVersion=" + paperVersion +
        ", paperDelete=" + paperDelete +
        "}";
    }
}
