package com.questionnaire.ssm.module.qesTemplateManage.service.impl;

import com.questionnaire.ssm.module.global.enums.UserActionEnum;
import com.questionnaire.ssm.module.global.service.Add2LibraryService;
import com.questionnaire.ssm.module.global.service.CheckDuplicateService;
import com.questionnaire.ssm.module.global.util.ListArrayUtil;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.qesTemplateManage.mapper.QesTemplateManageMapper;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.Add2MyTemplateResultVO;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.ListPrivateTemplateInfoVO;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.ListPublicTemplateInfoVO;
import com.questionnaire.ssm.module.qesTemplateManage.service.QesTemplateManageService;
import com.questionnaire.ssm.module.qesTemplateManage.util.ActionCode2StrUtil;
import com.questionnaire.ssm.module.questionnaireManage.service.RecordActionService;
import com.questionnaire.ssm.module.questionnaireManage.util.OperateQuestionnaireUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
     * @return 添加到我的模板库结果
     * @throws Exception
     */
    @Override
    @Transactional
    public Add2MyTemplateResultVO addToMyTemplateLibrary(Long[] qesTemplateIds, UserActionEnum userActionEnum) throws Exception {
        Date currentDate = new Date();
        List<Long> checkedTemplateIds = new ArrayList<>();

        for (Long checkingTemplateId : qesTemplateIds) {
            if (!checkDuplicateService.isAdded2MyTemplate(checkingTemplateId, UserValidationUtil.getUserTel(logger))) {
                checkedTemplateIds.add(checkingTemplateId);
            }
        }
        if (checkedTemplateIds.size() <= 0) {
            return new Add2MyTemplateResultVO(qesTemplateIds.length, 0);
        }

        for (Long currentTemplateId : checkedTemplateIds) {
            Long newId = addSingleTemplate(currentTemplateId);
            recordActionService.saveRecord(currentDate,
                    newId, String.valueOf(UserActionEnum.COPY_FROM_PUBLIC_TEMPLATE.getCode()));
        }

        recordActionService.saveMultiRecords(currentDate,
                ListArrayUtil.list2Array(checkedTemplateIds), String.valueOf(userActionEnum.getCode()));

        return new Add2MyTemplateResultVO(qesTemplateIds.length - checkedTemplateIds.size(),
                checkedTemplateIds.size());
    }

    /**
     * 获取个人模板问卷
     *
     * @param userTel 个人账号
     * @return
     * @throws Exception
     */
    @Override
    public List<ListPrivateTemplateInfoVO> listPrivateTemplate(String userTel) throws Exception {
        List<ListPrivateTemplateInfoVO> privateTemplateInfoVOList = qesTemplateManageMapper.listPrivateTemplateInfo(userTel);
        for (ListPrivateTemplateInfoVO privateTemplateInfoVO : privateTemplateInfoVOList) {
            String actionForVO = ActionCode2StrUtil.toVOString(privateTemplateInfoVO.getOperate_action());
            privateTemplateInfoVO.setOperate_action(actionForVO);
        }
        return privateTemplateInfoVOList;
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
    private RecordActionService recordActionService;

    private Add2LibraryService add2LibraryService;
    private CheckDuplicateService checkDuplicateService;

    @Autowired
    public QesTemplateManageServiceImpl(QesTemplateManageMapper qesTemplateManageMapper,
                                        RecordActionService recordActionService,
                                        Add2LibraryService add2LibraryService,
                                        CheckDuplicateService checkDuplicateService) {
        this.qesTemplateManageMapper = qesTemplateManageMapper;
        this.recordActionService = recordActionService;
        this.add2LibraryService = add2LibraryService;
        this.checkDuplicateService = checkDuplicateService;
    }
}
