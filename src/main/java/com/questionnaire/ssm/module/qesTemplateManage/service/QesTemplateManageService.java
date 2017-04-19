package com.questionnaire.ssm.module.qesTemplateManage.service;

import com.questionnaire.ssm.module.qesTemplateManage.pojo.ListPrivateTemplateInfoVO;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.ListPublicTemplateInfoVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/30.
 * Description: 问卷模板管理service
 */
public interface QesTemplateManageService {

    /**
     * 获取个人问卷信息
     *
     * @param userTel 个人账号
     * @return
     * @throws Exception
     */
    List<ListPrivateTemplateInfoVO> listPrivateTemplate(String userTel) throws Exception;

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
     * @return 添加到我的模板库结果
     * @throws Exception
     */
    @Transactional
    void addToMyTemplateLibrary(Long[] qesTemplateIds) throws Exception;
}
