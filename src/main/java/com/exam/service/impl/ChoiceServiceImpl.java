package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.constant.OtherConstant;
import com.exam.constant.ResultEnum;
import com.exam.mapper.ChoiceMapper;
import com.exam.pojo.ChoiceAnswerDO;
import com.exam.pojo.ChoiceDO;
import com.exam.pojo.Page;
import com.exam.service.ChoiceAnswerService;
import com.exam.service.ChoiceService;
import com.exam.utils.IdWorker;
import com.exam.utils.Result;
import com.exam.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 选择题表 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@Service
public class ChoiceServiceImpl extends ServiceImpl<ChoiceMapper, ChoiceDO> implements ChoiceService {

    @Autowired
    private ChoiceMapper choiceMapper;
    @Autowired
    private ChoiceAnswerService choiceAnswerService;
    @Autowired
    private IdWorker idWorker;

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @Override
    public Page<ChoiceDO> getByPage(Page<ChoiceDO> page) {
        // 处理参数
        page.filterParams();
        // 设置每页显示条数
        if (page.getCurrentCount() == null) {
            page.setCurrentCount(OtherConstant.CURRENT_COUNT);
        }
        // 计算索引
        Integer index = (page.getCurrentPage() - 1) * page.getCurrentCount();
        page.setIndex(index);
        // 查询每页数据
        List<ChoiceDO> list = choiceMapper.getListByPage(page);
        // 过滤正确答案选项
        list.forEach(e -> {
            List<String> numberList = e.getChoiceAnswer().stream().filter(ChoiceAnswerDO::getAnswerTrue)
                    .map(ChoiceAnswerDO::getAnswerNumber).collect(Collectors.toList());
            e.setChoiceTrue(StringUtils.join(numberList, ", "));
        });
        page.setList(list);
        Integer totalCount = choiceMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        // 计算总页数
        page.setTotalPage((int) Math.ceil((page.getTotalCount() * 1.0) / page.getCurrentCount()));
        return page;
    }

    /**
     * 添加或修改单选题
     * @param choice
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result addOrUpdateOneChoice(ChoiceDO choice) {
        // 添加选择题，然后获取选择题的选项进行添加
        List<ChoiceAnswerDO> answerList = choice.getChoiceAnswer();
        // 判断是否添加了选项
        if (answerList.isEmpty()) {
            return Result.build(ResultEnum.ERROR.getCode(), "请添加选项！");
        }
        // 判断是否有多个正确答案
        List<Boolean> trueList = answerList.stream().map(ChoiceAnswerDO::getAnswerTrue).collect(Collectors.toList());
        int count = Collections.frequency(trueList, true);
        if (count != 1) {
            // 不止一个正确答案或者没有正确答案
            return Result.build(ResultEnum.ERROR.getCode(), "单项选择题必须只有一个正确答案！");
        }

        // 判断是修改还是添加
        return updateChoice(choice, answerList);
    }

    /**
     * 添加或修改多选题
     * @param choice
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result addOrUpdateManyChoice(ChoiceDO choice) {
        // 添加选择题，然后获取选择题的选项进行添加
        List<ChoiceAnswerDO> answerList = choice.getChoiceAnswer();
        // 判断是否添加了选项
        if (answerList.isEmpty()) {
            return Result.build(ResultEnum.ERROR.getCode(), "请添加选项！");
        }
        // 判断是否有多个正确答案
        List<Boolean> trueList = answerList.stream().map(ChoiceAnswerDO::getAnswerTrue).collect(Collectors.toList());
        int count = Collections.frequency(trueList, true);
        if (count < 1) {
            // 没有正确答案
            return Result.build(ResultEnum.ERROR.getCode(), "多项选择题必须有正确答案！");
        }

        return updateChoice(choice, answerList);
    }


    @Transactional(rollbackFor = Exception.class)
    protected Result updateChoice(ChoiceDO choice, List<ChoiceAnswerDO> answerList) {
        if (StringUtils.isBlank(choice.getChoiceId())) {
            // 补全属性
            choice.setChoiceId(idWorker.nextId() + "");
            // 获取id，给选项每一项的题目id赋值
            String choiceId = choice.getChoiceId();
            answerList.forEach(e -> {
                e.setAnswerChoice(choiceId);
                e.setAnswerId(idWorker.nextId() + "");
            });
            choiceMapper.insert(choice);
            choiceAnswerService.saveBatch(answerList);
            return Result.ok("添加成功！");
        } else {

            // 删除旧选项
            choiceAnswerService.deleteOldAnswer(choice);

            // 更新
            choiceMapper.updateById(choice);
            choiceAnswerService.updateBatchById(answerList);

            // 查询所有的答案
            String choiceId = choice.getChoiceId();
            QueryWrapper<ChoiceAnswerDO> wrapper = new QueryWrapper<ChoiceAnswerDO>()
                    .eq("answer_choice", choiceId);

            List<ChoiceAnswerDO> answerDOList = choiceAnswerService.list(wrapper);

            // 删除所有更新成功的答案，剩下的就是新增的，批量添加一下
            answerList.removeAll(answerDOList);

            answerList.forEach(e -> {
                e.setAnswerId(idWorker.nextId() + "");
                e.setAnswerChoice(choiceId);
            });

            choiceAnswerService.saveBatch(answerList);

            return Result.ok("修改成功！");
        }
    }


}
