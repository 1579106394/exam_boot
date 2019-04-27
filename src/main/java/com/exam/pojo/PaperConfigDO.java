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
import java.util.Objects;

/**
 * <p>
 * 试卷配置表
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-12
 */
@TableName("ex_paper_config")
@Data
public class PaperConfigDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "config_id", type = IdType.INPUT)
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
     * 题型
     */
    private TypeDO type;

    /**
     * 知识点
     */
    private String configKnow;

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

    /**
     * 对应的题目
     */
    @TableField(exist = false)
    private List<PaperConfigQuestionDO> questionList;

    /**
     * 题目详细列表
     */
    @TableField(exist = false)
    private List questionDetailList;

    @Override
    public String toString() {
        return "PaperConfigDO{" +
                "configId='" + configId + '\'' +
                ", configPaper='" + configPaper + '\'' +
                ", configQuestionNum=" + configQuestionNum +
                ", configScore=" + configScore +
                ", configType='" + configType + '\'' +
                ", type=" + type +
                ", configKnow='" + configKnow + '\'' +
                ", configVersion=" + configVersion +
                ", configDelete=" + configDelete +
                ", questionList=" + questionList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaperConfigDO that = (PaperConfigDO) o;
        return Objects.equals(configId, that.configId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(configId);
    }
}
