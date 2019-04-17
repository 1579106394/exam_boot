package com.exam.service.impl;

import com.exam.pojo.CompletionAnswerDO;
import com.exam.mapper.CompletionAnswerMapper;
import com.exam.pojo.CompletionDO;
import com.exam.service.CompletionAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 填空题答案表 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-16
 */
@Service
public class CompletionAnswerServiceImpl extends ServiceImpl<CompletionAnswerMapper, CompletionAnswerDO> implements CompletionAnswerService {

    @Autowired
    private CompletionAnswerMapper completionAnswerMapper;

    /**
     * 删除旧答案
     * @param completion
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOldAnswer(CompletionDO completion) {
        completionAnswerMapper.deleteOldAnswer(completion);
    }
}
