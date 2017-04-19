package com.questionnaire.ssm.module.qesTemplateManage.service.impl;

import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import com.questionnaire.ssm.module.global.service.Add2LibraryService;
import com.questionnaire.ssm.module.qesTemplateManage.mapper.QesTemplateManageMapper;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.ListPrivateTemplateInfoVO;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.ListPublicTemplateInfoVO;
import com.questionnaire.ssm.module.qesTemplateManage.service.QesTemplateManageService;
import com.questionnaire.ssm.module.questionnaireManage.util.OperateQuestionnaireUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/3.
 * Description: 问卷管管理Service
 */
@Service
public class QesTemplateManageServiceImpl implements QesTemplateManageService {

    /**
     * 获取个人模板问卷
     *
     * @param userTel 个人账号
     * @return
     * @throws Exception
     */
    @Override
    public List<ListPrivateTemplateInfoVO> listPrivateTemplate(String userTel) throws Exception {
        return qesTemplateManageMapper.listPrivateTemplateInfo(userTel);
    }

    /**
     * 获取公共模板信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<ListPublicTemplateInfoVO> listPublicTemplate() throws Exception {
        return qesTemplateManageMapper.listPublicTemplateInfo();
    }

    /**
     * 添加问卷模板到我的问卷模板库
     *
     * @param qesTemplateIds 要添加的模板id
     * @return 添加到我的模板库结果
     * @throws Exception
     */
    @Override
    @Transactional
    public void addToMyTemplateLibrary(Long[] qesTemplateIds) throws Exception {
        for (Long currentTemplateId : qesTemplateIds) {
           addSingleTemplate(currentTemplateId);
        }
    }

    /**
     * 添加一份公共模板问卷到个人问卷模板
     *
     * @param qesTemplateId 要添加的问卷模板id
     * @return 新生成的问卷ID
     * @throws Exception
     */
    @Transactional
    private Long addSingleTemplate(Long qesTemplateId) throws Exception {
        Questionnaire addingTemplate = add2LibraryService.getSharingQesPaperFromDB(qesTemplateId);
        Questionnaire copiedQesPaper = OperateQuestionnaireUtil.copyQesPaperFromPublic(addingTemplate);
        return add2LibraryService.Add2PublicOrPrivateLibrary(qesTemplateId, copiedQesPaper);
    }

    private final static Logger logger = LoggerFactory.getLogger(QesTemplateManageServiceImpl.class);

    private QesTemplateManageMapper qesTemplateManageMapper;
    private Add2LibraryService add2LibraryService;

    @Autowired
    public QesTemplateManageServiceImpl(QesTemplateManageMapper qesTemplateManageMapper,
                                        Add2LibraryService add2LibraryService) {
        this.qesTemplateManageMapper = qesTemplateManageMapper;
        this.add2LibraryService = add2LibraryService;
    }
}
