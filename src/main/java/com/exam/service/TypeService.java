package com.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.pojo.Page;
import com.exam.pojo.TypeDO;

/**
 * <p>
 * 题型表 服务类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
public interface TypeService extends IService<TypeDO> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<TypeDO> getListByPage(Page<TypeDO> page);
}
