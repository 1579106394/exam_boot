package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 题库表
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@TableName("ex_bank")
@Data
public class BankDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "bank_id", type = IdType.INPUT)
    private String bankId;

    /**
     * 题库名称
     */
    private String bankName;

    /**
     * 图片
     */
    private String bankImg;

    /**
     * 学院，数据字典id
     */
    private String bankCollege;

    /**
     * 科目。数据字典id
     */
    private String bankSubject;

    /**
     * 乐观锁
     */
    @Version
    private Integer bankVersion;

    /**
     * 0正常1删除
     */
    @TableLogic
    private Integer bankDelete;

    /**
     * 所属学院
     * @return
     */
    @TableField(exist = false)
    private DictDO college;

    /**
     * 所属科目
     * @return
     */
    @TableField(exist = false)
    private DictDO subject;

    /**
     * 题型列表
     * @return
     */
    @TableField(exist = false)
    private List<TypeDO> typeList;

    @Override
    public String toString() {
        return "BankDO{" +
        "bankId=" + bankId +
        ", bankName=" + bankName +
        ", bankImg=" + bankImg +
        ", bankCollege=" + bankCollege +
        ", bankSubject=" + bankSubject +
        ", bankVersion=" + bankVersion +
        ", bankDelete=" + bankDelete +
        "}";
    }
}
