package com.exam.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.pojo.RoleDO;
import com.exam.service.RoleService;
import com.exam.utils.TreeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/4/1 0001 下午 9:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleTreeTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void testRoleTree() throws Exception {
        QueryWrapper<RoleDO> wrapper = new QueryWrapper<RoleDO>().orderByAsc("role_index");
        List<RoleDO> list = roleService.list(wrapper);
        List<RoleDO> roleList = TreeUtils.getRoleList(list);
        printName(roleList);
    }

    public void printName(List<RoleDO> roleList) {
        for (RoleDO roleDO : roleList) {
            System.out.println(roleDO.getRoleName());
            if(!roleDO.getList().isEmpty()) {
                printName(roleDO.getList());
            }
        }
    }

}
