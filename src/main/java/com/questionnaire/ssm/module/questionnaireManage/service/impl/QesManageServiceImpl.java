package com.questionnaire.ssm.module.questionnaireManage.service.impl;

import com.questionnaire.ssm.module.generated.mapper.*;
import com.questionnaire.ssm.module.generated.pojo.*;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.enums.CodeEnum;
import com.questionnaire.ssm.module.global.enums.UserActionEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;

import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.questionnaireManage.mapper.QesManageMapper;
import com.questionnaire.ssm.module.questionnaireManage.pojo.*;
import com.questionnaire.ssm.module.questionnaireManage.service.QesManageService;
import com.questionnaire.ssm.module.questionnaireManage.util.QesManageVODOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:问卷管理 具体实现
 * 1.创建问卷
 */
@Service
public class QesManageServiceImpl implements QesManageService {
    /**
     * 插入问卷（创建问卷）相关信息：问卷信息、题目信息、题目选项信息
     *
     * @param questionnaireVO
     * @return 返回创建完成的问卷ID
     * @throws Exception 抛出操作数据库插入异常信息
     */
    @Override
    @Transactional
    public void insertQuestionnaire(CreateQuestionnaireVO questionnaireVO) throws Exception {
        //检查用户是否登录
        UserValidationUtil.checkUserValid(logger);
        Date currentDate = new Date();
        String userTel = UserValidationUtil.getUserTel(logger);

        Questionnaire questionnaire = QesManageVODOUtil.toQuestionnaireDO(questionnaireVO);

        int insertResult = 0;
        try {
            insertResult = questionnaireMapper.insertSelective(questionnaire);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeEnum.UNKNOWN_ERROR, DBTableEnum.QUESTIONNAIRE.getTableName());
        }
        if (insertResult != 1) {
            logger.error(CodeEnum.DB_INSERT_FAIL.getMessage() + "\n" + DBTableEnum.QUESTIONNAIRE.getTableName());
            throw new OperateDBException(CodeEnum.DB_INSERT_FAIL, DBTableEnum.QUESTIONNAIRE.getTableName());
        }
        insertResult = 0;

        QuestionDTO questionDTO = QesManageVODOUtil.toQuestionMultiDO(questionnaireVO.getQuestions());
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
                logger.error(e.getMessage());
                throw new OperateDBException(CodeEnum.UNKNOWN_ERROR, DBTableEnum.QUESTION_OPTION.getTableName());
            }
            if (insertResult != 1) {
                logger.error(CodeEnum.DB_INSERT_FAIL.getMessage() + "\n" + DBTableEnum.QUESTION_OPTION.getTableName());
                throw new OperateDBException(CodeEnum.DB_INSERT_FAIL, DBTableEnum.QUESTION_OPTION.getTableName());
            }
            insertResult = 0;

            questions.get(order).setOptionId(options.get(order).getOptionId());

            try {
                insertResult = questionMapper.insertSelective(questions.get(order));
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new OperateDBException(CodeEnum.UNKNOWN_ERROR, DBTableEnum.QUESTION.getTableName());
            }
            if (insertResult != 1) {
                logger.error(CodeEnum.DB_INSERT_FAIL.getMessage() + "\n" + DBTableEnum.QUESTION.getTableName());
                throw new OperateDBException(CodeEnum.DB_INSERT_FAIL, DBTableEnum.QUESTION.getTableName());
            }
            insertResult = 0;

            mapping.setQuestionId(questions.get(order).getQuestionId());
            mapping.setQuestionOrder(order);

            try {
                insertResult = mappingQuestionnaireQuestionMapper.insertSelective(mapping);
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new OperateDBException(CodeEnum.UNKNOWN_ERROR, DBTableEnum.MAPPING_QUESTIONNAIRE_QUESTION.getTableName());
            }
            if (insertResult != 1) {
                logger.error(CodeEnum.DB_INSERT_FAIL + "\n" + DBTableEnum.MAPPING_QUESTIONNAIRE_QUESTION.getTableName());
                throw new OperateDBException(CodeEnum.DB_INSERT_FAIL, DBTableEnum.MAPPING_QUESTIONNAIRE_QUESTION.getTableName());
            }
            insertResult = 0;
        }

        /*用户操作问卷记录保存*/
        RecordOperateQuestionnaire recordOperateQuestionnaire = new RecordOperateQuestionnaire();
        recordOperateQuestionnaire.setOperateDate(currentDate);
        recordOperateQuestionnaire.setQuestionnaireId(questionnaire.getQuestionnaireId());
        recordOperateQuestionnaire.setUserTel(userTel);
        recordOperateQuestionnaire.setAction(String.valueOf(UserActionEnum.INSERT_ACTION.getCode()));

        int result = 0;
        try {
            result = recordOperateQuestionnaireMapper.insertSelective(recordOperateQuestionnaire);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeEnum.UNKNOWN_ERROR, DBTableEnum.RECORD_OPERATE_QUESTIONNAIRE.getTableName());
        }
        if (result != 1) {
            throw new OperateDBException(CodeEnum.DB_INSERT_FAIL, DBTableEnum.RECORD_OPERATE_QUESTIONNAIRE.getTableName());
        }
    }

    /**
     * 获取用户创建的所有问卷信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<ListQuestionnaireVO> listQuestionnaireInfoByUserTel(String userTel) throws Exception {
        List<ListQuestionnaireVO> questionnaireVOs = null;
        try {
            questionnaireVOs = qesManageMapper.selectQuestionnaireInfoByUserTel(userTel);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeEnum.UNKNOWN_ERROR, "获取用户问卷信息时出现异常!");
        }
        return questionnaireVOs;
    }

    /**
     * 根据问卷ID查询展示问卷
     *
     * @param questionnaireId 问卷ID
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public DisplayQuestionnaireVO getQuestionnaireById(long questionnaireId) throws Exception {
        Questionnaire questionnaireDO = null;
        try {
            questionnaireDO = questionnaireMapper.selectByPrimaryKey(questionnaireId);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        if (questionnaireDO == null) {
            throw new OperateDBException(CodeEnum.DB_SELECT_FAIL, DBTableEnum.QUESTIONNAIRE.getTableName());
        }

        DisplayQuestionnaireVO displayQuestionnaireVO = QesManageVODOUtil.toDisplayQuestionnaireVO(questionnaireDO);

        List<MappingQuestionnaireQuestion> mapDOList = null;
        MappingQuestionnaireQuestionExample mapDOExample = new MappingQuestionnaireQuestionExample();
        mapDOExample.createCriteria().andQuestionnaireIdEqualTo(questionnaireId);
        try {
            mapDOList = mappingQuestionnaireQuestionMapper.selectByExample(mapDOExample);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeEnum.UNKNOWN_ERROR, DBTableEnum.MAPPING_QUESTIONNAIRE_QUESTION.getTableName());
        }

        long currentQuestionId = 0;
        long currentOptionId = 0;
        List<QuestionVO> questionVOList = new ArrayList<>();
        List<QuestionOptionVO> questionOptionVOList = null;
        Question questionDO = null;
        QuestionOption questionOptionDO = null;

        for (int order = 0; order < mapDOList.size(); order++) {
            currentQuestionId = mapDOList.get(order).getQuestionId();
            questionDO = questionMapper.selectByPrimaryKey(currentQuestionId);

            currentOptionId = questionDO.getOptionId();
            questionOptionDO = questionOptionMapper.selectByPrimaryKey(currentOptionId);
            questionOptionVOList = QesManageVODOUtil.toOptionsItem(questionOptionDO.getOptionString()
                    , questionDO.getQuestionType());

            questionVOList.add(order, QesManageVODOUtil.toQuestionVO(questionDO, questionOptionVOList));
        }

        displayQuestionnaireVO.setQuestions(questionVOList);

        return displayQuestionnaireVO;
    }

    /**
     * 操作单张问卷
     * 删除、共享、模板化
     *
     * @param questionnaireId 要操作问卷的ID
     * @throws Exception
     */
    @Override
    @Transactional
    public void operateQuestionnaireById(long questionnaireId, Questionnaire questionnaire) throws Exception {
        int result = 0;
        QuestionnaireExample questionnaireExample = new QuestionnaireExample();
        questionnaireExample.createCriteria().andQuestionnaireIdEqualTo(questionnaireId);
        questionnaire.setIsVisible(false);
        try {
            result = questionnaireMapper.updateByExampleSelective(questionnaire, questionnaireExample);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeEnum.UNKNOWN_ERROR, DBTableEnum.QUESTIONNAIRE.getTableName());
        }

        if (result != 1) {
            throw new OperateDBException(CodeEnum.DB_UPDATE_FAIL, DBTableEnum.QUESTIONNAIRE.getTableName());
        }
    }

    /**
     * 批量操作问卷
     * 删除、共享、模板化
     *
     * @param questionnaireIds 批量操作问卷的id信息
     * @param questionnaire    批量操作的动作
     * @throws Exception
     */
    @Override
    @Transactional
    public void operateQuestionnaireByIds(List<Long> questionnaireIds, Questionnaire questionnaire) throws Exception {
        int result = 0;
        QuestionnaireExample questionnaireExample = new QuestionnaireExample();
        questionnaireExample.createCriteria().andQuestionnaireIdIn(questionnaireIds);

        try {
            result = questionnaireMapper.updateByExampleSelective(questionnaire, questionnaireExample);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeEnum.UNKNOWN_ERROR, DBTableEnum.QUESTIONNAIRE.getTableName());
        }

        if (result != questionnaireIds.size()) {
            throw new OperateDBException(CodeEnum.DB_UPDATE_FAIL, DBTableEnum.QUESTIONNAIRE.getTableName());
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(QesManageServiceImpl.class);
    private QuestionnaireMapper questionnaireMapper;
    private QuestionMapper questionMapper;
    private QuestionOptionMapper questionOptionMapper;
    private MappingQuestionnaireQuestionMapper mappingQuestionnaireQuestionMapper;
    private RecordOperateQuestionnaireMapper recordOperateQuestionnaireMapper;
    private QesManageMapper qesManageMapper;

    @Autowired
    public QesManageServiceImpl(QuestionnaireMapper questionnaireMapper,
                                QuestionMapper questionMapper,
                                QuestionOptionMapper questionOptionMapper,
                                MappingQuestionnaireQuestionMapper mappingQuestionnaireQuestionMapper,
                                RecordOperateQuestionnaireMapper recordOperateQuestionnaireMapper,
                                QesManageMapper qesManageMapper) {
        this.questionnaireMapper = questionnaireMapper;
        this.questionMapper = questionMapper;
        this.questionOptionMapper = questionOptionMapper;
        this.mappingQuestionnaireQuestionMapper = mappingQuestionnaireQuestionMapper;
        this.recordOperateQuestionnaireMapper = recordOperateQuestionnaireMapper;
        this.qesManageMapper = qesManageMapper;
    }
}
