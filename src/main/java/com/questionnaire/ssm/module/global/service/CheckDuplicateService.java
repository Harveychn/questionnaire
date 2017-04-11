package com.questionnaire.ssm.module.global.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 郑晓辉 on 2017/4/7.
 * Description: 检查分享、添加到个人模板库动作是否重复
 */
public interface CheckDuplicateService {
    /**
     * 判断问卷是否共享过
     *
     * @param questionnaireId 判断的问卷id
     * @return true表示添加过
     * @throws Exception
     */
    @Transactional
    boolean isShared(Long questionnaireId) throws Exception;

    /**
     * 判断是否添加到个人模板库过
     *
     * @param questionnaireId 判断的模板id
     * @param userTel         判断的操作用户
     * @return true表示添加过
     * @throws Exception
     */
    @Transactional
    boolean isAdded2MyTemplate(Long questionnaireId, String userTel) throws Exception;
}
