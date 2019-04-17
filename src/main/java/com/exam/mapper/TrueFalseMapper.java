package com.exam.mapper;

import com.exam.pojo.Page;
import com.exam.pojo.TrueFalseDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 判断题表 Mapper 接口
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-12
 */
public interface TrueFalseMapper extends BaseMapper<TrueFalseDO> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<TrueFalseDO> getListByPage(Page<TrueFalseDO> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    Integer getCountByPage(Page<TrueFalseDO> page);
}
