package com.exam.service.impl;

import com.exam.pojo.CodeAnswerDO;
import com.exam.mapper.CodeAnswerMapper;
import com.exam.pojo.CodeDO;
import com.exam.service.CodeAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 编程题-答案 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-19
 */
@Service
public class CodeAnswerServiceImpl extends ServiceImpl<CodeAnswerMapper, CodeAnswerDO> implements CodeAnswerService {

    @Autowired
    private CodeAnswerMapper codeAnswerMapper;

    @Override
    public void deleteOldAnswer(CodeDO codeDO) {
        codeAnswerMapper.deleteOldAnswer(codeDO);
    }
}
