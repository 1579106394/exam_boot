package com.exam.mapper;

import com.exam.pojo.ChoiceAnswerDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.pojo.ChoiceDO;

/**
 * <p>
 * 选项表 Mapper 接口
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
public interface ChoiceAnswerMapper extends BaseMapper<ChoiceAnswerDO> {

    /**
     * 批量删除旧答案
     * @param choice
     */
    void deleteOldAnswer(ChoiceDO choice);
}
