package com.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.pojo.Page;
import com.exam.pojo.QuestionDO;

/**
 * <p>
 * 其他题型 服务类
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-12
 */
public interface QuestionService extends IService<QuestionDO> {

    /**
     * 修改题目
     * @param question
     * @return
     */
    void updateQuestion(QuestionDO question);

    /**
     * 添加题目
     * @param question
     */
    void saveQuestion(QuestionDO question);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<QuestionDO> getByPage(Page<QuestionDO> page);
}
