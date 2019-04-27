package com.exam.controller;


import com.exam.constant.ResultEnum;
import com.exam.pojo.CompileDO;
import com.exam.service.CompileService;
import com.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 编译器表 前端控制器
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-19
 */
@RestController
@RequestMapping("/compile")
public class CompileController {

    @Autowired
    private CompileService compileService;

    /**
     * 查询所有编译器
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result all() {
        try {
            List<CompileDO> list = compileService.list();
            return Result.ok(list);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "查询失败！");
        }
    }


}

