package com.exam.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.constant.ResultEnum;
import com.exam.pojo.DictDO;
import com.exam.pojo.Page;
import com.exam.service.DictService;
import com.exam.utils.IdWorker;
import com.exam.utils.Result;
import com.exam.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 数据字典表
 * college-学院
 * major-专业
 * job-职务
 * title-职称
 * subject-科目 前端控制器
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @Autowired
    private IdWorker idWorker;

    /**
     * 新增数据字典
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody DictDO dict) {
        try {
            dict.setDictId(idWorker.nextId() + "");
            dictService.save(dict);
            return Result.ok("添加成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "新增失败！");
        }
    }

    /**
     * 修改数据字典
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody DictDO dict) {
        try {
            dictService.updateById(dict);
            return Result.ok("更新成功！");

        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "修改失败！");
        }
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "/delete/{dictId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String dictId) {
        try {
            dictService.removeById(dictId);
            return Result.ok("删除成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "删除失败！");
        }
    }

    /**
     * 分页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result list(@RequestBody Page<DictDO> page) {
        try {
            page = dictService.getListByPage(page);
            return Result.ok(page);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/get/{dictId}", method = RequestMethod.GET)
    public Result get(@PathVariable String dictId) {
        DictDO dictDO = dictService.getById(dictId);
        return Result.ok(dictDO);
    }

    /**
     * 查询所有
     */
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public Result allList(@RequestBody DictDO dictDO) {
        QueryWrapper<DictDO> wrapper = new QueryWrapper<>();
        if (dictDO != null && StringUtils.isNotBlank(dictDO.getDictType())) {
            wrapper.eq("dict_type", dictDO.getDictType());
        }
        List<DictDO> list = dictService.list(wrapper);
        return Result.ok(list);
    }

    /**
     * 根据父级id查询
     */
    @RequestMapping(value = "/childList/{fatherId}", method = RequestMethod.GET)
    public Result childList(@PathVariable String fatherId) {
        QueryWrapper<DictDO> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_father", fatherId);
        List<DictDO> list = dictService.list(wrapper);
        return Result.ok(list);
    }

}

