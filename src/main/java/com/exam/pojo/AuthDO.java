package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-01
 */
@TableName("ex_auth")
public class AuthDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    @TableId(value = "auth_id", type = IdType.AUTO)
    private String authId;

    /**
     * 权限名，用于展示
     */
    private String authName;

    /**
     * 权限代码，用于后台写代码用
     */
    private String authCode;

    /**
     * 父级权限
     */
    private String authFather;

    /**
     * 菜单名
     */
    private String authMenu;

    /**
     * url
     */
    private String authUrl;

    /**
     * 排序字段
     */
    private Integer authIndex;

    /**
     * 是否生成菜单，1生成0不生成
     */
    private Integer authIsmenu;

    /**
     * 乐观锁
     */
    private Integer authVersion;

    /**
     * 1正常0删除
     */
    private Integer authDelete;


    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthFather() {
        return authFather;
    }

    public void setAuthFather(String authFather) {
        this.authFather = authFather;
    }

    public String getAuthMenu() {
        return authMenu;
    }

    public void setAuthMenu(String authMenu) {
        this.authMenu = authMenu;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public Integer getAuthIndex() {
        return authIndex;
    }

    public void setAuthIndex(Integer authIndex) {
        this.authIndex = authIndex;
    }

    public Integer getAuthIsmenu() {
        return authIsmenu;
    }

    public void setAuthIsmenu(Integer authIsmenu) {
        this.authIsmenu = authIsmenu;
    }

    public Integer getAuthVersion() {
        return authVersion;
    }

    public void setAuthVersion(Integer authVersion) {
        this.authVersion = authVersion;
    }

    public Integer getAuthDelete() {
        return authDelete;
    }

    public void setAuthDelete(Integer authDelete) {
        this.authDelete = authDelete;
    }

    @Override
    public String toString() {
        return "AuthDO{" +
        "authId=" + authId +
        ", authName=" + authName +
        ", authCode=" + authCode +
        ", authFather=" + authFather +
        ", authMenu=" + authMenu +
        ", authUrl=" + authUrl +
        ", authIndex=" + authIndex +
        ", authIsmenu=" + authIsmenu +
        ", authVersion=" + authVersion +
        ", authDelete=" + authDelete +
        "}";
    }
}
