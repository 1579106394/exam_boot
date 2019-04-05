package com.exam.service;

import com.exam.pojo.Page;
import com.exam.pojo.StudentDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 学生表 服务类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
public interface StudentService extends IService<StudentDO> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<StudentDO> getByPage(Page<StudentDO> page);
}
