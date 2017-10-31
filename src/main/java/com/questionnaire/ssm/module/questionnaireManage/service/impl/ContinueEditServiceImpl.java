package com.questionnaire.ssm.module.questionnaireManage.service.impl;

import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import com.questionnaire.ssm.module.global.service.Add2LibraryService;
import com.questionnaire.ssm.module.questionnaireManage.pojo.CreateQesVO;
import com.questionnaire.ssm.module.questionnaireManage.service.ContinueEditService;
import com.questionnaire.ssm.module.questionnaireManage.service.QesManageService;
import com.questionnaire.ssm.module.questionnaireManage.util.OperateQuestionnaireUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/6/11.
 * Description:
 */
@Service
public class ContinueEditServiceImpl implements ContinueEditService {


    /**
     * 继续编辑问卷
     *
     * @param createQesVO
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean continueEdit(CreateQesVO createQesVO) throws Exception {
        if (createQesVO.getQuestionnaireId() == 0L) {
            return false;
        }
        List<Long> delIdList = new ArrayList<>();
        delIdList.add(createQesVO.getQuestionnaireId());
        createQesVO.setQuestionnaireId(0L);
        //删除原先的  (2017-6-11 存在问题：复制问卷的时候问题ID可能被多份问卷ID所引用、存在删除异常)
        qesManageService.delDataForeverQesByIds(delIdList);
        qesManageService.insertQuestionnaire(createQesVO);
        return true;
    }

    private QesManageService qesManageService;

    @Autowired
    public ContinueEditServiceImpl(QesManageService qesManageService) {
        this.qesManageService = qesManageService;
    }
}
