package com.exam.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.constant.ResultEnum;
import com.exam.pojo.Page;
import com.exam.pojo.PwdDO;
import com.exam.pojo.RoleDO;
import com.exam.pojo.TeacherDO;
import com.exam.pojo.TeacherRoleDO;
import com.exam.service.PwdService;
import com.exam.service.RoleService;
import com.exam.service.TeacherRoleService;
import com.exam.service.TeacherService;
import com.exam.utils.IdWorker;
import com.exam.utils.Md5Utils;
import com.exam.utils.Result;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 教师表 前端控制器
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-31
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private PwdService pwdService;
    @Autowired
    private TeacherRoleService teacherRoleService;
    @Autowired
    private RoleService roleService;

    /**
     * 教师登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody TeacherDO teacherDO) {
        // 使用shiro框架进行认证
        // 获取当前用户对象，状态为“未认证”
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(teacherDO.getTeacherUsername(), Md5Utils.toMD5(teacherDO.getTeacherPassword()));
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "用户名或密码错误！");
        }
        // 查询登录成功的数据，放到redis中
        TeacherDO teacher = (TeacherDO) subject.getPrincipal();
        Serializable sessionId = subject.getSession().getId();
        Map<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("token", sessionId);
        dataMap.put("teacher", teacher);
        return Result.ok("登陆成功！", dataMap);
    }

    /**
     * 添加教师
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody TeacherDO teacherDO) {
        try {
            // 根据工号、账号、身份证号分别查询一次，这几列都是唯一的
            QueryWrapper<TeacherDO> wrapper = new QueryWrapper<TeacherDO>()
                    .eq("teacher_number", teacherDO.getTeacherNumber());
            TeacherDO byNumber = teacherService.getOne(wrapper);
            if (byNumber != null) {
                return Result.build(ResultEnum.ERROR.getCode(), "工号已存在！");
            }
            wrapper = new QueryWrapper<TeacherDO>()
                    .eq("teacher_card", teacherDO.getTeacherCard());
            TeacherDO byCard = teacherService.getOne(wrapper);
            if (byCard != null) {
                return Result.build(ResultEnum.ERROR.getCode(), "身份证号已存在！");
            }
            wrapper = new QueryWrapper<TeacherDO>()
                    .eq("teacher_username", teacherDO.getTeacherUsername());
            TeacherDO byUsername = teacherService.getOne(wrapper);
            if (byUsername != null) {
                return Result.build(ResultEnum.ERROR.getCode(), "用户名已存在！");
            }
            // 设置属性
            String teacherId = idWorker.nextId() + "";
            teacherDO.setTeacherId(teacherId);
            teacherDO.setTeacherUsername(teacherDO.getTeacherNumber());
            String plaintext = teacherDO.getTeacherPassword();
            String ciphertext = Md5Utils.toMD5(plaintext);
            teacherDO.setTeacherPassword(ciphertext);
            teacherService.save(teacherDO);

            // 在密码表里添加一套密码对应关系，防止忘记密码。
            pwdService.save(new PwdDO(teacherId, plaintext, ciphertext));

        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "添加失败！");
        }
        return Result.ok("添加成功！");
    }

    /**
     * 修改教师
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody TeacherDO teacherDO) {
        try {
            TeacherDO teacher = teacherService.getById(teacherDO.getTeacherId());

            // 根据工号、账号、身份证号分别查询一次，这几列都是唯一的
            QueryWrapper<TeacherDO> wrapper = new QueryWrapper<>();
            wrapper.eq("teacher_number", teacherDO.getTeacherNumber());
            TeacherDO byNumber = teacherService.getOne(wrapper);
            if (!teacher.getTeacherNumber().equals(teacherDO.getTeacherNumber()) && byNumber != null) {
                return Result.build(ResultEnum.ERROR.getCode(), "工号已存在！");
            }
            wrapper = new QueryWrapper<>();
            wrapper.eq("teacher_card", teacherDO.getTeacherCard());
            TeacherDO byCard = teacherService.getOne(wrapper);
            if (!teacher.getTeacherCard().equals(teacherDO.getTeacherCard()) && byCard != null) {
                return Result.build(ResultEnum.ERROR.getCode(), "身份证号已存在！");
            }
            wrapper = new QueryWrapper<>();
            wrapper.eq("teacher_username", teacherDO.getTeacherUsername());
            TeacherDO byUsername = teacherService.getOne(wrapper);
            if (!teacher.getTeacherUsername().equals(teacherDO.getTeacherUsername()) && byUsername != null) {
                return Result.build(ResultEnum.ERROR.getCode(), "用户名已存在！");
            }

            String plaintext = teacherDO.getTeacherPassword();
            String ciphertext = Md5Utils.toMD5(plaintext);
            teacherDO.setTeacherPassword(ciphertext);
            teacherService.updateById(teacherDO);

            // 修改对应的密码表
            pwdService.updateById(new PwdDO(teacherDO.getTeacherId(), plaintext, ciphertext));

        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "修改失败！");
        }
        return Result.ok("修改成功！");
    }

    /**
     * 根据id查询教师
     */
    @RequestMapping(value = "/get/{teacherId}", method = RequestMethod.GET)
    public Result get(@PathVariable String teacherId) {
        try {
            TeacherDO teacherDO = teacherService.getById(teacherId);
            QueryWrapper<PwdDO> wrapper = new QueryWrapper<>();
            wrapper.eq("pwd_ciphertext", teacherDO.getTeacherPassword());
            PwdDO pwdDO = pwdService.getOne(wrapper);
            teacherDO.setTeacherPassword(pwdDO.getPwdPlaintext());
            return Result.ok(teacherDO);
        } catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "/delete/{teacherId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String teacherId) {
        try {
            teacherService.removeById(teacherId);
            return Result.ok("删除成功！");
        } catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "删除失败！");
        }
    }

    /**
     * 分页查询教师
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result list(@RequestBody Page<TeacherDO> page) {
        try {
            page = teacherService.getByPage(page);
            return Result.ok(page);
        } catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 根据token获取用户信息
     */
    @RequestMapping(value = "/info/{token}", method = RequestMethod.GET)
    public Result info(@PathVariable String token, HttpServletRequest request, HttpServletResponse response) {
        WebSessionKey key = new WebSessionKey(token, request, response);
        try {
            Session session = SecurityUtils.getSecurityManager().getSession(key);
            SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            TeacherDO teacherDO = (TeacherDO) principalCollection.getPrimaryPrincipal();
            // 查询教师的角色信息，封装到教师中
            // 查询角色， 封装成集合
            QueryWrapper<TeacherRoleDO> wrapper = new QueryWrapper<TeacherRoleDO>()
                    .eq("tr_teacher", teacherDO.getTeacherId());
            List<TeacherRoleDO> list = teacherRoleService.list(wrapper);
            // Lambda表达式取出集合中指定元素封装成另一个集合
            List<String> roleIds = list.stream().map(TeacherRoleDO::getTrRole).collect(Collectors.toList());
            // 使用roleIds查询所有的角色，将角色名封装成集合
            List<String> roleList = Lists.newArrayList();
            if(!roleIds.isEmpty()) {
                roleList = roleService.listByIds(roleIds).stream().map(RoleDO::getRoleName).collect(Collectors.toList());
            }
            teacherDO.setRoleList(roleList);
            return Result.ok(teacherDO);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "您未登录，请登录");
        }
    }
}