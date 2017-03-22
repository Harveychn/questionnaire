package com.questionnaire.ssm.module.questionnaireManager.service;

import com.questionnaire.ssm.module.questionnaireManager.pojo.CreateQuestionnaireVO;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:问卷管理模块service 接口
 */
public interface QuestionnaireManagerService {

    /**
     * 创建问卷
     *
     * @param questionnaireVO
     * @throws Exception 异常时会抛出 InsertException
     */
    void insertQuestionnaire(CreateQuestionnaireVO questionnaireVO) throws Exception;
}
