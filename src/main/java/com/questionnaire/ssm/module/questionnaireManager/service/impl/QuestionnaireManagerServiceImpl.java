package com.questionnaire.ssm.module.questionnaireManager.service.impl;

import com.questionnaire.ssm.module.generated.mapper.MappingQuestionnaireQuestionMapper;
import com.questionnaire.ssm.module.generated.mapper.QuestionMapper;
import com.questionnaire.ssm.module.generated.mapper.QuestionOptionMapper;
import com.questionnaire.ssm.module.generated.mapper.QuestionnaireMapper;
import com.questionnaire.ssm.module.generated.pojo.MappingQuestionnaireQuestion;
import com.questionnaire.ssm.module.generated.pojo.Question;
import com.questionnaire.ssm.module.generated.pojo.QuestionOption;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.enums.OperateDBEnum;
import com.questionnaire.ssm.module.questionnaireManager.exception.InsertException;
import com.questionnaire.ssm.module.questionnaireManager.pojo.CreateQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManager.pojo.QuestionDTO;
import com.questionnaire.ssm.module.questionnaireManager.service.QuestionnaireManagerService;
import com.questionnaire.ssm.module.questionnaireManager.util.QuestionnaireObjUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:问卷管理 具体实现
 */
@Service
public class QuestionnaireManagerServiceImpl implements QuestionnaireManagerService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionnaireManagerServiceImpl.class);
    private QuestionnaireMapper questionnaireMapper;
    private QuestionMapper questionMapper;
    private QuestionOptionMapper questionOptionMapper;
    private MappingQuestionnaireQuestionMapper mappingQuestionnaireQuestionMapper;

    /**
     * 插入问卷相关信息：问卷信息、题目信息、题目选项信息
     *
     * @param questionnaireVO
     * @throws Exception 抛出操作数据库插入异常信息
     */
    @Override
    @Transactional
    public void insertQuestionnaire(CreateQuestionnaireVO questionnaireVO) throws Exception {
        int questionnaireId
                = questionnaireMapper.insertSelective(QuestionnaireObjUtil.toQuestionnaireDO(questionnaireVO));

        QuestionDTO questionDTO = QuestionnaireObjUtil.toQuestionMultiDO(questionnaireVO.getQuestions());
        List<Question> questions = questionDTO.getQuestion();
        List<QuestionOption> options = questionDTO.getQuestionOption();

        MappingQuestionnaireQuestion mapping = new MappingQuestionnaireQuestion();
        mapping.setQuestionId((long) questionnaireId);

        int questionSize = questions.size();
        for (int order = 0; order < questionSize; order++) {
            int optionId = 0;
            try {
                optionId = questionOptionMapper.insertSelective(options.get(order));
            } catch (Exception e) {
                throw new InsertException(OperateDBEnum.INSERT_FAIL, DBTableEnum.QUESTION_OPTION.getTableName());
            }

            if (optionId == 0) {
                logger.error(OperateDBEnum.INSERT_FAIL.getMessage() + "\n" + DBTableEnum.QUESTION_OPTION.getTableName());
                break;
            }

            questions.get(order).setOptionId((long) optionId);

            int questionId = 0;
            try {
                questionId = questionMapper.insertSelective(questions.get(order));
            } catch (Exception e) {
                throw new InsertException(OperateDBEnum.INSERT_FAIL, DBTableEnum.QUESTION.getTableName());
            }
            if (questionId == 0) {
                logger.error(OperateDBEnum.INSERT_FAIL.getMessage() + "\n" + DBTableEnum.QUESTION.getTableName());
                break;
            }

            mapping.setQuestionId((long) questionId);
            mapping.setQuestionOrder(order);

            int mappingId = 0;
            try {
                mappingId = mappingQuestionnaireQuestionMapper.insertSelective(mapping);
            } catch (Exception e) {
                throw new InsertException(OperateDBEnum.INSERT_FAIL, DBTableEnum.MAPPING_QUESTIONNAIRE_QUESTION.getTableName());
            }

            if (mappingId == 0) {
                logger.error(OperateDBEnum.INSERT_FAIL.getMessage() + "\n" + DBTableEnum.MAPPING_QUESTIONNAIRE_QUESTION.getTableName());
                break;
            }
        }


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
