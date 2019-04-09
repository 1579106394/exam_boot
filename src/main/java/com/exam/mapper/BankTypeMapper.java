package com.exam.mapper;

import com.exam.pojo.BankTypeDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 题库-题型对应表 Mapper 接口
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-30
 */
public interface BankTypeMapper extends BaseMapper<BankTypeDO> {

    /**
     * 根据题库id查询
     * @param bankId
     * @return
     */
    List<BankTypeDO> getListByBank(String bankId);
}
