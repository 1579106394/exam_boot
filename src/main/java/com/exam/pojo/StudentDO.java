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
 * 学生表
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@Data
@TableName("ex_student")
public class StudentDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "stu_id", type = IdType.INPUT)
    private String stuId;

    /**
     * 学号
     */
    private String stuNumber;

    /**
     * 密码
     */
    private String stuPassword;

    /**
     * 姓名
     */
    private String stuName;

    /**
     * 1男2女
     */
    private Integer stuSex;

    /**
     * 年龄
     */
    private Integer stuAge;

    /**
     * 学院，数据字典id
     */
    private String stuCollege;

    /**
     * 专业，数据字典id
     */
    private String stuMajor;

    /**
     * 照片
     */
    private String stuImg;

    /**
     * 身份证号码
     */
    private String stuCard;

    /**
     * 入学时间
     */
    private String stuEntranceTime;

    /**
     * 乐观锁
     */
    @Version
    private Integer stuVersion;

    /**
     * 0删除1正常
     */
    @TableLogic
    private Integer stuDelete;

    /**
     * 学院
     */
    @TableField(exist = false)
    private DictDO  college;

    /**
     * 专业
     */
    @TableField(exist = false)
    private DictDO major;

    @Override
    public String toString() {
        return "StudentDO{" +
        "stuId=" + stuId +
        ", stuNumber=" + stuNumber +
        ", stuPassword=" + stuPassword +
        ", stuName=" + stuName +
        ", stuSex=" + stuSex +
        ", stuAge=" + stuAge +
        ", stuCollege=" + stuCollege +
        ", stuMajor=" + stuMajor +
        ", stuImg=" + stuImg +
        ", stuCard=" + stuCard +
        ", stuEntranceTime=" + stuEntranceTime +
        ", stuVersion=" + stuVersion +
        ", stuDelete=" + stuDelete +
        "}";
    }
}
