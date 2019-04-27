package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.constant.CoreConstant;
import com.exam.mapper.CodeMapper;
import com.exam.pojo.CodeAnswerDO;
import com.exam.pojo.CodeDO;
import com.exam.pojo.Page;
import com.exam.service.CodeAnswerService;
import com.exam.service.CodeService;
import com.exam.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 编程题 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-17
 */
@Service
public class CodeServiceImpl extends ServiceImpl<CodeMapper, CodeDO> implements CodeService {

    @Autowired
    private CodeMapper codeMapper;
    @Autowired
    private CodeAnswerService codeAnswerService;
    @Autowired
    private IdWorker idWorker;

    /**
     * 添加编程题
     * @param code
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCode(CodeDO code) {
        List<CodeAnswerDO> answerList = code.getAnswerList();
        // 添加题目
        // 补全属性，先给题目赋id
        String codeId = idWorker.nextId() + "";
        code.setCodeId(codeId);

        // 遍历小题，设置题目id，设置自己的id，获取分数
        answerList.forEach(e -> {
            e.setAnswerId(idWorker.nextId() + "");
            e.setAnswerCode(codeId);
        });

        BigDecimal score = answerList.stream().map(CodeAnswerDO::getAnswerScore).reduce(BigDecimal::add).get();
        code.setCodeScore(score);

        codeAnswerService.saveBatch(answerList);

        codeMapper.insert(code);
    }

    /**
     * 更新编程题
     * @param codeDO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCode(CodeDO codeDO) {
        List<CodeAnswerDO> answerList = codeDO.getAnswerList();
        // 修改题目
        // 删除旧小题
        codeAnswerService.deleteOldAnswer(codeDO);

        // 计算总分
        BigDecimal score = answerList.stream().map(CodeAnswerDO::getAnswerScore).reduce(BigDecimal::add).get();
        codeDO.setCodeScore(score);

        // 更新
        codeMapper.updateById(codeDO);
        codeAnswerService.updateBatchById(answerList);

        // 查询所有的答案
        String codeId = codeDO.getCodeId();
        List<CodeAnswerDO> answerDOList = codeAnswerService.list(new QueryWrapper<CodeAnswerDO>().eq("answer_code", codeId));

        // 删除所有更新成功的答案，剩下的就是新增的，批量添加一下
        answerList.removeAll(answerDOList);

        answerList.forEach(e -> {
            e.setAnswerId(idWorker.nextId() + "");
            e.setAnswerCode(codeId);
        });

        codeAnswerService.saveBatch(answerList);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Override
    public Page<CodeDO> getByPage(Page<CodeDO> page) {
        // 处理参数
        page.filterParams();
        // 设置每页显示条数
        if (page.getCurrentCount() == null) {
            page.setCurrentCount(CoreConstant.CURRENT_COUNT);
        }
        // 计算索引
        Integer index = (page.getCurrentPage() - 1) * page.getCurrentCount();
        page.setIndex(index);
        // 查询每页数据
        List<CodeDO> list = codeMapper.getListByPage(page);

        page.setList(list);
        Integer totalCount = codeMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        // 计算总页数
        page.setTotalPage((int) Math.ceil((page.getTotalCount() * 1.0) / page.getCurrentCount()));
        return page;
    }
}
