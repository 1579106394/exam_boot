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
 * 题型表
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@Data
@TableName("ex_type")
public class TypeDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 题型id
     */
    @TableId(value = "type_id", type = IdType.INPUT)
    private String typeId;

    /**
     * 题型名称
     */
    private String typeName;

    /**
     * 乐观锁
     */
    @Version
    private Integer typeVersion;

    /**
     * 0删除1正常
     */
    @TableLogic
    private Integer typeDelete;

    @TableField(exist = false)
    private String bankId;

    @Override
    public String toString() {
        return "TypeDO{" +
        "typeId=" + typeId +
        ", typeName=" + typeName +
        ", typeVersion=" + typeVersion +
        ", typeDelete=" + typeDelete +
        "}";
    }
}
