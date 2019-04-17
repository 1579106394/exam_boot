package com.exam.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.constant.ResultEnum;
import com.exam.pojo.CompletionAnswerDO;
import com.exam.pojo.CompletionDO;
import com.exam.pojo.Page;
import com.exam.service.CompletionAnswerService;
import com.exam.service.CompletionService;
import com.exam.utils.IdWorker;
import com.exam.utils.Result;
import com.exam.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 填空题表 前端控制器
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@RestController
@RequestMapping("/completion")
public class CompletionController {

    @Autowired
    private CompletionService completionService;
    @Autowired
    private CompletionAnswerService completionAnswerService;
    @Autowired
    private IdWorker idWorker;

    /**
     * 添加填空题
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody CompletionDO completion) {
        try {
            Result result = saveOrUpdate(completion);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "添加失败！");
        }
    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/get/{compId}", method = RequestMethod.GET)
    public Result get(@PathVariable String compId) {
        try {
            CompletionDO completionDO = completionService.getById(compId);
            // 查询所有的答案
            String comId = completionDO.getCompId();
            QueryWrapper<CompletionAnswerDO> wrapper = new QueryWrapper<CompletionAnswerDO>()
                    .eq("answer_comp", comId);
            List<CompletionAnswerDO> answerDOList = completionAnswerService.list(wrapper);
            completionDO.setAnswerList(answerDOList);
            return Result.ok(completionDO);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody CompletionDO completionDO) {
        try {
            Result result = saveOrUpdate(completionDO);
            return result;
        } catch (Exception e) {
            e.printStackTrace();

            return Result.build(ResultEnum.ERROR.getCode(), "修改失败！");
        }
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "/delete/{compId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String compId) {
        try {
            completionService.removeById(compId);
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
    public Result list(@RequestBody Page<CompletionDO> page) {
        try {
            page = completionService.getByPage(page);
            return  Result.ok(page);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 添加或修改填空题
     */
    @Transactional(rollbackFor = Exception.class)
    public Result saveOrUpdate(CompletionDO completion) throws Exception {

        // 获取填空，遍历插入
        List<CompletionAnswerDO> answerList = completion.getAnswerList();
        if (answerList.isEmpty()) {
            return Result.build(ResultEnum.ERROR.getCode(), "填空题至少有一个空！");
        }

        if (StringUtils.isBlank(completion.getCompId())) {
            // id为空，为添加
            // 设置id
            String comId = idWorker.nextId() + "";
            completion.setCompId(comId);
            answerList.forEach(e -> {
                e.setAnswerId(idWorker.nextId() + "");
                e.setAnswerComp(comId);
            });
            // 批量插入
            completionAnswerService.saveBatch(answerList);

            // 插入填空题
            completionService.save(completion);
            return Result.ok("添加成功！");
        } else {
            // 否则，为修改

            // 答案列表为最新答案列表，根据id和答案列表删除数据库中不存在的答案
            completionAnswerService.deleteOldAnswer(completion);

            completionAnswerService.updateBatchById(answerList);
            completionService.updateById(completion);

            // 根据id查询该题，获取该题的所有答案
            CompletionDO completionDO = completionService.getById(completion.getCompId());
            // 查询所有的答案
            String comId = completionDO.getCompId();
            QueryWrapper<CompletionAnswerDO> wrapper = new QueryWrapper<CompletionAnswerDO>()
                    .eq("answer_choice", comId);

            List<CompletionAnswerDO> answerDOList = completionAnswerService.list(wrapper);

            // 删除所有更新成功的答案，剩下的就是新增的，批量添加一下
            answerList.removeAll(answerDOList);

            answerList.forEach(e -> {
                e.setAnswerId(idWorker.nextId() + "");
                e.setAnswerComp(comId);
            });

            completionAnswerService.saveBatch(answerList);

            return Result.ok("修改成功！");
        }
    }
}


