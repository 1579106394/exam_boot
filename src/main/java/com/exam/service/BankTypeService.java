package com.exam.service;

import com.exam.pojo.BankTypeDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 题库-题型对应表 服务类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-30
 */
public interface BankTypeService extends IService<BankTypeDO> {

    /**
     * 根据知识点id查询题型
     * @param bankId
     * @return
     */
    List<BankTypeDO> getListByKnow(String bankId);
}
