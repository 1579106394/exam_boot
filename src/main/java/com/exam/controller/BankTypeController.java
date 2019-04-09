package com.exam.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.constant.ResultEnum;
import com.exam.pojo.BankTypeDO;
import com.exam.service.BankTypeService;
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
 * 题库-题型对应表 前端控制器
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-30
 */
@RestController
@RequestMapping("/bankType")
public class BankTypeController {

    @Autowired
    private BankTypeService bankTypeService;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private TypeService typeService;

    /**
     * 根据题库id查询题型
     */
    @RequestMapping(value = "/list/{bankId}", method = RequestMethod.GET)
    public Result list(@PathVariable String bankId) {
        try {
            List<BankTypeDO> list = bankTypeService.getListByBank(bankId);
            return Result.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }

    /**
     * 分配题型
     */
    @RequestMapping(value = "/addType", method = RequestMethod.POST)
    public Result addType(@RequestBody BankTypeDO bankTypeDO) {
        try {
            // 查询该题库是否已有这个题型
            QueryWrapper<BankTypeDO> wrapper = new QueryWrapper<BankTypeDO>()
                    .eq("bank_id", bankTypeDO.getBankId())
                    .eq("bank_type", bankTypeDO.getBankType());
            BankTypeDO bt = bankTypeService.getOne(wrapper);
            if (bt != null) {
                return Result.build(ResultEnum.ERROR.getCode(), "题库中已存在该题型，请勿重复添加！");
            }
            bankTypeDO.setId(idWorker.nextId() + "");
            bankTypeService.save(bankTypeDO);
            return Result.ok("添加成功！");
        } catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "添加失败！");
        }
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        try {
            bankTypeService.removeById(id);
            return Result.ok("删除成功！");
        } catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "删除失败！");
        }
    }

}

