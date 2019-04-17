package com.exam.service;

import com.exam.pojo.CompletionAnswerDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.pojo.CompletionDO;

/**
 * <p>
 * 填空题答案表 服务类
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-16
 */
public interface CompletionAnswerService extends IService<CompletionAnswerDO> {

    /**
     * 修改题目，删除前端传进来的不存在的答案
     * @param completion
     */
    void deleteOldAnswer(CompletionDO completion);
}
