package com.exam.service;

import com.exam.pojo.QuestionAnswerDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.pojo.QuestionDO;

/**
 * <p>
 * 其他题目答案 服务类
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-18
 */
public interface QuestionAnswerService extends IService<QuestionAnswerDO> {

    /**
     * 删除旧小题
     * @param question
     */
    void deleteOldAnswer(QuestionDO question);
}
