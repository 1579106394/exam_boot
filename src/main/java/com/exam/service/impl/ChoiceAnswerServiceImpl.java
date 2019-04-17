package com.exam.service.impl;

import com.exam.pojo.ChoiceAnswerDO;
import com.exam.mapper.ChoiceAnswerMapper;
import com.exam.pojo.ChoiceDO;
import com.exam.service.ChoiceAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 选项表 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@Service
public class ChoiceAnswerServiceImpl extends ServiceImpl<ChoiceAnswerMapper, ChoiceAnswerDO> implements ChoiceAnswerService {

    @Autowired
    private ChoiceAnswerMapper choiceAnswerMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOldAnswer(ChoiceDO choice) {
        choiceAnswerMapper.deleteOldAnswer(choice);
    }
}
