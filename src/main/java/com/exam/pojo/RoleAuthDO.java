package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 角色-权限表
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-01
 */
@TableName("ex_role_auth")
public class RoleAuthDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "ra_id", type = IdType.AUTO)
    private String raId;

    /**
     * 角色id
     */
    private String raRole;

    /**
     * 权限id
     */
    private String raAuth;


    public String getRaId() {
        return raId;
    }

    public void setRaId(String raId) {
        this.raId = raId;
    }

    public String getRaRole() {
        return raRole;
    }

    public void setRaRole(String raRole) {
        this.raRole = raRole;
    }

    public String getRaAuth() {
        return raAuth;
    }

    public void setRaAuth(String raAuth) {
        this.raAuth = raAuth;
    }

    @Override
    public String toString() {
        return "RoleAuthDO{" +
        "raId=" + raId +
        ", raRole=" + raRole +
        ", raAuth=" + raAuth +
        "}";
    }
}
