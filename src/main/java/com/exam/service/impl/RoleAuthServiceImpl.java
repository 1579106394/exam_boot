package com.exam.service.impl;

import com.exam.pojo.RoleAuthDO;
import com.exam.mapper.RoleAuthMapper;
import com.exam.service.RoleAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色-权限表 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-01
 */
@Service
public class RoleAuthServiceImpl extends ServiceImpl<RoleAuthMapper, RoleAuthDO> implements RoleAuthService {

}
