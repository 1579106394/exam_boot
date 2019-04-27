package com.exam.service;

import com.exam.pojo.Page;
import com.exam.pojo.TrueFalseDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 判断题表 服务类
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-12
 */
public interface TrueFalseService extends IService<TrueFalseDO> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<TrueFalseDO> getByPage(Page<TrueFalseDO> page);
}
