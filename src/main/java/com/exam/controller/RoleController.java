package com.exam.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.constant.ResultEnum;
import com.exam.pojo.RoleDO;
import com.exam.service.RoleService;
import com.exam.utils.IdWorker;
import com.exam.utils.Result;
import com.exam.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-01
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private IdWorker idWorker;

    /**
     * 新增角色
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody RoleDO roleDO) {
        try {
            roleDO.setRoleId(idWorker.nextId() + "");
            roleService.save(roleDO);
            return Result.ok("添加成功！");
        } catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "添加失败！");
        }
    }

    /**
     * 修改角色
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody RoleDO roleDO) {
        try {
            roleService.updateById(roleDO);
            return Result.ok("修改成功！");
        } catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "修改失败！");
        }
    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/get/{roleId}", method = RequestMethod.GET)
    public Result get(@PathVariable String roleId) {
        try {
            RoleDO roleDO = roleService.getById(roleId);
            // 查询这个角色的子角色
            QueryWrapper<RoleDO> wrapper = new QueryWrapper<RoleDO>()
                    .eq("role_father", roleDO.getRoleId());
            List<RoleDO> list = roleService.list(wrapper);
            roleDO.setList(list);
            return Result.ok(roleDO);
        }catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "/delete/{roleId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String roleId) {
        try {
            roleService.delete(roleId);
            return Result.ok("删除成功！");
        }catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "删除失败！");
        }
    }

    /**
     * 树形查询
     */
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public Result treeList() {
        try {
            QueryWrapper<RoleDO> wrapper = new QueryWrapper<RoleDO>().orderByAsc("role_index");
            List<RoleDO> list = roleService.list(wrapper);
            List<RoleDO> roleList = TreeUtils.getRoleList(list);
            return Result.ok(roleList);
        }catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 查询所有（非树形）
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result all() {
        try {
            QueryWrapper<RoleDO> wrapper = new QueryWrapper<RoleDO>().orderByAsc("role_index");
            List<RoleDO> list = roleService.list(wrapper);
            return Result.ok(list);
        }catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }
}