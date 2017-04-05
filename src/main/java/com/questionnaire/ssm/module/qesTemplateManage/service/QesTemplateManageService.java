package com.questionnaire.ssm.module.qesTemplateManage.service;

import com.questionnaire.ssm.module.global.enums.UserActionEnum;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.ListPublicTemplateInfoVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/30.
 * Description: 问卷模板管理service
 */
public interface QesTemplateManageService {

    /**
     * 获取公共模板
     *
     * @return
     * @throws Exception
     */
    List<ListPublicTemplateInfoVO> listPublicTemplate() throws Exception;

    /**
     * 添加问卷模板到我的问卷模板库
     *
     * @param qesTemplateIds 要添加的模板id
     * @param userActionEnum 用户添加动作枚举
     * @throws Exception
     */
    @Transactional
    void addToMyTemplateLibrary(Long[] qesTemplateIds, UserActionEnum userActionEnum) throws Exception;
}
