package com.exam.mapper;

import com.exam.pojo.CodeDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.pojo.Page;

import java.util.List;

/**
 * <p>
 * 编程题 Mapper 接口
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-17
 */
public interface CodeMapper extends BaseMapper<CodeDO> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<CodeDO> getListByPage(Page<CodeDO> page);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Integer getCountByPage(Page<CodeDO> page);
}
