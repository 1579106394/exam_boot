package com.exam.controller;


import com.exam.constant.ResultEnum;
import com.exam.service.RoleService;
import com.exam.service.TeacherRoleService;
import com.exam.service.TeacherService;
import com.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 教师-角色表 前端控制器
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-05
 */
@RestController
@RequestMapping("/teacherRole")
public class TeacherRoleController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TeacherRoleService teacherRoleService;
    /**
     * 根据教师id查询拥有的权限
     */
    @RequestMapping(value = "/roleList/{teacherId}", method = RequestMethod.GET)
    public Result roleList(@PathVariable String teacherId) {
        try {

            return Result.ok();
        }catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

}

