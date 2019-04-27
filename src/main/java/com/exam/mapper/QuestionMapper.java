package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.pojo.Page;
import com.exam.pojo.QuestionDO;

import java.util.List;

/**
 * <p>
 * 其他题型 Mapper 接口
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-12
 */
public interface QuestionMapper extends BaseMapper<QuestionDO> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<QuestionDO> getListByPage(Page<QuestionDO> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    Integer getCountByPage(Page<QuestionDO> page);

    /**
     * 根据id列表查询
     * @param ids
     * @return
     */
    List<QuestionDO> getByIds(List<String> ids);
}
