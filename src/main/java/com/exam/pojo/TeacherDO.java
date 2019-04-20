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
 * 教师表
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-31
 */
@TableName("ex_teacher")
@Data
public class TeacherDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教师id
     */
    @TableId(value = "teacher_id", type = IdType.INPUT)
    private String teacherId;

    /**
     * 工号
     */
    private String teacherNumber;

    /**
     * 账号
     */
    private String teacherUsername;

    /**
     * 密码
     */
    private String teacherPassword;

    /**
     * 身份证号
     */
    private String teacherCard;

    /**
     * 姓名
     */
    private String teacherName;

    /**
     * 性别，1男2女
     */
    private Integer teacherSex;

    /**
     * 年龄
     */
    private Integer teacherAge;

    /**
     * 职务，数据字典id
     */
    private String teacherJob;

    /**
     * 职称，数据字典id
     */
    private String teacherTitle;

    /**
     * 手机号
     */
    private String teacherMobile;

    /**
     * 邮箱
     */
    private String teacherEmail;

    /**
     * 照片
     */
    private String teacherImg;

    /**
     * 入职时间
     */
    private String teacherEntryTime;

    /**
     * 学院，数据字典id
     */
    private String teacherCollege;

    /**
     * 乐观锁
     */
    @Version
    private Integer teacherVersion;

    /**
     * 0删除1正常
     */
    @TableLogic
    private Integer teacherDelete;

    /**
     * 职务
     */
    @TableField(exist = false)
    private DictDO job;

    /**
     * 职称
     */
    @TableField(exist = false)
    private DictDO title;

    /**
     * 学院
     */
    @TableField(exist = false)
    private DictDO college;

    /**
     * 角色
     * @return
     */
    @TableField(exist = false)
    private List<String> roleList;

    @Override
    public String toString() {
        return "TeacherDO{" +
        "teacherId=" + teacherId +
        ", teacherNumber=" + teacherNumber +
        ", teacherUsername=" + teacherUsername +
        ", teacherPassword=" + teacherPassword +
        ", teacherCard=" + teacherCard +
        ", teacherName=" + teacherName +
        ", teacherSex=" + teacherSex +
        ", teacherAge=" + teacherAge +
        ", teacherJob=" + teacherJob +
        ", teacherTitle=" + teacherTitle +
        ", teacherMobile=" + teacherMobile +
        ", teacherEmail=" + teacherEmail +
        ", teacherImg=" + teacherImg +
        ", teacherEntryTime=" + teacherEntryTime +
        ", teacherCollege=" + teacherCollege +
        ", teacherVersion=" + teacherVersion +
        ", teacherDelete=" + teacherDelete +
        "}";
    }
}
