package com.questionnaire.ssm.module.generated.mapper;

import com.questionnaire.ssm.module.generated.pojo.MappingQuestionnaireQuestion;
import com.questionnaire.ssm.module.generated.pojo.Question;
import com.questionnaire.ssm.module.generated.pojo.QuestionOption;
import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.enums.OperateDBEnum;
import com.questionnaire.ssm.module.questionnaireManager.enums.QuestionTypeEnum;
import com.questionnaire.ssm.module.questionnaireManager.exception.InsertException;
import com.questionnaire.ssm.module.questionnaireManager.pojo.CreateQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManager.pojo.QuestionDO;
import com.questionnaire.ssm.module.questionnaireManager.pojo.QuestionDTO;
import com.questionnaire.ssm.module.questionnaireManager.pojo.QuestionOptionDO;
import com.questionnaire.ssm.module.questionnaireManager.util.QuestionnaireObjUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    @Transactional
    public void insertSelective() throws Exception {

        CreateQuestionnaireVO questionnaireVO = new CreateQuestionnaireVO();
        questionnaireVO.setQuestionnaireTitle("测试问卷");
        questionnaireVO.setQuestionnaireSubtitle("开发测试问卷");
        questionnaireVO.setTemplate(false);
        questionnaireVO.setDone(true);
        questionnaireVO.setQuestionnaireDescription("这是一份用于开发过程测试的问卷");

        List<QuestionDO> questionsDemo = new ArrayList<>();
        List<QuestionOptionDO> optionsDemo = null;

        QuestionDO questionDO = null;
        QuestionOptionDO questionOptionDO = null;
        for (int i = 0; i < 20; i++) {
            questionDO = new QuestionDO();
            if (i > 10) {
                questionDO.setQuestionType(QuestionTypeEnum.MULTIPLE_CHOICE.getCode());
                questionDO.setMust(false);
            } else {
                questionDO.setQuestionType(QuestionTypeEnum.SINGLE_CHOICE.getCode());
                questionDO.setMust(true);
            }
            questionDO.setQuestionDescription("问题描述" + i);
            questionDO.setQuestionContext("问题" + i);
            optionsDemo = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                questionOptionDO = new QuestionOptionDO();
                questionOptionDO.setOptionOrder(j);
                questionOptionDO.setOption("问题" + i + ":选项" + j);
                optionsDemo.add(j, questionOptionDO);
            }
            questionDO.setOptions(optionsDemo);
            questionsDemo.add(i, questionDO);
        }
        questionnaireVO.setQuestions(questionsDemo);


        //=====================================================
        Questionnaire questionnaire = QuestionnaireObjUtil.toQuestionnaireDO(questionnaireVO);
        int insertResult = 0;
        insertResult = questionnaireMapper.insertSelective(questionnaire);
        assertEquals(insertResult, 1);

        insertResult = 0;

        QuestionDTO questionDTO = QuestionnaireObjUtil.toQuestionMultiDO(questionnaireVO.getQuestions());
        List<Question> questions = questionDTO.getQuestion();
        List<QuestionOption> options = questionDTO.getQuestionOption();

        MappingQuestionnaireQuestion mapping = new MappingQuestionnaireQuestion();
        mapping.setQuestionId(questionnaire.getQuestionnaireId());

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
}