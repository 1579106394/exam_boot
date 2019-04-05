package com.exam.controller;


import com.exam.constant.ResultEnum;
import com.exam.pojo.BankTypeDO;
import com.exam.service.BankTypeService;
import com.exam.utils.IdWorker;
import com.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 * 题库-题型对应表 前端控制器
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-30
 */
@Controller
@RequestMapping("/bankTypeDO")
public class BankTypeController {

    @Autowired
    private BankTypeService bankTypeService;
    @Autowired
    private IdWorker idWorker;

    /**
     * 分配题型
     */
    @RequestMapping(value = "/addType", method = RequestMethod.POST)
    public Result addType(@RequestBody BankTypeDO bankTypeDO) {
        try {
            bankTypeDO.setId(idWorker.nextId() + "");
            bankTypeService.save(bankTypeDO);
            return Result.ok("添加成功！");
        }catch (Exception e) {
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
        }catch (Exception e) {
            return Result.build(ResultEnum.ERROR.getCode(), "删除失败！");
        }
    }

}

