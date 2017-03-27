package com.questionnaire.ssm.module.questionnaireManage.service;

import com.questionnaire.ssm.module.questionnaireManage.pojo.CreateQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManage.pojo.DisplayQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManage.pojo.ListQuestionnaireVO;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:问卷管理模块service 接口
 */
public interface QesManageService {

    /**
     * 创建问卷
     *
     * @param questionnaireVO
     * @throws Exception 异常时会抛出 OperateDBException
     */
    void insertQuestionnaire(CreateQuestionnaireVO questionnaireVO) throws Exception;

    /**
     * 获取单个用户/全部用户（userTel 为 null 或者 ‘’是）的问卷信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    List<ListQuestionnaireVO> listQuestionnaireInfoByUserTel(String userTel) throws Exception;

    DisplayQuestionnaireVO getQuestionnaireById(long questionnaireId) throws Exception;
}
