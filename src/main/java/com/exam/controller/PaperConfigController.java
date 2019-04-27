package com.exam.controller;


import com.exam.constant.ResultEnum;
import com.exam.exception.ExamException;
import com.exam.pojo.PaperConfigDO;
import com.exam.service.PaperConfigService;
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
 * 试卷配置表 前端控制器
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-12
 */
@RestController
@RequestMapping("/paperConfig")
public class PaperConfigController {

    @Autowired
    private PaperConfigService paperConfigService;

    /**
     * 将题目添加进试卷
     * 参数需要题目id、试卷id、题型id
     */
    @RequestMapping(value = "/addToPaper", method = RequestMethod.POST)
    public Result addToPaper(@RequestBody PaperConfigDO config) {
        try {
            paperConfigService.addQuestionToPaper(config);
            return Result.ok("添加成功！");
        }catch (ExamException e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "添加失败！");
        }
    }

    /**
     * 查询这个试卷已经有了多少题目
     */
    @RequestMapping(value = "/getQuestionNum/{paperId}", method = RequestMethod.GET)
    public Result getQuestionNum(@PathVariable String paperId) {
        List<PaperConfigDO> list = paperConfigService.getQuestionNum(paperId);
        return Result.ok(list);
    }


}

