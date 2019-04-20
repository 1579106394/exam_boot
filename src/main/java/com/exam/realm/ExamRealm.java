package com.exam.realm;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.pojo.RoleDO;
import com.exam.pojo.TeacherDO;
import com.exam.pojo.TeacherRoleDO;
import com.exam.service.RoleService;
import com.exam.service.TeacherRoleService;
import com.exam.service.TeacherService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限控制的Realm
 * @author
 */
public class ExamRealm extends AuthorizingRealm {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TeacherRoleService teacherRoleService;
    /**
     * 授权方法
     * @param principalCollection
     * @author 杨德石
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 获取登录中的用户
        TeacherDO teacher = (TeacherDO) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 查询角色， 封装成集合
        QueryWrapper<TeacherRoleDO> wrapper = new QueryWrapper<TeacherRoleDO>()
                .eq("tr_teacher", teacher.getTeacherId());
        List<TeacherRoleDO> list = teacherRoleService.list(wrapper);
        // Lambda表达式取出集合中指定元素封装成另一个集合
        List<String> roleIds = list.stream().map(TeacherRoleDO::getTrRole).collect(Collectors.toList());
        // 使用roleIds查询所有的角色，将角色名封装成集合
        List<String> roleList = roleService.listByIds(roleIds).stream().map(RoleDO::getRoleName).collect(Collectors.toList());
        info.addRoles(roleList);


        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 根据用户名查询数据库中的密码
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
        String username = passwordToken.getUsername();

        QueryWrapper<TeacherDO> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_username", username);
        TeacherDO teacherDO = teacherService.getOne(wrapper);
        if(teacherDO == null) {
            // 用户名不存在
            return null;
        }
        // 框架负责比对数据库中的密码和页面输入的密码是否一致
        AuthenticationInfo info = new SimpleAuthenticationInfo(teacherDO, teacherDO.getTeacherPassword(), this.getName());
        return info;
    }
}
