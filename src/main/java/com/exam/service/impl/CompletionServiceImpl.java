package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.constant.OtherConstant;
import com.exam.constant.PatternConstant;
import com.exam.constant.ResultEnum;
import com.exam.mapper.CompletionMapper;
import com.exam.pojo.CompletionAnswerDO;
import com.exam.pojo.CompletionDO;
import com.exam.pojo.Page;
import com.exam.service.CompletionAnswerService;
import com.exam.service.CompletionService;
import com.exam.utils.IdWorker;
import com.exam.utils.Result;
import com.exam.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 填空题表 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@Service
public class CompletionServiceImpl extends ServiceImpl<CompletionMapper, CompletionDO> implements CompletionService {

    @Autowired
    private CompletionMapper completionMapper;
    @Autowired
    private CompletionAnswerService completionAnswerService;
    @Autowired
    private IdWorker idWorker;

    private static final Pattern UNDER_LINE_PATTERN = PatternConstant.THREE_UNDER_LINE_PATTERN;

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @Override
    public Page<CompletionDO> getByPage(Page<CompletionDO> page) {
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
        List<CompletionDO> list = completionMapper.getListByPage(page);

        // 将下划线设置为答案
        list.forEach(e -> {
            List<CompletionAnswerDO> answerList = e.getAnswerList();
            answerList.forEach(answer -> {
                Matcher matcher = UNDER_LINE_PATTERN.matcher(e.getCompContent());
                String newAnswer = matcher.replaceFirst("<span style='color: red;text-decoration:underline;font-size: 14px'>" + answer.getAnswerContent() + "</span>");
                e.setCompContent(newAnswer);
            });
        });


        page.setList(list);
        Integer totalCount = completionMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        // 计算总页数
        page.setTotalPage((int) Math.ceil((page.getTotalCount() * 1.0) / page.getCurrentCount()));
        return page;
    }

    /**
     * 添加或修改填空题
     *
     * @param completion
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveOrUpdateCompletion(CompletionDO completion) {

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
            completionMapper.insert(completion);
            return Result.ok("添加成功！");
        } else {
            // 否则，为修改

            // 答案列表为最新答案列表，根据id和答案列表删除数据库中不存在的答案
            completionAnswerService.deleteOldAnswer(completion);

            completionAnswerService.updateBatchById(answerList);
            completionMapper.updateById(completion);

            // 查询所有的答案
            String comId = completion.getCompId();
            QueryWrapper<CompletionAnswerDO> wrapper = new QueryWrapper<CompletionAnswerDO>()
                    .eq("answer_comp", comId);

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
