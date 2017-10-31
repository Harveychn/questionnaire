package com.questionnaire.ssm.module.global.service;

import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 郑晓辉 on 2017/4/3.
 * Description:
 */
public interface Add2LibraryService {


    /**
     * 从数据库获取正在分享的问卷信息
     *
     * @param questionnaireId
     * @return
     * @throws Exception
     */
    @Transactional
    Questionnaire getSharingQesPaperFromDB(Long questionnaireId) throws Exception;

    /**
     *复制问卷信息并且重新组织问卷-题目对应关系
     *
     * @param questionnaireId 被操作问卷id
     * @param copiedQesPaper  复制的问卷
     * @return 新生成的问卷id
     * @throws Exception
     */
    @Transactional
    Long copiedQesPaperId(Long questionnaireId, Questionnaire copiedQesPaper) throws Exception;
}
