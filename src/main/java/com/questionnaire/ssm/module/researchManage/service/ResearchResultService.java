package com.questionnaire.ssm.module.researchManage.service;

import com.questionnaire.ssm.module.researchManage.pojo.AnswerPaperVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/5/3.
 * Description:
 */
public interface ResearchResultService {

    /**
     * 批量提交答卷数据信息
     *
     * @param userTel           用户名
     * @param answerPaperVOList 答卷信息list
     * @return 操作成功的答卷份数
     * @throws Exception
     */
    @Transactional
    int multiSubmitAnswerPaper(String userTel, List<AnswerPaperVO> answerPaperVOList) throws Exception;
}
