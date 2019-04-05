package com.exam.mapper;

import com.exam.pojo.DictDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.pojo.Page;

import java.util.List;

/**
 * <p>
 * 数据字典表
college-学院
major-专业
job-职务
title-职称
subject-科目 Mapper 接口
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
public interface DictMapper extends BaseMapper<DictDO> {

    /**
     * 分页查询总数
     * @param page
     * @return
     */
    List<DictDO> getListByPage(Page<DictDO> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    Integer getCountByPage(Page<DictDO> page);

    /**
     * 根据父级id查询
     * @param fatherId
     * @return
     */
    List<DictDO> getByFather(String fatherId);
}
