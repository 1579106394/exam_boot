package com.exam.service;

import com.exam.pojo.Page;
import com.exam.pojo.TeacherDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 教师表 服务类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-31
 */
public interface TeacherService extends IService<TeacherDO> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<TeacherDO> getByPage(Page<TeacherDO> page);
}
