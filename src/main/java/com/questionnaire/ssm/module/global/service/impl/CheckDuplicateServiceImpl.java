package com.questionnaire.ssm.module.global.service.impl;

import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.enums.UserActionEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.global.service.CheckDuplicateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/7.
 * Description: 检查分享、添加到个人模板库动作是否重复
 */
@Service
public class CheckDuplicateServiceImpl implements CheckDuplicateService {

    /**
     * 判断问卷是否共享过
     *
     * @param questionnaireId 判断的问卷id
     * @return true表示添加过
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean isShared(Long questionnaireId) throws Exception {
        RecordOperateQuestionnaireExample example = new RecordOperateQuestionnaireExample();
        example.createCriteria().andQuestionnaireIdEqualTo(questionnaireId);
        example.setOrderByClause("operate_date DESC LIMIT 1");
        /*返回一条最新的操作记录*/
        List<RecordOperateQuestionnaire> recordList = null;
        try {
            recordList = recordOperateQuestionnaireMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_SELECT_FAIL,
                    DBTableEnum.RECORD_OPERATE_QUESTIONNAIRE.getTableName());
        }
        if (recordList.size() <= 0) {
            return false;
        }
        for (RecordOperateQuestionnaire record : recordList) {
            if (record.getAction().equals(String.valueOf(UserActionEnum.ADD_2_PUBLIC_TEMPLATE.getCode()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否添加到个人模板库过
     *
     * @param questionnaireId
     * @param userTel
     * @return true表示添加过
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean isAdded2MyTemplate(Long questionnaireId, String userTel) throws Exception {
        RecordOperateQuestionnaireExample example = new RecordOperateQuestionnaireExample();
        example.createCriteria()
                .andQuestionnaireIdEqualTo(questionnaireId)
                .andUserTelEqualTo(userTel);
        example.setOrderByClause("operate_date DESC LIMIT 1");
        /*返回一条最新的操作记录*/
        List<RecordOperateQuestionnaire> recordList = null;
        try {
            recordList = recordOperateQuestionnaireMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_SELECT_FAIL,
                    DBTableEnum.RECORD_OPERATE_QUESTIONNAIRE.getTableName());
        }
        /*没有该用户对该问卷的操作记录，则说明未添加过*/
        if (recordList.size() <= 0) {
            return false;
        }
        for (RecordOperateQuestionnaire record : recordList) {
            if (record.getAction().equals(String.valueOf(UserActionEnum.ADD_PUBLIC_TEMPLATE_2_MY_TEMPLATE_LIBRARY.getCode()))) {
                return true;
            }
        }
        return false;
    }

    private static final Logger logger = LoggerFactory.getLogger(CheckDuplicateServiceImpl.class);
    private RecordOperateQuestionnaireMapper recordOperateQuestionnaireMapper;

    @Autowired
    public CheckDuplicateServiceImpl(RecordOperateQuestionnaireMapper recordOperateQuestionnaireMapper) {
        this.recordOperateQuestionnaireMapper = recordOperateQuestionnaireMapper;
    }
}
