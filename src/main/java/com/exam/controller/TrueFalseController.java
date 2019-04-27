package com.exam.controller;


import com.exam.constant.ResultEnum;
import com.exam.pojo.Page;
import com.exam.pojo.TrueFalseDO;
import com.exam.service.TrueFalseService;
import com.exam.utils.IdWorker;
import com.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 判断题表 前端控制器
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-12
 */
@RestController
@RequestMapping("/trueFalse")
public class TrueFalseController {

    @Autowired
    private TrueFalseService trueFalseService;
    @Autowired
    private IdWorker idWorker;

    /**
     * 添加选择题
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody TrueFalseDO trueFalse) {
        try {
            // 生成id
            trueFalse.setTfId(idWorker.nextId() + "");
            trueFalseService.save(trueFalse);
            return Result.ok("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "添加失败！");
        }
    }

    /**
     * 分页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result list(@RequestBody Page<TrueFalseDO> page) {
        try {
            page = trueFalseService.getByPage(page);
            return Result.ok(page);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/get/{tfId}", method = RequestMethod.GET)
    public Result get(@PathVariable String tfId) {
        try {
            TrueFalseDO trueFalseDO = trueFalseService.getById(tfId);
            return Result.ok(trueFalseDO);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "/delete/{tfId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String tfId) {
        try {
            trueFalseService.removeById(tfId);
            return Result.ok("删除成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "删除失败！");
        }
    }

    /**
     * 修改选择题
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody TrueFalseDO trueFalse) {
        try {
            trueFalseService.updateById(trueFalse);
            return Result.ok("修改成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "修改失败！");
        }
    }
}

