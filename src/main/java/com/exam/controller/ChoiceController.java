package com.exam.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.constant.ResultEnum;
import com.exam.constant.TypeEnum;
import com.exam.pojo.ChoiceAnswerDO;
import com.exam.pojo.ChoiceDO;
import com.exam.pojo.Page;
import com.exam.service.ChoiceAnswerService;
import com.exam.service.ChoiceService;
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
 * 选择题表 前端控制器
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@RestController
@RequestMapping("/choice")
public class ChoiceController {

    @Autowired
    private ChoiceService choiceService;
    @Autowired
    private ChoiceAnswerService choiceAnswerService;
    @Autowired
    private IdWorker idWorker;

    /**
     * 添加选择、多选、判断题
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody ChoiceDO choice) {
        try {
            Result result = Result.ok("添加成功！");
            if (TypeEnum.ONE_CHOICE.getCode().equals(choice.getChoiceType())) {
                // 是单选
                result = choiceService.addOrUpdateOneChoice(choice);
            } else if (TypeEnum.MANY_CHOICE.getCode().equals(choice.getChoiceType())) {
                // 多选
                result = choiceService.addOrUpdateManyChoice(choice);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "添加失败！");
        }
    }

    /**
     * 查询选择题
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result list(@RequestBody Page<ChoiceDO> page, String bankId, String typeId) {
        try {
            if (StringUtils.isNotBlank(bankId)) {
                page.getParams().put("bankId", bankId);
            }
            if (StringUtils.isNotBlank(typeId)) {
                page.getParams().put("typeId", typeId);
            }
            // 分页查询
            page = choiceService.getByPage(page);
            return Result.ok(page);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "/delete/{choiceId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String choiceId) {
        try {
            // 根据id删除
            choiceService.removeById(choiceId);
            return Result.ok("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "删除失败！");
        }
    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/get/{choiceId}", method = RequestMethod.GET)
    public Result get(@PathVariable String choiceId) {
        try {
            ChoiceDO choice = choiceService.getById(choiceId);

            List<ChoiceAnswerDO> list = choiceAnswerService.list(new QueryWrapper<ChoiceAnswerDO>().eq("answer_choice", choiceId));
            choice.setChoiceAnswer(list);
            return Result.ok(choice);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody ChoiceDO choice) {
        try {
            Result result = Result.ok("修改成功！");
            if (TypeEnum.ONE_CHOICE.getCode().equals(choice.getChoiceType())) {
                // 是单选
                result = choiceService.addOrUpdateOneChoice(choice);
            } else if (TypeEnum.MANY_CHOICE.getCode().equals(choice.getChoiceType())) {
                // 多选
                result = choiceService.addOrUpdateManyChoice(choice);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "修改失败！");
        }
    }

}

