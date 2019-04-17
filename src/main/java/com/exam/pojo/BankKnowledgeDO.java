package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 知识点表
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-11
 */
@TableName("ex_bank_knowledge")
@Data
public class BankKnowledgeDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "know_id", type = IdType.INPUT)
    private String knowId;

    /**
     * 知识点名
     */
    private String knowName;

    /**
     * 题库id
     */
    private String knowBank;

    /**
     * 乐观锁
     */
    @Version
    private Integer knowVersion;

    /**
     * 1正常0删除
     */
    @TableLogic
    private Integer knowDelete;

    @Override
    public String toString() {
        return "BankKnowledgeDO{" +
        "knowId=" + knowId +
        ", knowName=" + knowName +
        ", knowBank=" + knowBank +
        ", knowVersion=" + knowVersion +
        ", knowDelete=" + knowDelete +
        "}";
    }
}
