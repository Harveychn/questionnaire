package com.questionnaire.ssm.module.questionnaireManager.service;

import com.questionnaire.ssm.module.questionnaireManager.pojo.CreateQuestionnaireVO;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:问卷管理模块service 接口
 */
public interface QesManagerService {

    /**
     * 创建问卷
     *
     * @param questionnaireVO
     * @throws Exception 异常时会抛出 OperateDBException
     */
    long insertQuestionnaire(CreateQuestionnaireVO questionnaireVO) throws Exception;
}
