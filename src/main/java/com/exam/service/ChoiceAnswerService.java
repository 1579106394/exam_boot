package com.exam.service;

import com.exam.pojo.ChoiceAnswerDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.pojo.ChoiceDO;

/**
 * <p>
 * 选项表 服务类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
public interface ChoiceAnswerService extends IService<ChoiceAnswerDO> {

    /**
     * 删除旧答案
     * @param choice
     */
    void deleteOldAnswer(ChoiceDO choice);
}
