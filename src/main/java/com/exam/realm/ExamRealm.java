package com.exam.realm;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.pojo.TeacherDO;
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

/**
 * 权限控制的Realm
 * @author
 */
public class ExamRealm extends AuthorizingRealm {

    @Autowired
    private TeacherService teacherService;

    /**
     * 授权方法
     * @param principalCollection
     * @author 杨德石
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("============授权方法执行了===========");

        // 先写在这里，保证授权都能过去
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    // 认证
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
