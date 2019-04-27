package com.exam.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.exam.pojo.PaperDO;
import com.exam.service.PaperService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/4/24 0024 下午 5:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PaperServiceImplTest {

    @Autowired
    private PaperService paperService;

    /**
     * 测试查询试卷所有题目
     */
    @Test
    public void getQuestion() {
        String paperId = "1119583466745303040";
        PaperDO paper = paperService.getQuestion(paperId);
        System.out.println(JSONObject.toJSONString(paper));
    }
}