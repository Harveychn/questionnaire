package com.questionnaire.ssm.module.questionnaireManager.service.impl;

import com.questionnaire.ssm.module.generated.mapper.MappingQuestionnaireQuestionMapper;
import com.questionnaire.ssm.module.generated.mapper.QuestionMapper;
import com.questionnaire.ssm.module.generated.mapper.QuestionOptionMapper;
import com.questionnaire.ssm.module.generated.mapper.QuestionnaireMapper;
import com.questionnaire.ssm.module.generated.pojo.MappingQuestionnaireQuestion;
import com.questionnaire.ssm.module.generated.pojo.Question;
import com.questionnaire.ssm.module.generated.pojo.QuestionOption;
import com.questionnaire.ssm.module.generated.pojo.QuestionOptionExample;
import com.questionnaire.ssm.module.questionnaireManager.pojo.CreateQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManager.pojo.QuestionDTO;
import com.questionnaire.ssm.module.questionnaireManager.util.QuestionnaireObjUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:问卷管理 具体实现
 */
@Service
public class QuestionnaireManagerServiceImpl {

    private QuestionnaireMapper questionnaireMapper;
    private QuestionMapper questionMapper;
    private QuestionOptionMapper questionOptionMapper;
    private MappingQuestionnaireQuestionMapper mappingQuestionnaireQuestionMapper;

    @Transactional
    public void insertQuestionnaire(CreateQuestionnaireVO questionnaireVO) throws Exception {
        int qusetionnaireId
                = questionnaireMapper.insertSelective(QuestionnaireObjUtil.toQuestionnaireDO(questionnaireVO));

        QuestionDTO questionDTO = QuestionnaireObjUtil.toQuestionMultiDO(questionnaireVO.getQuestions());
        List<Question> questions = questionDTO.getQuestion();
        List<QuestionOption> options = questionDTO.getQuestionOption();

        MappingQuestionnaireQuestion mapping = new MappingQuestionnaireQuestion();



    }

    @Autowired
    public QuestionnaireManagerServiceImpl(
            QuestionnaireMapper questionnaireMapper,
            QuestionMapper questionMapper,
            QuestionOptionMapper questionOptionMapper,
            MappingQuestionnaireQuestionMapper mappingQuestionnaireQuestionMapper) {
        this.questionnaireMapper = questionnaireMapper;
        this.questionMapper = questionMapper;
        this.questionOptionMapper = questionOptionMapper;
        this.mappingQuestionnaireQuestionMapper = mappingQuestionnaireQuestionMapper;
    }
}
