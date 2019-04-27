package com.exam.utils;

import com.exam.constant.TypeEnum;
import com.exam.pojo.ChoiceDO;
import com.exam.pojo.CodeDO;
import com.exam.pojo.CompletionDO;
import com.exam.pojo.QuestionDO;
import com.exam.pojo.TrueFalseDO;
import com.exam.vo.QuestionVO;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 将题目集合转成QuestionVO的工具类
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/4/21 0021 上午 9:58
 */
public class QuestionUtils {

    private QuestionUtils() {}

    /**
     * 选择题转VO
     */
    public static List<QuestionVO> Choice2VO(List<ChoiceDO> list) {

        List<QuestionVO> questionList = Lists.newArrayList();

        list.forEach(e->{
            QuestionVO vo = new QuestionVO();
            vo.setId(e.getChoiceId());
            vo.setTitle(e.getChoiceTitle());
            vo.setScore(e.getChoiceScore());
            vo.setDifficulty(e.getChoiceDifficulty());
            vo.setTypeId(e.getChoiceType());
            questionList.add(vo);
        });

        return questionList;
    }

    /**
     * 判断题转VO
     */
    public static List<QuestionVO> trueFalse2VO(List<TrueFalseDO> list) {
        List<QuestionVO> questionList = Lists.newArrayList();

        list.forEach(e->{
            QuestionVO vo = new QuestionVO();
            vo.setId(e.getTfId());
            vo.setTitle(e.getTfTitle());
            vo.setScore(e.getTfScore());
            vo.setDifficulty(e.getTfDifficulty());
            vo.setTypeId(TypeEnum.JUDGEMENT.getCode());
            questionList.add(vo);
        });

        return questionList;
    }

    /**
     * 填空题转VO
     */
    public static List<QuestionVO> completion2VO(List<CompletionDO> list) {
        List<QuestionVO> questionList = Lists.newArrayList();

        list.forEach(e->{
            QuestionVO vo = new QuestionVO();
            vo.setId(e.getCompId());
            vo.setTitle(e.getCompTitle());
            vo.setScore(e.getCompScore());
            vo.setDifficulty(e.getCompDifficulty());
            vo.setTypeId(TypeEnum.COMPLETION.getCode());
            questionList.add(vo);
        });

        return questionList;
    }

    /**
     * 编程题转VO
     */
    public static List<QuestionVO> code2VO(List<CodeDO> list) {
        List<QuestionVO> questionList = Lists.newArrayList();

        list.forEach(e->{
            QuestionVO vo = new QuestionVO();
            vo.setId(e.getCodeId());
            vo.setTitle(e.getCodeTitle());
            vo.setScore(e.getCodeScore());
            vo.setDifficulty(e.getCodeDifficulty());
            vo.setTypeId(TypeEnum.PROGRAMMING.getCode());
            questionList.add(vo);
        });

        return questionList;
    }

    /**
     * 主观题转VO
     */
    public static List<QuestionVO> question2VO(List<QuestionDO> list) {
        List<QuestionVO> questionList = Lists.newArrayList();

        list.forEach(e->{
            QuestionVO vo = new QuestionVO();
            vo.setId(e.getQuestionId());
            vo.setTitle(e.getQuestionTitle());
            vo.setScore(e.getQuestionScore());
            vo.setDifficulty(e.getQuestionDifficulty());
            vo.setTypeId(TypeEnum.OTHER.getCode());
            questionList.add(vo);
        });

        return questionList;
    }

}
