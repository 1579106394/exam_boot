package com.exam.vo;

import com.exam.pojo.BankDO;
import com.exam.pojo.DictDO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 展示一个试卷有哪些题目的视图类
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/4/24 0024 下午 1:43
 */
@Data
public class PaperQuestionVO implements Serializable {

    /**
     * 试卷id
     */
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
     * 学院
     */
    private DictDO college;

    /**
     * 专业
     */
    private DictDO major;

    /**
     * 题库
     */
    private BankDO bank;

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
     * 题量
     */
    private Integer paperQuestionNum;

    /**
     * 提交试卷后的下载链接
     */
    private String paperDownload;

    /**
     * 配置
     */
    private List<PaperConfigVO> configList;

    @Override
    public String toString() {
        return "PaperQuestionVO{" +
                "paperId='" + paperId + '\'' +
                ", paperTitle='" + paperTitle + '\'' +
                ", paperStartYear='" + paperStartYear + '\'' +
                ", paperEndYear='" + paperEndYear + '\'' +
                ", paperSeme=" + paperSeme +
                ", college=" + college +
                ", major=" + major +
                ", bank=" + bank +
                ", paperStyle='" + paperStyle + '\'' +
                ", paperType=" + paperType +
                ", paperDifficulty=" + paperDifficulty +
                ", paperScore=" + paperScore +
                ", paperQuestionNum=" + paperQuestionNum +
                ", paperDownload='" + paperDownload + '\'' +
                ", configList=" + configList +
                '}';
    }
}
