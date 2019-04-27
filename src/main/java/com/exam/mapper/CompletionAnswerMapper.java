package com.exam.mapper;

import com.exam.pojo.CompletionAnswerDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.pojo.CompletionDO;

/**
 * <p>
 * 填空题答案表 Mapper 接口
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-16
 */
public interface CompletionAnswerMapper extends BaseMapper<CompletionAnswerDO> {

    /**
     * 删除旧答案
     * @param completion
     */
    void deleteOldAnswer(CompletionDO completion);
}
