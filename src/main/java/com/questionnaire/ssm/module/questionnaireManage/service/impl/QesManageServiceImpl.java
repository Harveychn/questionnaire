package com.questionnaire.ssm.module.questionnaireManage.service.impl;

import com.questionnaire.ssm.module.generated.mapper.*;
import com.questionnaire.ssm.module.generated.pojo.*;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.enums.UserActionEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;

import com.questionnaire.ssm.module.global.service.Add2LibraryService;
import com.questionnaire.ssm.module.global.service.CheckDuplicateService;
import com.questionnaire.ssm.module.global.util.ListArrayUtil;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.questionnaireManage.mapper.QesManageMapper;
import com.questionnaire.ssm.module.questionnaireManage.pojo.*;
import com.questionnaire.ssm.module.questionnaireManage.service.QesManageService;
import com.questionnaire.ssm.module.questionnaireManage.service.RecordActionService;
import com.questionnaire.ssm.module.questionnaireManage.util.OperateQuestionnaireUtil;
import com.questionnaire.ssm.module.questionnaireManage.util.QesManageVODOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
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
        //检查用户是否登录
        UserValidationUtil.checkUserValid(logger);
        /*用户开始业务操作时间*/
        Date currentDate = new Date();
        /*获取前端视图数据中的问卷信息*/
        Questionnaire questionnaire = QesManageVODOUtil.toQuestionnaireDO(questionnaireVO);

        try {
            questionnaireMapper.insertSelective(questionnaire);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL, DBTableEnum.QUESTIONNAIRE.getTableName());
        }
        /*获取视图数据中的问题以及问题选项信息*/
        QuestionDTO questionDTO = QesManageVODOUtil.toQuestionMultiDO(questionnaireVO.getQuestions());
        List<Question> questions = questionDTO.getQuestion();
        List<QuestionOption> options = questionDTO.getQuestionOption();
        /*组织问卷问题对应信息*/
        MappingQuestionnaireQuestion mapping = new MappingQuestionnaireQuestion();
        mapping.setQuestionnaireId(questionnaire.getQuestionnaireId());

        int questionSize = questions.size();
        for (int order = 0; order < questionSize; order++) {
            try {
                questionOptionMapper.insertSelective(options.get(order));
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL, DBTableEnum.QUESTION_OPTION.getTableName());
            }

            questions.get(order).setOptionId(options.get(order).getOptionId());

            try {
                questionMapper.insertSelective(questions.get(order));
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL, DBTableEnum.QUESTION.getTableName());
            }

            mapping.setQuestionId(questions.get(order).getQuestionId());
            mapping.setQuestionOrder(order);

            try {
                mappingQuestionnaireQuestionMapper.insertSelective(mapping);
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL, DBTableEnum.MAPPING_QUESTIONNAIRE_QUESTION.getTableName());
            }
        }
        //判断是否创建为模板
        recordActionService.saveRecord(currentDate,
                questionnaire.getQuestionnaireId(),
                String.valueOf(questionnaire.getIsTemplate() ?
                        UserActionEnum.CREATE_QUESTIONNAIRE_AS_TEMPLATE.getCode() : UserActionEnum.CREATE_QUESTIONNAIRE.getCode()));
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
     * 批量操作问卷
     * 删除、模板化
     *
     * @param questionnaireIds 批量操作问卷的id信息
     * @param questionnaire    批量操作的动作
     * @throws Exception
     */
    @Override
    @Transactional
    public void delOrTemplateQesByIds(List<Long> questionnaireIds, Questionnaire questionnaire, UserActionEnum userActionEnum) throws Exception {
        Date currentDate = new Date();

        QuestionnaireExample questionnaireExample = new QuestionnaireExample();
        questionnaireExample.createCriteria().andQuestionnaireIdIn(questionnaireIds);

        try {
            questionnaireMapper.updateByExampleSelective(questionnaire, questionnaireExample);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_UPDATE_FAIL, DBTableEnum.QUESTIONNAIRE.getTableName());
        }

        recordActionService.saveMultiRecords(currentDate,
                ListArrayUtil.list2Array(questionnaireIds), String.valueOf(userActionEnum.getCode()));
    }

    /**
     * 批量分享问卷为公共模板
     *
     * @param questionnaireIds 批量操作的操作的问卷id
     * @param userActionEnum   用户操作动作
     * @return 分享结果
     * @throws Exception
     */
    @Override
    @Transactional
    public ShareResultVO shareQesPaperByIds(List<Long> questionnaireIds, UserActionEnum userActionEnum) throws Exception {
        Date currentDate = new Date();

        List<Long> checkedQuestionnaireIds = new ArrayList<>();

        /*检查当前问卷id是否分享过*/
        for (Long checkingQesId : questionnaireIds) {
            //查找未分享的问卷
            if (!checkDuplicateService.isShared(checkingQesId)) {
                checkedQuestionnaireIds.add(checkingQesId);
            }
        }
        /*要操作的全部问卷均为分享过的*/
        if (checkedQuestionnaireIds.size() <= 0) {
            return new ShareResultVO(questionnaireIds.size(), 0);
        }

        QuestionnaireExample questionnaireExample = new QuestionnaireExample();
        questionnaireExample.createCriteria().andQuestionnaireIdIn(checkedQuestionnaireIds);

        try {
            questionnaireMapper.updateByExampleSelective(OperateQuestionnaireUtil.shareAction(), questionnaireExample);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_UPDATE_FAIL, DBTableEnum.QUESTIONNAIRE.getTableName());
        }

        /*批量分享问卷，即复制被分享问卷信息到新的问卷信息中*/
        for (Long currentQuestionnaireId : checkedQuestionnaireIds) {
            Long newId = shareSingleQuestionnaire(currentQuestionnaireId);
            /*保存公共问卷的分享记录，以便查找分享人*/
            recordActionService.saveRecord(currentDate,
                    newId, String.valueOf(UserActionEnum.SHARE_QUESTIONNAIRE_2_PUBLIC_TEMPLATE.getCode()));
        }
        /*操作记录的保存*/
        recordActionService.saveMultiRecords(currentDate,
                ListArrayUtil.list2Array(checkedQuestionnaireIds), String.valueOf(userActionEnum.getCode()));

        return new ShareResultVO(questionnaireIds.size() - checkedQuestionnaireIds.size(),
                checkedQuestionnaireIds.size());
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
    private QuestionOptionMapper questionOptionMapper;
    private MappingQuestionnaireQuestionMapper mappingQuestionnaireQuestionMapper;
    private QesManageMapper qesManageMapper;

    private RecordActionService recordActionService;
    private Add2LibraryService add2LibraryService;
    private CheckDuplicateService checkDuplicateService;

    @Autowired
    public QesManageServiceImpl(QuestionnaireMapper questionnaireMapper,
                                QuestionMapper questionMapper,
                                QuestionOptionMapper questionOptionMapper,
                                MappingQuestionnaireQuestionMapper mappingQuestionnaireQuestionMapper,
                                QesManageMapper qesManageMapper,
                                RecordActionService recordActionService,
                                Add2LibraryService add2LibraryService,
                                CheckDuplicateService checkDuplicateService) {
        this.questionnaireMapper = questionnaireMapper;
        this.questionMapper = questionMapper;
        this.questionOptionMapper = questionOptionMapper;
        this.mappingQuestionnaireQuestionMapper = mappingQuestionnaireQuestionMapper;
        this.qesManageMapper = qesManageMapper;
        this.recordActionService = recordActionService;
        this.add2LibraryService = add2LibraryService;
        this.checkDuplicateService = checkDuplicateService;
    }
}


/*单份问卷操作*/
//    /**
//     * 操作单张问卷
//     * 删除、模板化
//     *
//     * @param questionnaireId 要操作问卷的ID
//     * @throws Exception
//     */
//    @Override
//    @Transactional
//    public void delOrTemplateQesById(long questionnaireId, Questionnaire questionnaire, UserActionEnum userActionEnum) throws Exception {
//        Date currentDate = new Date();
//
//        QuestionnaireExample questionnaireExample = new QuestionnaireExample();
//        questionnaireExample.createCriteria().andQuestionnaireIdEqualTo(questionnaireId);
//        try {
//            questionnaireMapper.updateByExampleSelective(questionnaire, questionnaireExample);
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            throw new OperateDBException(CodeForVOEnum.DB_UPDATE_FAIL, DBTableEnum.QUESTIONNAIRE.getTableName());
//        }
//
//        recordActionService.saveRecord(currentDate, questionnaireId,
//                String.valueOf(userActionEnum.getCode()));
//    }
//
//    /**
//     * 用户分享单份问卷
//     *
//     * @param questionnaireId 要分享问卷的id
//     * @param userActionEnum  用户操作动作
//     * @throws Exception
//     */
//    @Override
//    @Transactional
//    public void shareQesPaperById(Long questionnaireId, UserActionEnum userActionEnum) throws Exception {
//        Date currentDate = new Date();
//        /*用户分享完问卷后，设置用户的问卷isShare为 true 防止重复分享*/
//        QuestionnaireExample questionnaireExample = new QuestionnaireExample();
//        questionnaireExample.createCriteria().andQuestionnaireIdEqualTo(questionnaireId);
//        try {
//            questionnaireMapper.updateByExampleSelective(OperateQuestionnaireUtil.shareAction(), questionnaireExample);
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            throw new OperateDBException(CodeForVOEnum.DB_UPDATE_FAIL, DBTableEnum.QUESTIONNAIRE.getTableName());
//        }
//        /*分享一份问卷*/
//        shareSingleQuestionnaire(questionnaireId);
//
//        recordActionService.saveRecord(currentDate, questionnaireId,
//                String.valueOf(userActionEnum.getCode()));
//    }