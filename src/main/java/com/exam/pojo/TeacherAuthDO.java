package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 教师-权限表
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-01
 */
@TableName("ex_teacher_auth")
public class TeacherAuthDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "ua_id", type = IdType.AUTO)
    private String uaId;

    /**
     * 教师id
     */
    private String uaTeacher;

    /**
     * 权限id
     */
    private String uaAuth;


    public String getUaId() {
        return uaId;
    }

    public void setUaId(String uaId) {
        this.uaId = uaId;
    }

    public String getUaTeacher() {
        return uaTeacher;
    }

    public void setUaTeacher(String uaTeacher) {
        this.uaTeacher = uaTeacher;
    }

    public String getUaAuth() {
        return uaAuth;
    }

    public void setUaAuth(String uaAuth) {
        this.uaAuth = uaAuth;
    }

    @Override
    public String toString() {
        return "TeacherAuthDO{" +
        "uaId=" + uaId +
        ", uaTeacher=" + uaTeacher +
        ", uaAuth=" + uaAuth +
        "}";
    }
}
