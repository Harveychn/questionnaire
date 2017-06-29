package com.questionnaire.ssm.module.qesTemplateManage.service;

import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.PrivateTemplateInfoVO;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.PublicTemplateInfoVO;
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
    List<PrivateTemplateInfoVO> listPrivateTemplate(String userTel) throws Exception;

    /**
     * 获取公共模板
     *
     * @return
     * @throws Exception
     */
    List<PublicTemplateInfoVO> listPublicTemplate() throws Exception;

    /**
     * 删除模板
     *
     * @param qesTemplateIds
     * @throws Exception
     */
    @Transactional
    void delTemplateByIds(List<Long> qesTemplateIds, Questionnaire qesAction) throws Exception;

    /**
     * 添加个人模板到公共模板库
     *
     * @param qesTemplateIds
     * @throws Exception
     */
    @Transactional
    void add2PublicTemplateLib(List<Long> qesTemplateIds) throws Exception;

    /**
     * 添加公共模板到个人模板库
     *
     * @param qesTemplateIds 要添加的模板id
     * @return 添加到我的模板库结果
     * @throws Exception
     */
    @Transactional
    void add2MyTemplateLibrary(List<Long> qesTemplateIds) throws Exception;
}
