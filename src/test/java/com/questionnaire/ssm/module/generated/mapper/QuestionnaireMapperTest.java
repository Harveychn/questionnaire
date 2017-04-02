package com.questionnaire.ssm.module.generated.mapper;

import com.questionnaire.ssm.module.generated.pojo.*;
import com.questionnaire.ssm.module.questionnaireManage.enums.QuestionTypeEnum;
import com.questionnaire.ssm.module.questionnaireManage.pojo.CreateQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManage.pojo.QuestionVO;
import com.questionnaire.ssm.module.questionnaireManage.pojo.QuestionDTO;
import com.questionnaire.ssm.module.questionnaireManage.pojo.QuestionOptionVO;
import com.questionnaire.ssm.module.questionnaireManage.util.QesManageVODOUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 郑晓辉 on 2017/3/23.
 * Description:
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class QuestionnaireMapperTest {

    @Resource
    private QuestionnaireMapper questionnaireMapper;
    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private QuestionOptionMapper questionOptionMapper;
    @Resource
    private MappingQuestionnaireQuestionMapper mappingQuestionnaireQuestionMapper;

    @Test
//    @Transactional
    public void insertSelective() throws Exception {

        CreateQuestionnaireVO questionnaireVO = new CreateQuestionnaireVO();
        questionnaireVO.setQuestionnaireTitle("测试问卷");
        questionnaireVO.setQuestionnaireSubtitle("开发1111112312313问卷");
        questionnaireVO.setTemplate(false);
        questionnaireVO.setDone(true);
        questionnaireVO.setQuestionnaireDescription("这是一份用于开发过程测试的问卷");

        List<QuestionVO> questionsDemo = new ArrayList<>();
        List<QuestionOptionVO> optionsDemo = null;

        QuestionVO questionVO = null;
        QuestionOptionVO questionOptionVO = null;
        for (int i = 0; i < 20; i++) {
            questionVO = new QuestionVO();
            if (i > 10) {
                questionVO.setQuestionType(QuestionTypeEnum.MULTIPLE_CHOICE.getCode());
                questionVO.setMust(false);
            } else {
                questionVO.setQuestionType(QuestionTypeEnum.SINGLE_CHOICE.getCode());
                questionVO.setMust(true);
            }
            questionVO.setQuestionDescription("xxx问题描述" + (i + 1));
            questionVO.setQuestionContext("xxx问题" + (i + 1));
            optionsDemo = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                questionOptionVO = new QuestionOptionVO();
                questionOptionVO.setOptionOrder(j);
                questionOptionVO.setOption("xxx问题" + (i + 1) + ":xx选项" + (j + 1));
                optionsDemo.add(j, questionOptionVO);
            }
            questionVO.setOptions(optionsDemo);
            questionsDemo.add(i, questionVO);
        }
        questionnaireVO.setQuestions(questionsDemo);


        //=====================================================
        Questionnaire questionnaire = QesManageVODOUtil.toQuestionnaireDO(questionnaireVO);
        int insertResult = 0;
        insertResult = questionnaireMapper.insertSelective(questionnaire);
        assertEquals(insertResult, 1);

        insertResult = 0;

        QuestionDTO questionDTO = QesManageVODOUtil.toQuestionMultiDO(questionnaireVO.getQuestions());
        List<Question> questions = questionDTO.getQuestion();
        List<QuestionOption> options = questionDTO.getQuestionOption();

        MappingQuestionnaireQuestion mapping = new MappingQuestionnaireQuestion();
        mapping.setQuestionnaireId(questionnaire.getQuestionnaireId());

        int questionSize = questions.size();
        for (int order = 0; order < questionSize; order++) {
            insertResult = 0;
            insertResult = questionOptionMapper.insertSelective(options.get(order));

            assertEquals(insertResult, 1);
            insertResult = 0;

            questions.get(order).setOptionId(options.get(order).getOptionId());

            insertResult = questionMapper.insertSelective(questions.get(order));
            assertEquals(insertResult, 1);
            insertResult = 0;

            mapping.setQuestionId(questions.get(order).getQuestionId());
            mapping.setQuestionOrder(order);


            insertResult = mappingQuestionnaireQuestionMapper.insertSelective(mapping);
            assertEquals(insertResult, 1);
            insertResult = 0;
        }

    }

    @Test
    public void updateMultiQuestionnaires() throws Exception {
        Long[] questionnaireIds = {9L, 10L, 11L};
        int result = 0;
        QuestionnaireExample questionnaireExample = new QuestionnaireExample();
        questionnaireExample.createCriteria().andQuestionnaireIdIn(Arrays.asList(questionnaireIds));

        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setIsVisible(true);

        result = questionnaireMapper.updateByExampleSelective(questionnaire, questionnaireExample);

        assertEquals(3, result);
    }
}