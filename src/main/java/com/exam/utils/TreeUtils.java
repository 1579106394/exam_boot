package com.exam.utils;

import com.exam.pojo.RoleDO;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 将集合封装成树形结构的工具类
 *
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/4/1 0001 下午 9:37
 */
public class TreeUtils {

    private TreeUtils() {}

    public static List<RoleDO> getRoleList(List<RoleDO> roleList) {
        List<RoleDO> list = Lists.newArrayList();
        // 1.先遍历找出所有父节点
        for (RoleDO roleDO : roleList) {
            if (StringUtils.isBlank(roleDO.getRoleFather())) {
                list.add(roleDO);
            }
        }

        // 2.递归找出每个父节点的子节点
        for (RoleDO roleDO : list) {
            roleDO.setList(getChild(roleDO.getRoleId(), roleList));
        }

        return list;
    }

    public static List<RoleDO> getChild(String fatherId, List<RoleDO> roleList) {
        List<RoleDO> childList = Lists.newArrayList();
        // 循环封装子节点
        for (RoleDO roleDO : roleList) {
            if (StringUtils.isNotBlank(roleDO.getRoleFather())) {
                if (fatherId.equals(roleDO.getRoleFather())) {
                    childList.add(roleDO);
                }
            }
        }

        // 子集不为空，递归找出每个子节点的子节点
        for (RoleDO roleDO : childList) {
            roleDO.setList(getChild(roleDO.getRoleId(), roleList));
        }

        // 如果子集是空的，说明已经没有子节点了，就结束
        if(childList.isEmpty()) {
            return Lists.newArrayList();
        }

        return childList;
    }

}
