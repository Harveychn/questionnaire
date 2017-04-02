package com.questionnaire.ssm.module.questionnaireManage.service.impl;

import com.questionnaire.ssm.module.generated.mapper.RecordOperateQuestionnaireMapper;
import com.questionnaire.ssm.module.generated.pojo.RecordOperateQuestionnaire;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.questionnaireManage.service.RecordActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by 郑晓辉 on 2017/3/30.
 * Description: 记录用户操作关键业务信息的服务
 */
@Service
public class RecordActionServiceImpl implements RecordActionService {

    @Override
    @Transactional
    public void saveRecord(Date operateDate, Long questionnaireId, String actionCode) throws Exception {
        /*获取在线用户名*/
        String userTel = UserValidationUtil.getUserTel(logger);

        RecordOperateQuestionnaire recordOperateQuestionnaire = new RecordOperateQuestionnaire();
        /*操作时间*/
        recordOperateQuestionnaire.setOperateDate(operateDate);
        /*操作问卷的id*/
        recordOperateQuestionnaire.setQuestionnaireId(questionnaireId);
        /*用户名*/
        recordOperateQuestionnaire.setUserTel(userTel);
        /*创建问卷动作*/
        recordOperateQuestionnaire.setAction(actionCode);

        int result = 0;
        try {
            result = recordOperateQuestionnaireMapper.insertSelective(recordOperateQuestionnaire);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, DBTableEnum.RECORD_OPERATE_QUESTIONNAIRE.getTableName());
        }
        if (result != 1) {
            throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL, DBTableEnum.RECORD_OPERATE_QUESTIONNAIRE.getTableName());
        }
    }

    @Override
    @Transactional
    public void saveMultiRecords(Date operateDate, Long[] questionnaireIds, String actionCode) throws Exception {
        /*获取在线用户名*/
        String userTel = UserValidationUtil.getUserTel(logger);

        RecordOperateQuestionnaire recordOperateQuestionnaire = new RecordOperateQuestionnaire();
        /*操作时间*/
        recordOperateQuestionnaire.setOperateDate(operateDate);
        /*用户名*/
        recordOperateQuestionnaire.setUserTel(userTel);
        /*创建问卷动作*/
        recordOperateQuestionnaire.setAction(actionCode);

        int result = 0;
        for (Long questionnaireId : questionnaireIds) {
        /*操作问卷的id*/
            recordOperateQuestionnaire.setQuestionnaireId(questionnaireId);
            try {
                result = recordOperateQuestionnaireMapper.insertSelective(recordOperateQuestionnaire);
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, DBTableEnum.RECORD_OPERATE_QUESTIONNAIRE.getTableName());
            }
            if (result != 1) {
                throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL, DBTableEnum.RECORD_OPERATE_QUESTIONNAIRE.getTableName());
            }
        }
    }

    private final static Logger logger = LoggerFactory.getLogger(RecordActionServiceImpl.class);

    private RecordOperateQuestionnaireMapper recordOperateQuestionnaireMapper;

    @Autowired
    public RecordActionServiceImpl(RecordOperateQuestionnaireMapper recordOperateQuestionnaireMapper) {
        this.recordOperateQuestionnaireMapper = recordOperateQuestionnaireMapper;
    }
}
