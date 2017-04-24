package com.questionnaire.ssm.module.questionnaireManage.service.impl;

import com.questionnaire.ssm.module.generated.mapper.*;
import com.questionnaire.ssm.module.generated.pojo.*;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;

import com.questionnaire.ssm.module.global.service.Add2LibraryService;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.questionnaireManage.mapper.QesManageMapper;
import com.questionnaire.ssm.module.questionnaireManage.pojo.*;
import com.questionnaire.ssm.module.questionnaireManage.service.QesManageService;
import com.questionnaire.ssm.module.questionnaireManage.util.OperateQuestionnaireUtil;
import com.questionnaire.ssm.module.questionnaireManage.util.QesManageVODOUtil;
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
        /*获取前端视图数据中的问卷信息*/
        Questionnaire questionnaire = QesManageVODOUtil.toQuestionnaireDO(questionnaireVO);

        try {
            questionnaireMapper.insertSelective(questionnaire);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL, DBTableEnum.QUESTIONNAIRE.getTableName());
        }

        /*获取视图数据中的问题以及问题选项信息*/
        List<QuestionWithBLOBs> questionList = QesManageVODOUtil.toQuestionMultiDO(questionnaireVO.getQuestions());
        /*组织问卷问题对应信息*/
        MappingQuestionnaireQuestion mapping = new MappingQuestionnaireQuestion();
        mapping.setQuestionnaireId(questionnaire.getQuestionnaireId());

        int questionSize = questionList.size();
        for (int order = 0; order < questionSize; order++) {
            try {
                questionMapper.insertSelective(questionList.get(order));
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL, DBTableEnum.QUESTION.getTableName());
            }

            mapping.setQuestionId(questionList.get(order).getQuestionId());
            mapping.setQuestionOrder(order);

            try {
                mappingQuestionnaireQuestionMapper.insertSelective(mapping);
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL, DBTableEnum.MAPPING_QUESTIONNAIRE_QUESTION.getTableName());
            }
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
            questionnaireVOs = qesManageMapper.listQuestionnaireInfoByUserTel(userTel);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, "获取用户问卷信息时出现异常!");
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
            throw new OperateDBException(CodeForVOEnum.DB_SELECT_FAIL, DBTableEnum.QUESTIONNAIRE.getTableName());
        }

        DisplayQuestionnaireVO displayQuestionnaireVO = QesManageVODOUtil.toDisplayQuestionnaireVO(questionnaireDO);

        /*根据查询出来的问卷ID查询问卷中问题*/
        List<MappingQuestionnaireQuestion> mapDOList = null;
        MappingQuestionnaireQuestionExample mapDOExample = new MappingQuestionnaireQuestionExample();
        mapDOExample.createCriteria().andQuestionnaireIdEqualTo(questionnaireId);
        try {
            mapDOList = mappingQuestionnaireQuestionMapper.selectByExample(mapDOExample);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, DBTableEnum.MAPPING_QUESTIONNAIRE_QUESTION.getTableName());
        }

        long currentQuestionId = 0;
        List<QuestionVO> questionVOList = new ArrayList<>();
        List<QuestionOptionVO> questionOptionVOList = null;
        QuestionWithBLOBs questionWithBLOBs = null;
        String currentOptionString = null;

        for (int order = 0; order < mapDOList.size(); order++) {
            currentQuestionId = mapDOList.get(order).getQuestionId();
            questionWithBLOBs = questionMapper.selectByPrimaryKey(currentQuestionId);

            currentOptionString = questionWithBLOBs.getOptionString();
            //根据问题类型以及问题选线文本切割获取视图层可以展示的问题信息
            questionOptionVOList = QesManageVODOUtil.toOptionsItem(currentOptionString
                    , questionWithBLOBs.getQuestionType());

            questionVOList.add(order, QesManageVODOUtil.toQuestionVO(questionWithBLOBs, questionOptionVOList));
        }

        displayQuestionnaireVO.setQuestions(questionVOList);

        return displayQuestionnaireVO;
    }

    /**
     * 批量操作问卷
     * 删除、模板化
     *
     * @param questionnaireIds 批量操作问卷的id信息
     * @param questionnaire    批量操作的动作
     * @throws Exception
     */
    @Override
    @Transactional
    public void delOrTemplateQesByIds(List<Long> questionnaireIds, Questionnaire questionnaire) throws Exception {
        QuestionnaireExample questionnaireExample = new QuestionnaireExample();
        questionnaireExample.createCriteria().andQuestionnaireIdIn(questionnaireIds);

        try {
            questionnaireMapper.updateByExampleSelective(questionnaire, questionnaireExample);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_UPDATE_FAIL, DBTableEnum.QUESTIONNAIRE.getTableName());
        }
    }

    /**
     * 批量分享问卷为公共模板
     *
     * @param questionnaireIds 批量操作的操作的问卷id
     * @return 分享结果
     * @throws Exception
     */
    @Override
    @Transactional
    public void shareQesPaperByIds(List<Long> questionnaireIds) throws Exception {
        QuestionnaireExample questionnaireExample = new QuestionnaireExample();
        questionnaireExample.createCriteria().andQuestionnaireIdIn(questionnaireIds);
        //原问卷share设置为true
        try {
            questionnaireMapper.updateByExampleSelective(OperateQuestionnaireUtil.makeShareAttrTrue(), questionnaireExample);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_UPDATE_FAIL, DBTableEnum.QUESTIONNAIRE.getTableName());
        }
        //复制一份新问卷
        for (Long currentQesId : questionnaireIds) {
            shareSingleQuestionnaire(currentQesId);
        }
    }

    /**
     * 查询回收站问卷
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<ListTempDelQesPaperVO> listTempDelQesPaperByUserTel(String userTel) throws Exception {
        return qesManageMapper.listTempDelQesPaperByUserTel(userTel);
    }


    /**
     * 分享问卷（复制问卷信息并且重新组织问卷-题目对应关系）
     *
     * @param questionnaireId
     * @throws Exception
     */
    @Transactional
    private Long shareSingleQuestionnaire(Long questionnaireId) throws Exception {
        Questionnaire sharingQesPaper = add2LibraryService.getSharingQesPaperFromDB(questionnaireId);
        Questionnaire copyQesPaper = OperateQuestionnaireUtil.copyQesPaper2Public(sharingQesPaper);
        return add2LibraryService.Add2PublicOrPrivateLibrary(questionnaireId, copyQesPaper);
    }


    private static final Logger logger = LoggerFactory.getLogger(QesManageServiceImpl.class);
    private QuestionnaireMapper questionnaireMapper;
    private QuestionMapper questionMapper;
    private MappingQuestionnaireQuestionMapper mappingQuestionnaireQuestionMapper;
    private QesManageMapper qesManageMapper;

    private Add2LibraryService add2LibraryService;

    @Autowired
    public QesManageServiceImpl(QuestionnaireMapper questionnaireMapper,
                                QuestionMapper questionMapper,
                                MappingQuestionnaireQuestionMapper mappingQuestionnaireQuestionMapper,
                                QesManageMapper qesManageMapper,
                                Add2LibraryService add2LibraryService) {
        this.questionnaireMapper = questionnaireMapper;
        this.questionMapper = questionMapper;
        this.mappingQuestionnaireQuestionMapper = mappingQuestionnaireQuestionMapper;
        this.qesManageMapper = qesManageMapper;
        this.add2LibraryService = add2LibraryService;
    }
}