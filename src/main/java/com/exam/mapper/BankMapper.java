package com.exam.mapper;

import com.exam.pojo.BankDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.pojo.Page;

import java.util.List;

/**
 * <p>
 * 题库表 Mapper 接口
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
public interface BankMapper extends BaseMapper<BankDO> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<BankDO> getListByPage(Page<BankDO> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    Integer getCountByPage(Page<BankDO> page);
}
