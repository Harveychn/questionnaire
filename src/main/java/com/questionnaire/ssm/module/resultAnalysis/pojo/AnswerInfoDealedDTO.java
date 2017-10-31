package com.questionnaire.ssm.module.resultAnalysis.pojo;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/5/30.
 * Description: 经过处理的答案信息
 */
public class AnswerInfoDealedDTO {
    //问题编号
    private Long questionId;
    //答项信息
    private List<String> answerOptions;

    @Override
    public String toString() {
        return "AnswerInfoDealedDTO{" +
                "questionId=" + questionId +
                ", answerOptions=" + answerOptions +
                '}';
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<String> answerOptions) {
        this.answerOptions = answerOptions;
    }
}
