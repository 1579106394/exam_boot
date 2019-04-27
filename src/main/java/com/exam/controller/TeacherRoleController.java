package com.exam.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.constant.ResultEnum;
import com.exam.pojo.TeacherRoleDO;
import com.exam.service.RoleService;
import com.exam.service.TeacherRoleService;
import com.exam.service.TeacherService;
import com.exam.utils.IdWorker;
import com.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private IdWorker idWorker;

    /**
     * 根据教师id查询拥有的角色
     */
    @RequestMapping(value = "/roleList/{teacherId}", method = RequestMethod.GET)
    public Result roleList(@PathVariable String teacherId) {
        try {
            QueryWrapper<TeacherRoleDO> wrapper = new QueryWrapper<TeacherRoleDO>()
                    .eq("tr_teacher", teacherId);
            List<TeacherRoleDO> list = teacherRoleService.list(wrapper);
            // Lambda表达式取出集合中指定元素封装成另一个集合
            List<String> idList = list.stream().map(TeacherRoleDO::getTrRole).collect(Collectors.toList());
            return Result.ok(idList);
        } catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 修改角色
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody List<TeacherRoleDO> list) {
        try {
            if (!list.isEmpty()) {
                String teacherId = list.get(0).getTrTeacher();
                QueryWrapper<TeacherRoleDO> wrapper = new QueryWrapper<TeacherRoleDO>()
                        .eq("tr_teacher", teacherId);
                teacherRoleService.remove(wrapper);

                // 设置id
                list = list.stream().map(e -> {
                    TeacherRoleDO tr = new TeacherRoleDO();
                    tr.setTrId(idWorker.nextId() + "");
                    tr.setTrRole(e.getTrRole());
                    tr.setTrTeacher(e.getTrTeacher());
                    return tr;
                }).collect(Collectors.toList());
                teacherRoleService.saveBatch(list);
                return Result.ok("修改成功！");
            }else {
                return Result.build(ResultEnum.ERROR.getCode(), "请选择至少一个角色！");
            }
        } catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "修改失败！");
        }
    }

}

