package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 题库-题型对应表
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-30
 */
@TableName("ex_bank_type")
@Data
public class BankTypeDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 题库id
     */
    private String bankId;

    /**
     * 题型id
     */
    private String bankType;

    /**
     * 知识点id
     */
    private String bankKnow;

    /**
     * 乐观锁
     */
    @Version
    private Integer bankVersion;

    /**
     * 0删除1正常
     */
    @TableLogic
    private Integer bankDelete;

    /**
     * 题型
     * @return
     */
    @TableField(exist = false)
    private TypeDO type;

    @Override
    public String toString() {
        return "BankTypeDO{" +
        "id=" + id +
        ", bankId=" + bankId +
        ", bankType=" + bankType +
        ", bankVersion=" + bankVersion +
        ", bankDelete=" + bankDelete +
        "}";
    }
}
