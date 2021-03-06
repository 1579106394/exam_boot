package com.exam.mapper;

import com.exam.pojo.ChoiceDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.pojo.Page;

import java.util.List;

/**
 * <p>
 * 选择题表 Mapper 接口
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
public interface ChoiceMapper extends BaseMapper<ChoiceDO> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<ChoiceDO> getListByPage(Page<ChoiceDO> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    Integer getCountByPage(Page<ChoiceDO> page);
}
