package com.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.pojo.Page;
import com.exam.pojo.PaperDO;

/**
 * <p>
 * 试卷表 服务类
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-20
 */
public interface PaperService extends IService<PaperDO> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<PaperDO> getByPage(Page<PaperDO> page);

    /**
     * 查询题目列表
     * @param paperId
     * @return
     */
    PaperDO getQuestion(String paperId);

    /**
     * 提交组卷请求
     * @param paperId
     */
    void submit(String paperId);
}
