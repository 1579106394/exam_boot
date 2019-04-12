package com.exam.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.constant.ResultEnum;
import com.exam.pojo.BankDO;
import com.exam.pojo.Page;
import com.exam.service.BankService;
import com.exam.service.BankTypeService;
import com.exam.service.DictService;
import com.exam.service.TypeService;
import com.exam.utils.IdWorker;
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
 * 题库表 前端控制器
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;
    @Autowired
    private BankTypeService bankTypeService;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private TypeService typeService;
    @Autowired
    private DictService dictService;

    /**
     * 新增题库
     *
     * @param bankDO
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody BankDO bankDO) {
        try {
            bankDO.setBankId(idWorker.nextId() + "");
            bankService.save(bankDO);
            return Result.ok("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "添加失败！");
        }
    }

    /**
     * 更新题库
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody BankDO bankDO) {
        try {
            BankDO bank = bankService.getById(bankDO.getBankId());
            bankDO.setBankVersion(bank.getBankVersion());
            bankService.updateById(bankDO);
            return Result.ok("更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "修改失败！");
        }
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "/delete/{bankId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String bankId) {
        try {
            bankService.removeById(bankId);
            return Result.ok("删除成功！");
        } catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "删除失败！");
        }
    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/get/{bankId}", method = RequestMethod.GET)
    public Result get(@PathVariable String bankId) {
        BankDO bankDO = bankService.getById(bankId);
        // 查询对应的学院和科目
        bankDO.setCollege(dictService.getById(bankDO.getBankCollege()));
        bankDO.setSubject(dictService.getById(bankDO.getBankSubject()));
        return Result.ok(bankDO);
    }

    /**
     * 查询所有
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result findAll() {
        try {
            List<BankDO> list = bankService.list();
            return Result.ok(list);
        } catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 根据学院查询
     */
    @RequestMapping(value = "/getByCollege/{collegeId}", method = RequestMethod.GET)
    public Result getByCollege(@PathVariable String collegeId) {
        QueryWrapper<BankDO> wrapper = new QueryWrapper<>();
        wrapper.eq("bank_college", collegeId);
        List<BankDO> list = bankService.list(wrapper);
        return Result.ok(list);
    }

    /**
     * 根据科目查询
     */
    @RequestMapping(value = "/getBySubject/{subjectId}", method = RequestMethod.GET)
    public Result getBySubject(@PathVariable String subjectId) {
        QueryWrapper<BankDO> wrapper = new QueryWrapper<>();
        wrapper.eq("bank_subject", subjectId);
        List<BankDO> list = bankService.list(wrapper);
        return Result.ok(list);
    }

    /**
     * 分页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result list(@RequestBody Page<BankDO> page) {
        try {
            page = bankService.getListByPage(page);
            return Result.ok(page);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

}

