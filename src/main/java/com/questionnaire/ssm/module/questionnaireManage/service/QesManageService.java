package com.questionnaire.ssm.module.questionnaireManage.service;

import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import com.questionnaire.ssm.module.questionnaireManage.pojo.CreateQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManage.pojo.DisplayQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManage.pojo.ListQuestionnaireVO;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    void insertQuestionnaire(CreateQuestionnaireVO questionnaireVO) throws Exception;

    /**
     * 获取单个用户/全部用户（userTel 为 null 或者 ‘’是）的问卷信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    List<ListQuestionnaireVO> listQuestionnaireInfoByUserTel(String userTel) throws Exception;

    /**
     * 展示问卷
     *
     * @param questionnaireId 要展示的问卷Id
     * @return
     * @throws Exception
     */
    DisplayQuestionnaireVO getQuestionnaireById(long questionnaireId) throws Exception;

    /**
     * 批量操作问卷
     * 删除、模板化
     *
     * @param questionnaireIds 批量操作问卷的id信息
     * @param questionnaire    批量操作的问卷数据
     * @throws Exception
     */
    @Transactional
    void delOrTemplateQesByIds(List<Long> questionnaireIds, Questionnaire questionnaire) throws Exception;

    /**
     * 批量分享问卷
     *
     * @param questionnaireIds 批量操作的操作的问卷id
     * @return 分享结果
     * @throws Exception
     */
    @Transactional
    void shareQesPaperByIds(List<Long> questionnaireIds) throws Exception;
}