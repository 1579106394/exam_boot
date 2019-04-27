package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 教师-角色表
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-05
 */
@TableName("ex_teacher_role")
@Data
public class TeacherRoleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "tr_id", type = IdType.INPUT)
    private String trId;

    /**
     * 教师id
     */
    private String trTeacher;

    /**
     * 角色id
     */
    private String trRole;

    @Override
    public String toString() {
        return "TeacherRoleDO{" +
        "trId=" + trId +
        ", trTeacher=" + trTeacher +
        ", trRole=" + trRole +
        "}";
    }
}
