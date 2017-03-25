package com.questionnaire.ssm.module.questionnaireManager.service.impl;

import com.questionnaire.ssm.module.generated.mapper.MappingQuestionnaireQuestionMapper;
import com.questionnaire.ssm.module.generated.mapper.QuestionMapper;
import com.questionnaire.ssm.module.generated.mapper.QuestionOptionMapper;
import com.questionnaire.ssm.module.generated.mapper.QuestionnaireMapper;
import com.questionnaire.ssm.module.generated.pojo.*;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.enums.OperateDBEnum;
import com.questionnaire.ssm.module.questionnaireManager.exception.InsertException;

import com.questionnaire.ssm.module.questionnaireManager.pojo.CreateQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManager.pojo.QuestionDTO;
import com.questionnaire.ssm.module.questionnaireManager.service.QuestionnaireManagerService;
import com.questionnaire.ssm.module.questionnaireManager.util.QuestionnaireDBObjUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:问卷管理 具体实现
 * 1.创建问卷
 */
@Service
public class QuestionnaireManagerServiceImpl implements QuestionnaireManagerService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionnaireManagerServiceImpl.class);
    private QuestionnaireMapper questionnaireMapper;
    private QuestionMapper questionMapper;
    private QuestionOptionMapper questionOptionMapper;
    private MappingQuestionnaireQuestionMapper mappingQuestionnaireQuestionMapper;

    /**
     * 插入问卷（创建问卷）相关信息：问卷信息、题目信息、题目选项信息
     *
     * @param questionnaireVO
     * @throws Exception 抛出操作数据库插入异常信息
     */
    @Override
    @Transactional
    public void insertQuestionnaire(CreateQuestionnaireVO questionnaireVO) throws Exception {
        Questionnaire questionnaire = QuestionnaireDBObjUtil.toQuestionnaireDO(questionnaireVO);
        int insertResult = 0;
        try {
            insertResult = questionnaireMapper.insertSelective(questionnaire);
        } catch (Exception e) {
            throw new InsertException(OperateDBEnum.INSERT_FAIL, DBTableEnum.QUESTIONNAIRE.getTableName());
        }
        if (insertResult != 1) {
            logger.error(OperateDBEnum.INSERT_FAIL.getMessage() + "\n" + DBTableEnum.QUESTIONNAIRE.getTableName());
            return;
        }
        insertResult = 0;

        QuestionDTO questionDTO = QuestionnaireDBObjUtil.toQuestionMultiDO(questionnaireVO.getQuestions());
        List<Question> questions = questionDTO.getQuestion();
        List<QuestionOption> options = questionDTO.getQuestionOption();

        MappingQuestionnaireQuestion mapping = new MappingQuestionnaireQuestion();
        mapping.setQuestionnaireId(questionnaire.getQuestionnaireId());

        int questionSize = questions.size();
        for (int order = 0; order < questionSize; order++) {
            insertResult = 0;

            try {
                insertResult = questionOptionMapper.insertSelective(options.get(order));
            } catch (Exception e) {
                throw new InsertException(OperateDBEnum.INSERT_FAIL, DBTableEnum.QUESTION_OPTION.getTableName());
            }

            if (insertResult != 1) {
                logger.error(OperateDBEnum.INSERT_FAIL.getMessage() + "\n" + DBTableEnum.QUESTION_OPTION.getTableName());
                break;
            }

            insertResult = 0;

            questions.get(order).setOptionId(options.get(order).getOptionId());

            try {
                insertResult = questionMapper.insertSelective(questions.get(order));
            } catch (Exception e) {
                throw new InsertException(OperateDBEnum.INSERT_FAIL, DBTableEnum.QUESTION.getTableName());
            }
            if (insertResult != 1) {
                logger.error(OperateDBEnum.INSERT_FAIL.getMessage() + "\n" + DBTableEnum.QUESTION.getTableName());
                break;
            }
            insertResult = 0;

            mapping.setQuestionId(questions.get(order).getQuestionId());
            mapping.setQuestionOrder(order);

            try {
                insertResult = mappingQuestionnaireQuestionMapper.insertSelective(mapping);
            } catch (Exception e) {
                throw new InsertException(OperateDBEnum.INSERT_FAIL, DBTableEnum.MAPPING_QUESTIONNAIRE_QUESTION.getTableName());
            }

            if (insertResult != 1) {
                logger.error(OperateDBEnum.INSERT_FAIL.getMessage() + "\n" + DBTableEnum.MAPPING_QUESTIONNAIRE_QUESTION.getTableName());
                break;
            }
            insertResult = 0;
        }
    }

    /**
     * 获取用户创建的所有问卷信息
     *
     * @return
     * @throws Exception
     */
    public List<Questionnaire> listQuestionnaireInfo() throws Exception {
        return new ArrayList<>();
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
