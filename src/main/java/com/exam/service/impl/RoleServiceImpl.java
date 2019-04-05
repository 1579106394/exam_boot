package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.mapper.RoleMapper;
import com.exam.pojo.RoleDO;
import com.exam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-01
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 递归删除
     *
     * @param roleId
     */
    @Override
    public void delete(String roleId) {
        RoleDO roleDO = roleMapper.selectById(roleId);
        deleteChild(roleDO);
    }

    private void deleteChild(RoleDO roleDO) {
        String fatherId = roleDO.getRoleId();
        // 查询所有子节点
        QueryWrapper<RoleDO> wrapper = new QueryWrapper<RoleDO>()
                .eq("role_father", fatherId);
        List<RoleDO> roleList = roleMapper.selectList(wrapper);
        for (RoleDO child : roleList) {
            deleteChild(child);
        }
        roleMapper.deleteById(roleDO.getRoleId());
    }
}
