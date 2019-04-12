package com.exam.controller;


import com.exam.constant.ResultEnum;
import com.exam.pojo.BankKnowledgeDO;
import com.exam.pojo.Page;
import com.exam.service.BankKnowledgeService;
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
 * 知识点表 前端控制器
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-11
 */
@RestController
@RequestMapping("/bankKnowledge")
public class BankKnowledgeController {

    @Autowired
    private BankKnowledgeService knowledgeService;
    @Autowired
    private IdWorker idWorker;

    /**
     * 添加知识点
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody BankKnowledgeDO knowledge) {
        try {
            knowledge.setKnowId(idWorker.nextId() + "");
            knowledgeService.save(knowledge);
            return Result.ok("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "添加失败！");
        }
    }

    /**
     * 分页查询知识点
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result list(@RequestBody Page<BankKnowledgeDO> page) {
        try {
            page = knowledgeService.getByPage(page);
            return Result.ok(page);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/get/{knowId}", method = RequestMethod.GET)
    public Result get(@PathVariable String knowId) {
        try {
            BankKnowledgeDO knowledgeDO = knowledgeService.getById(knowId);
            return Result.ok(knowledgeDO);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody BankKnowledgeDO knowledgeDO) {
        try {
            BankKnowledgeDO knowledge = knowledgeService.getById(knowledgeDO.getKnowId());
            knowledgeDO.setKnowVersion(knowledge.getKnowVersion());
            knowledgeService.updateById(knowledgeDO);
            return Result.ok("修改成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "修改失败！");
        }
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "/delete/{knowId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String knowId) {
        try {
            knowledgeService.removeById(knowId);
            return Result.ok("删除成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "删除失败！");
        }
    }

}

