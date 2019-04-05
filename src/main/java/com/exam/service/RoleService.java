package com.exam.service;

import com.exam.pojo.RoleDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-01
 */
public interface RoleService extends IService<RoleDO> {

    /**
     * 递归删除
     * @param roleId
     */
    void delete(String roleId);
}
