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
 * 角色表
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-01
 */
@TableName("ex_role")
@Data
public class RoleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableId(value = "role_id", type = IdType.INPUT)
    private String roleId;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 父级角色
     */
    private String roleFather;

    /**
     * 排序字段
     */
    private Integer roleIndex;

    /**
     * 角色描述
     */
    private String roleComment;

    /**
     * 乐观锁
     */
    @Version
    private Integer roleVersion;

    /**
     * 1正常0删除
     */
    @TableLogic
    private Integer roleDelete;

    @TableField(exist = false)
    private List<RoleDO> list;

    @Override
    public String toString() {
        return "RoleDO{" +
        "roleId=" + roleId +
        ", roleName=" + roleName +
        ", roleFather=" + roleFather +
        ", roleIndex=" + roleIndex +
        ", roleComment=" + roleComment +
        ", roleVersion=" + roleVersion +
        ", roleDelete=" + roleDelete +
        "}";
    }
}
