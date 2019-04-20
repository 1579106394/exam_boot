package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 编译器表
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-19
 */
@TableName("ex_compile")
@Data
public class CompileDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "compile_id", type = IdType.INPUT)
    private String compileId;

    /**
     * 编译器编号
     */
    private Integer compileNumber;

    /**
     * 编译器名
     */
    private String compileName;

    @Override
    public String toString() {
        return "CompileDO{" +
        "compileId=" + compileId +
        ", compileNumber=" + compileNumber +
        ", compileName=" + compileName +
        "}";
    }
}
