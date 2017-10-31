package com.questionnaire.ssm.module.questionnaireManage.service;

import com.questionnaire.ssm.module.questionnaireManage.pojo.CreateQesVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 郑晓辉 on 2017/6/11.
 * Description:
 */
public interface ContinueEditService {

    /**
     * 继续编辑问卷
     *
     * @param createQesVO
     * @return
     * @throws Exception
     */
    @Transactional
    boolean continueEdit(CreateQesVO createQesVO) throws Exception;

}
