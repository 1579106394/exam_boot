package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 试卷配置表
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-12
 */
@TableName("ex_paper_config")
public class PaperConfigDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "config_id", type = IdType.AUTO)
    private String configId;

    /**
     * 所属试卷
     */
    private String configPaper;

    /**
     * 题目量
     */
    private Integer configQuestionNum;

    /**
     * 分值
     */
    private BigDecimal configScore;

    /**
     * 所属题型
     */
    private String configType;

    /**
     * 知识点
     */
    private String configKnow;

    /**
     * 乐观锁
     */
    private Integer configVersion;

    /**
     * 0删除1正常
     */
    private Integer configDelete;


    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getConfigPaper() {
        return configPaper;
    }

    public void setConfigPaper(String configPaper) {
        this.configPaper = configPaper;
    }

    public Integer getConfigQuestionNum() {
        return configQuestionNum;
    }

    public void setConfigQuestionNum(Integer configQuestionNum) {
        this.configQuestionNum = configQuestionNum;
    }

    public BigDecimal getConfigScore() {
        return configScore;
    }

    public void setConfigScore(BigDecimal configScore) {
        this.configScore = configScore;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigKnow() {
        return configKnow;
    }

    public void setConfigKnow(String configKnow) {
        this.configKnow = configKnow;
    }

    public Integer getConfigVersion() {
        return configVersion;
    }

    public void setConfigVersion(Integer configVersion) {
        this.configVersion = configVersion;
    }

    public Integer getConfigDelete() {
        return configDelete;
    }

    public void setConfigDelete(Integer configDelete) {
        this.configDelete = configDelete;
    }

    @Override
    public String toString() {
        return "PaperConfigDO{" +
        "configId=" + configId +
        ", configPaper=" + configPaper +
        ", configQuestionNum=" + configQuestionNum +
        ", configScore=" + configScore +
        ", configType=" + configType +
        ", configKnow=" + configKnow +
        ", configVersion=" + configVersion +
        ", configDelete=" + configDelete +
        "}";
    }
}
