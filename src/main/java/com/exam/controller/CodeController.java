package com.exam.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.constant.ResultEnum;
import com.exam.pojo.CodeAnswerDO;
import com.exam.pojo.CodeDO;
import com.exam.pojo.Page;
import com.exam.service.CodeAnswerService;
import com.exam.service.CodeService;
import com.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 编程题 前端控制器
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-17
 */
@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private CodeService codeService;
    @Autowired
    private CodeAnswerService codeAnswerService;

    /**
     * 添加编程题
     * @param code
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody CodeDO code) {
        try {
            codeService.addCode(code);
            return Result.ok("添加成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "添加失败！");
        }
    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/get/{codeId}", method = RequestMethod.GET)
    public Result get(@PathVariable String codeId) {
        try {
            CodeDO code = codeService.getById(codeId);
            List<CodeAnswerDO> answerDOList = codeAnswerService.list(new QueryWrapper<CodeAnswerDO>().eq("answer_code", codeId));
            code.setAnswerList(answerDOList);
            return Result.ok(code);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody CodeDO codeDO) {
        try {
            codeService.updateCode(codeDO);
            return Result.ok("修改成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "修改失败！");
        }
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "/delete/{codeId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String codeId) {
        try {
            codeService.removeById(codeId);
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
    public Result list(@RequestBody Page<CodeDO> page) {
        try {
            page = codeService.getByPage(page);
            return Result.ok(page);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

}

