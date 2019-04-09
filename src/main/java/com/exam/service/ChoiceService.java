package com.exam.service;

import com.exam.pojo.ChoiceDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.pojo.Page;

/**
 * <p>
 * 选择题表 服务类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
public interface ChoiceService extends IService<ChoiceDO> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<ChoiceDO> getByPage(Page<ChoiceDO> page);
}
