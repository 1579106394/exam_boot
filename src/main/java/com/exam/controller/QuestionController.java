package com.exam.controller;


import com.exam.constant.ResultEnum;
import com.exam.constant.TypeEnum;
import com.exam.pojo.QuestionDO;
import com.exam.service.QuestionService;
import com.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 * 其他题型 前端控制器
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-30
 */
@Controller
@RequestMapping("/questionDO")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 新增题目
     *
     * @param questionDO
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody QuestionDO questionDO) {
        try {
            if (questionDO.getQuestionStyle().equals(TypeEnum.ONE_CHOICE.getCode())) {
                // 单项选择题
            }else if(questionDO.getQuestionStyle().equals(TypeEnum.MANY_CHOICE.getCode())) {
                // 多项选择题
            }else if(questionDO.getQuestionStyle().equals(TypeEnum.COMPLETION.getCode())) {
                // 填空题
            }else if(questionDO.getQuestionStyle().equals(TypeEnum.JUDGEMENT.getCode())) {
                // 判断题
            }else if(questionDO.getQuestionStyle().equals(TypeEnum.PROGRAMMING.getCode())) {
                // 编程题
            }else if(questionDO.getQuestionStyle().equals(TypeEnum.OTHER.getCode())) {
                // 其他题
            }
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "添加失败！");
        }
    }

}

