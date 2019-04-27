package com.exam.mapper;

import com.exam.pojo.Page;
import com.exam.pojo.TeacherDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 教师表 Mapper 接口
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-31
 */
public interface TeacherMapper extends BaseMapper<TeacherDO> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<TeacherDO> getListByPage(Page<TeacherDO> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    Integer getCountByPage(Page<TeacherDO> page);
}
