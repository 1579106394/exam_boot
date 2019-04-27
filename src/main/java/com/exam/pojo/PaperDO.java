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
 * 试卷表
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-20
 */
@TableName("ex_paper")
@Data
public class PaperDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "paper_id", type = IdType.INPUT)
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
     * 学期
     */
    private Integer paperSeme;

    /**
     * 备注
     */
    private String paperComment;

    /**
     * 学院，数据字典id
     */
    private String paperCollege;

    /**
     * 学院
     */
    @TableField(exist = false)
    private DictDO college;

    /**
     * 专业，数据字典id
     */
    private String paperMajor;

    /**
     * 专业
     */
    @TableField(exist = false)
    private DictDO major;

    /**
     * 题库id
     */
    private String paperBank;

    /**
     * 题库
     */
    @TableField(exist = false)
    private BankDO bank;

    /**
     * 0未启用，1已启用
     */
    private Integer paperFlag;

    /**
     * ABC卷
     */
    private String paperStyle;

    /**
     * 组卷类型，0未组卷，1手动组卷，2智能组卷
     */
    private Integer paperType;

    /**
     * 难度系数
     */
    private BigDecimal paperDifficulty;

    /**
     * 总分
     */
    private BigDecimal paperScore;

    /**
     * 创建时间
     */
    private String paperCreateTime;

    /**
     * 更新时间
     */
    private String paperUpdateTime;

    /**
     * 题量
     */
    private Integer paperQuestionNum;

    /**
     * 是否提交组卷，0未提交，1已提交
     */
    private Integer paperSubmit;

    /**
     * 提交试卷后的下载链接
     */
    private String paperDownload;

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

    /**
     * 试卷配置表
     */
    @TableField(exist = false)
    private List<PaperConfigDO> configList;

    @Override
    public String toString() {
        return "PaperDO{" +
                "paperId='" + paperId + '\'' +
                ", paperTitle='" + paperTitle + '\'' +
                ", paperStartYear='" + paperStartYear + '\'' +
                ", paperEndYear='" + paperEndYear + '\'' +
                ", paperSeme=" + paperSeme +
                ", paperComment='" + paperComment + '\'' +
                ", paperCollege='" + paperCollege + '\'' +
                ", college=" + college +
                ", paperMajor='" + paperMajor + '\'' +
                ", major=" + major +
                ", paperBank='" + paperBank + '\'' +
                ", bank=" + bank +
                ", paperFlag=" + paperFlag +
                ", paperStyle='" + paperStyle + '\'' +
                ", paperType=" + paperType +
                ", paperDifficulty=" + paperDifficulty +
                ", paperScore=" + paperScore +
                ", paperCreateTime='" + paperCreateTime + '\'' +
                ", paperUpdateTime='" + paperUpdateTime + '\'' +
                ", paperQuestionNum=" + paperQuestionNum +
                ", paperSubmit=" + paperSubmit +
                ", paperDownload='" + paperDownload + '\'' +
                ", paperVersion=" + paperVersion +
                ", paperDelete=" + paperDelete +
                ", configList=" + configList +
                '}';
    }
}
