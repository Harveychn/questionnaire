package com.questionnaire.ssm.module.qesTemplateManage.service.impl;

import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import com.questionnaire.ssm.module.global.enums.UserActionEnum;
import com.questionnaire.ssm.module.global.service.Add2LibraryService;
import com.questionnaire.ssm.module.qesTemplateManage.mapper.QesTemplateManageMapper;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.ListPublicTemplateInfoVO;
import com.questionnaire.ssm.module.qesTemplateManage.service.QesTemplateManageService;
import com.questionnaire.ssm.module.questionnaireManage.service.RecordActionService;
import com.questionnaire.ssm.module.questionnaireManage.util.OperateQuestionnaireUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/3.
 * Description: 问卷管管理Service
 */
@Service
public class QesTemplateManageServiceImpl implements QesTemplateManageService {

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
     * @param userActionEnum 用户添加动作枚举
     * @throws Exception
     */
    @Override
    @Transactional
    public void addToMyTemplateLibrary(Long[] qesTemplateIds, UserActionEnum userActionEnum) throws Exception {
        Date currentDate = new Date();

        for (Long currentTemplateId : qesTemplateIds) {
            addSingleTemplate(currentTemplateId);
        }

        recordActionService.saveMultiRecords(currentDate,
                qesTemplateIds, String.valueOf(userActionEnum.getCode()));
    }

    /**
     * 添加一份公共模板问卷到个人问卷模板
     *
     * @param qesTemplateId 要添加的问卷模板id
     * @throws Exception
     */
    @Transactional
    private void addSingleTemplate(Long qesTemplateId) throws Exception {
        Questionnaire addingTemplate = add2LibraryService.getSharingQesPaperFromDB(qesTemplateId);
        Questionnaire copiedQesPaper = OperateQuestionnaireUtil.copyQesPaperFromPublic(addingTemplate);
        add2LibraryService.Add2PublicOrPrivateLibrary(qesTemplateId, copiedQesPaper);
    }

    private final static Logger logger = LoggerFactory.getLogger(QesTemplateManageServiceImpl.class);
    private QesTemplateManageMapper qesTemplateManageMapper;
    private RecordActionService recordActionService;

    private Add2LibraryService add2LibraryService;

    @Autowired
    public QesTemplateManageServiceImpl(QesTemplateManageMapper qesTemplateManageMapper,
                                        RecordActionService recordActionService,
                                        Add2LibraryService add2LibraryService) {
        this.qesTemplateManageMapper = qesTemplateManageMapper;
        this.recordActionService = recordActionService;
        this.add2LibraryService = add2LibraryService;
    }
}
