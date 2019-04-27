package com.exam.mapper;

import com.exam.pojo.Page;
import com.exam.pojo.StudentDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 学生表 Mapper 接口
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
public interface StudentMapper extends BaseMapper<StudentDO> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<StudentDO> getListByPage(Page<StudentDO> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    Integer getCountByPage(Page<StudentDO> page);
}
