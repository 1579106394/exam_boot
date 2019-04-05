package com.exam.service;

import com.exam.pojo.DictDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.pojo.Page;

import java.util.List;

/**
 * <p>
 * 数据字典表
college-学院
major-专业
job-职务
title-职称
subject-科目 服务类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
public interface DictService extends IService<DictDO> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<DictDO> getListByPage(Page<DictDO> page);

    /**
     * 根据父级id查询
     * @param fatherId
     * @return
     */
    List<DictDO> getByFather(String fatherId);
}
