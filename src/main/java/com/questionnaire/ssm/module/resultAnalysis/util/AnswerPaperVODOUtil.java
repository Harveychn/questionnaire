package com.questionnaire.ssm.module.resultAnalysis.util;

import com.questionnaire.ssm.module.generated.pojo.AnswerPaper;
import com.questionnaire.ssm.module.generated.pojo.QuestionWithBLOBs;
import com.questionnaire.ssm.module.resultAnalysis.pojo.AnswerQuestionVO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.DisplayAnswerPaperVO;

import java.util.List;

/**
 * Created by 95884 on 2017/5/16.
 */
public class AnswerPaperVODOUtil {
    /**
     * 将数据库中答卷实体的数据包装到 展示答卷视图的实体中去
     *
     * @param
     * @return
     * @throws Exception
     */
    public static DisplayAnswerPaperVO toDisplayQuestionnaireVO(AnswerPaper answerPaper) throws Exception {
        DisplayAnswerPaperVO displayAnswerPaperVO = new DisplayAnswerPaperVO();

        displayAnswerPaperVO.setAnswerPaperId(answerPaper.getAnswerPaperId());
        displayAnswerPaperVO.setQuestionnaireId(answerPaper.getQuestionnaireId());
        displayAnswerPaperVO.setLongitude(answerPaper.getLongitude());
        displayAnswerPaperVO.setLatitude(answerPaper.getLatitude());

        return displayAnswerPaperVO;
    }

    /**
     * 将数据库中问题数据包装到 展示问卷视图的实体中
     *
     * @param
     * @return
     * @throws Exception
     */
    public static AnswerQuestionVO toAnswerQuestionVO(QuestionWithBLOBs question, String answerDetail) throws Exception {
        AnswerQuestionVO answerQuestionVO = new AnswerQuestionVO();

        answerQuestionVO.setQuestionId(question.getQuestionId());

        if (question.getQuestionContext() != null) {
            answerQuestionVO.setQuestionContext(question.getQuestionContext());
        }
        if (question.getQuestionDescription() != null) {
            answerQuestionVO.setQuestionDescription(question.getQuestionDescription());
        }
        answerQuestionVO.setAnswerDetail(answerDetail);
        /*数据库中不为空字段*/
        answerQuestionVO.setQuestionType(question.getQuestionType());
        answerQuestionVO.setMust(question.getIsMust());
        return answerQuestionVO;
    }
}
