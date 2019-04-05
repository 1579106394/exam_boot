package com.exam.service;

import com.exam.pojo.BankDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.pojo.Page;

/**
 * <p>
 * 题库表 服务类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
public interface BankService extends IService<BankDO> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<BankDO> getListByPage(Page<BankDO> page);
}
