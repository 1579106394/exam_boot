package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 数据字典表
college-学院
major-专业
job-职务
title-职称
subject-科目
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@Data
@TableName("ex_dict")
public class DictDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据字典id
     */
    @TableId(value = "dict_id", type = IdType.INPUT)
    private String dictId;

    /**
     * 数据字典名
     */
    private String dictName;

    /**
     * 数据字典类型
     */
    private String dictType;

    /**
     * 父级字典id
     */
    private String dictFather;

    /**
     * 乐观锁
     */
    @Version
    private Integer dictVersion;

    /**
     * 0已删除，1未删除
     */
    @TableLogic
    private Integer dictDelete;

    @TableField(exist = false)
    private DictDO father;

    @Override
    public String toString() {
        return "DictDO{" +
        "dictId=" + dictId +
        ", dictName=" + dictName +
        ", dictType=" + dictType +
        ", dictVersion=" + dictVersion +
        ", dictDelete=" + dictDelete +
        "}";
    }
}
