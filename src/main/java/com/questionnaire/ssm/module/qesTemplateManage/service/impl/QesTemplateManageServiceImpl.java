package com.questionnaire.ssm.module.qesTemplateManage.service.impl;

import com.questionnaire.ssm.module.generated.mapper.QuestionnaireMapper;
import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import com.questionnaire.ssm.module.generated.pojo.QuestionnaireExample;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.global.service.Add2LibraryService;
import com.questionnaire.ssm.module.qesTemplateManage.mapper.QesTemplateManageMapper;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.PrivateTemplateInfoVO;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.PublicTemplateInfoVO;
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
    public List<PrivateTemplateInfoVO> listPrivateTemplate(String userTel) throws Exception {
        return qesTemplateManageMapper.listPrivateTemplateInfo(userTel);
    }

    /**
     * 获取公共模板信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<PublicTemplateInfoVO> listPublicTemplate() throws Exception {
        return qesTemplateManageMapper.listPublicTemplateInfo();
    }

    /**
     * 批量删除模板到回收站
     *
     * @param qesTemplateIds
     * @param qesAction
     * @throws Exception
     */
    @Override
    @Transactional
    public void delTemplateByIds(List<Long> qesTemplateIds, Questionnaire qesAction) throws Exception {
        QuestionnaireExample questionnaireExample = new QuestionnaireExample();
        questionnaireExample.createCriteria().andQuestionnaireIdIn(qesTemplateIds);
        //只改变可见状态
        try {
            questionnaireMapper.updateByExampleSelective(qesAction, questionnaireExample);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_UPDATE_FAIL, DBTableEnum.QUESTIONNAIRE.getTableName());
        }
    }

    /**
     * 添加个人模板到公共模板库
     *
     * @param qesTemplateIds
     * @throws Exception
     */
    @Transactional
    public void add2PublicTemplateLib(List<Long> qesTemplateIds) throws Exception {
        for (Long currentTemplateId : qesTemplateIds) {
            addSingleTemplate2Public(currentTemplateId);
        }
    }

    /**
     * 添加一份个人模板问卷到公共问卷模板
     *
     * @param qesTemplateId 要添加的问卷模板id
     * @return 新生成的问卷ID
     * @throws Exception
     */
    @Transactional
    private Long addSingleTemplate2Public(Long qesTemplateId) throws Exception {
        Questionnaire addingTemplate = add2LibraryService.getSharingQesPaperFromDB(qesTemplateId);
        //从个人模板库中复制信息到公共模板库中，得到未编辑问卷ID的问卷信息实体
        Questionnaire copiedQesPaper = OperateQuestionnaireUtil.copyQesPaper2Public(addingTemplate);
        return add2LibraryService.copiedQesPaperId(qesTemplateId, copiedQesPaper);
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
    public void add2MyTemplateLibrary(List<Long> qesTemplateIds) throws Exception {
        for (Long currentTemplateId : qesTemplateIds) {
            addSingleTemplate2Private(currentTemplateId);
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
    private Long addSingleTemplate2Private(Long qesTemplateId) throws Exception {
        Questionnaire addingTemplate = add2LibraryService.getSharingQesPaperFromDB(qesTemplateId);
        //从公共模板库复制模板信息到个人模板信息库中，得到未编辑问卷ID的问卷信息实体
        Questionnaire copiedQesPaper = OperateQuestionnaireUtil.copyQesPaperFromPublic(addingTemplate);
        return add2LibraryService.copiedQesPaperId(qesTemplateId, copiedQesPaper);
    }

    private final static Logger logger = LoggerFactory.getLogger(QesTemplateManageServiceImpl.class);

    private QesTemplateManageMapper qesTemplateManageMapper;
    private Add2LibraryService add2LibraryService;
    private QuestionnaireMapper questionnaireMapper;

    @Autowired
    public QesTemplateManageServiceImpl(QesTemplateManageMapper qesTemplateManageMapper,
                                        Add2LibraryService add2LibraryService,
                                        QuestionnaireMapper questionnaireMapper) {
        this.qesTemplateManageMapper = qesTemplateManageMapper;
        this.add2LibraryService = add2LibraryService;
        this.questionnaireMapper = questionnaireMapper;
    }
}
