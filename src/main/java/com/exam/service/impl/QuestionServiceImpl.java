package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.constant.CoreConstant;
import com.exam.constant.PageParamsConstant;
import com.exam.constant.TypeEnum;
import com.exam.mapper.QuestionMapper;
import com.exam.pojo.ChoiceDO;
import com.exam.pojo.CodeDO;
import com.exam.pojo.CompletionDO;
import com.exam.pojo.Page;
import com.exam.pojo.QuestionAnswerDO;
import com.exam.pojo.QuestionDO;
import com.exam.pojo.TrueFalseDO;
import com.exam.service.ChoiceService;
import com.exam.service.CodeService;
import com.exam.service.CompletionService;
import com.exam.service.QuestionAnswerService;
import com.exam.service.QuestionService;
import com.exam.service.TrueFalseService;
import com.exam.utils.IdWorker;
import com.exam.utils.QuestionUtils;
import com.exam.utils.StringUtils;
import com.exam.vo.QuestionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 其他题型 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-12
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, QuestionDO> implements QuestionService {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionAnswerService questionAnswerService;
    @Autowired
    private ChoiceService choiceService;
    @Autowired
    private TrueFalseService trueFalseService;
    @Autowired
    private CompletionService completionService;
    @Autowired
    private CodeService codeService;

    /**
     * 修改题目
     *
     * @param question
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateQuestion(QuestionDO question) {
        List<QuestionAnswerDO> answerList = question.getAnswerList();
        // 修改题目
        // 删除旧小题
        questionAnswerService.deleteOldAnswer(question);

        // 计算总分
        BigDecimal score = answerList.stream().map(QuestionAnswerDO::getAnswerScore).reduce(BigDecimal::add).get();
        question.setQuestionScore(score);

        // 更新
        questionMapper.updateById(question);
        questionAnswerService.updateBatchById(answerList);

        // 查询所有的答案
        String questionId = question.getQuestionId();
        List<QuestionAnswerDO> answerDOList = questionAnswerService.list(new QueryWrapper<QuestionAnswerDO>().eq("answer_question", questionId));

        // 删除所有更新成功的答案，剩下的就是新增的，批量添加一下
        answerList.removeAll(answerDOList);

        answerList.forEach(e -> {
            e.setAnswerId(idWorker.nextId() + "");
            e.setAnswerQuestion(questionId);
        });

        questionAnswerService.saveBatch(answerList);

    }

    /**
     * 添加题目
     *
     * @param question
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveQuestion(QuestionDO question) {
        List<QuestionAnswerDO> answerList = question.getAnswerList();
        // 添加题目
        // 补全属性，先给题目赋id
        String questionId = idWorker.nextId() + "";
        question.setQuestionId(questionId);
        question.setQuestionStyle(TypeEnum.OTHER.getCode());

        // 遍历小题，设置题目id，设置自己的id，获取分数
        answerList.forEach(e -> {
            e.setAnswerId(idWorker.nextId() + "");
            e.setAnswerQuestion(questionId);
        });

        BigDecimal score = answerList.stream().map(QuestionAnswerDO::getAnswerScore).reduce(BigDecimal::add).get();
        question.setQuestionScore(score);

        questionAnswerService.saveBatch(answerList);

        questionMapper.insert(question);
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @Override
    public Page<QuestionDO> getByPage(Page<QuestionDO> page) {
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
        List<QuestionDO> list = questionMapper.getListByPage(page);

        page.setList(list);
        Integer totalCount = questionMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        // 计算总页数
        page.setTotalPage((int) Math.ceil((page.getTotalCount() * 1.0) / page.getCurrentCount()));
        return page;
    }

    /**
     * 分页查询所有类型题目
     *
     * @param voPage
     * @return
     */
    @Override
    public Page<QuestionVO> getVoByPage(Page<QuestionVO> voPage) {
        if (TypeEnum.JUDGEMENT.getCode().toString().equals(voPage.getParams().get(PageParamsConstant.TYPE_ID).toString())) {
            // 是判断题
            Page<TrueFalseDO> tfPage = new Page<>();
            BeanUtils.copyProperties(voPage, tfPage);
            if (StringUtils.isNotBlank(tfPage.getSortName())) {
                tfPage.setSortName("tf_" + tfPage.getSortName());
            }
            // 分页查询
            tfPage = trueFalseService.getByPage(tfPage);
            List<TrueFalseDO> list = tfPage.getList();
            BeanUtils.copyProperties(tfPage, voPage);

            voPage.setList(QuestionUtils.trueFalse2VO(list));
        } else if (TypeEnum.COMPLETION.getCode().toString().equals(voPage.getParams().get(PageParamsConstant.TYPE_ID).toString())) {
            // 是填空题
            Page<CompletionDO> compPage = new Page<>();
            BeanUtils.copyProperties(voPage, compPage);
            if (StringUtils.isNotBlank(compPage.getSortName())) {
                compPage.setSortName("comp_" + compPage.getSortName());
            }
            // 分页查询
            compPage = completionService.getByPage(compPage);
            List<CompletionDO> list = compPage.getList();
            BeanUtils.copyProperties(compPage, voPage);

            voPage.setList(QuestionUtils.completion2VO(list));
        } else if (TypeEnum.ONE_CHOICE.getCode().toString().equals(voPage.getParams().get(PageParamsConstant.TYPE_ID).toString()) ||
                TypeEnum.MANY_CHOICE.getCode().toString().equals(voPage.getParams().get(PageParamsConstant.TYPE_ID).toString())) {
            // 是选择题
            Page<ChoiceDO> choicePage = new Page<>();
            BeanUtils.copyProperties(voPage, choicePage);
            if (StringUtils.isNotBlank(choicePage.getSortName())) {
                choicePage.setSortName("choice_" + choicePage.getSortName());
            }
            // 分页查询
            choicePage = choiceService.getByPage(choicePage);
            List<ChoiceDO> list = choicePage.getList();
            BeanUtils.copyProperties(choicePage, voPage);

            voPage.setList(QuestionUtils.Choice2VO(list));
        } else if (TypeEnum.PROGRAMMING.getCode().toString().equals(voPage.getParams().get(PageParamsConstant.TYPE_ID).toString())) {
            // 是编程题
            Page<CodeDO> codePage = new Page<>();
            BeanUtils.copyProperties(voPage, codePage);
            if (StringUtils.isNotBlank(codePage.getSortName())) {
                codePage.setSortName("code_" + codePage.getSortName());
            }
            // 分页查询
            codePage = codeService.getByPage(codePage);
            List<CodeDO> list = codePage.getList();
            BeanUtils.copyProperties(codePage, voPage);

            voPage.setList(QuestionUtils.code2VO(list));
        } else {
            // 是其他题
            Page<QuestionDO> questionPage = new Page<>();
            BeanUtils.copyProperties(voPage, questionPage);
            if (StringUtils.isNotBlank(questionPage.getSortName())) {
                questionPage.setSortName("question_" + questionPage.getSortName());
            }
            // 分页查询
            questionPage = this.getByPage(questionPage);
            List<QuestionDO> list = questionPage.getList();
            BeanUtils.copyProperties(questionPage, voPage);

            voPage.setList(QuestionUtils.question2VO(list));
        }
        return voPage;
    }
}
