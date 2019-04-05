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
 * 试卷配置表
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@Data
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
     * 乐观锁
     */
    @Version
    private Integer configVersion;

    /**
     * 0删除1正常
     */
    @TableLogic
    private Integer configDelete;

    @Override
    public String toString() {
        return "PaperConfigDO{" +
        "configId=" + configId +
        ", configPaper=" + configPaper +
        ", configQuestionNum=" + configQuestionNum +
        ", configScore=" + configScore +
        ", configType=" + configType +
        ", configVersion=" + configVersion +
        ", configDelete=" + configDelete +
        "}";
    }
}
