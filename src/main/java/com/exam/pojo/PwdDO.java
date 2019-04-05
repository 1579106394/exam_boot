package com.exam.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-31
 */
@TableName("ex_pwd")
@Data
public class PwdDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pwd_id", type = IdType.INPUT)
    private String pwdId;

    /**
     * 明文
     */
    private String pwdPlaintext;

    /**
     * 密文
     */
    private String pwdCiphertext;

    public PwdDO(String pwdId, String pwdPlaintext, String pwdCiphertext) {
        this.pwdId = pwdId;
        this.pwdPlaintext = pwdPlaintext;
        this.pwdCiphertext = pwdCiphertext;
    }

    public PwdDO() {
    }

    @Override
    public String toString() {
        return "PwdDO{" +
        "pwdId=" + pwdId +
        ", pwdPlaintext=" + pwdPlaintext +
        ", pwdCiphertext=" + pwdCiphertext +
        "}";
    }
}
