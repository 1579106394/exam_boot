package com.exam.controller;


import com.exam.constant.ResultEnum;
import com.exam.pojo.Page;
import com.exam.pojo.PaperDO;
import com.exam.service.PaperService;
import com.exam.utils.DateUtils;
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
 * 试卷表 前端控制器
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-20
 */
@RestController
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;
    @Autowired
    private IdWorker idWorker;

    /**
     * 创建试卷
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody PaperDO paper) {
        try {
            // 补全属性
            paper.setPaperId(idWorker.nextId()+"");
            paper.setPaperCreateTime(DateUtils.newDate());
            paper.setPaperUpdateTime(DateUtils.newDate());
            paperService.save(paper);
            return Result.ok("创建成功！请及时组卷！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "创建失败！");
        }
    }

    /**
     * 根据id查询试卷基本信息
     */
    @RequestMapping(value = "/get/{paperId}", method = RequestMethod.GET)
    public Result get(@PathVariable String paperId) {
        try {
            PaperDO paper = paperService.getById(paperId);
            return Result.ok(paper);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 更新试卷信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody PaperDO paper) {
        try {
            paper.setPaperUpdateTime(DateUtils.newDate());
            paperService.updateById(paper);
            return Result.ok("修改成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "修改失败！");
        }
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "/delete/{paperId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String paperId) {
        try {
            paperService.removeById(paperId);
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
    public Result list(@RequestBody Page<PaperDO> page) {
        try {
            page = paperService.getByPage(page);
            return Result.ok(page);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 提交组卷请求
     */
    @RequestMapping(value = "/submit/{paperId}", method = RequestMethod.GET)
    public Result submit(@PathVariable String paperId) {
        try {
            paperService.submit(paperId);
            return Result.ok("组卷已提交！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "提交失败！");
        }
    }

    /**
     * 查询试卷的题目
     */
    @RequestMapping(value = "/questionList/{paperId}", method = RequestMethod.GET)
    public Result questionList(@PathVariable String paperId) {
        try {
            PaperDO paper = paperService.getQuestion(paperId);
            return Result.ok(paper);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

}

