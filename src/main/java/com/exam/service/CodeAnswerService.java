package com.exam.service;

import com.exam.pojo.CodeAnswerDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.pojo.CodeDO;

/**
 * <p>
 * 编程题-答案 服务类
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-19
 */
public interface CodeAnswerService extends IService<CodeAnswerDO> {

    /**
     * 删除旧答案
     * @param codeDO
     */
    void deleteOldAnswer(CodeDO codeDO);
}
