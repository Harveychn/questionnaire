package com.questionnaire.ssm.module.resultAnalysis.pojo;

/**
 * Created by 95884 on 2017/5/16.
 */
public class QuestionAnswerPaperVO {
    private Long questionId;//问题id
    private Long answerPaperId;//答卷id 1

    @Override
    public String toString() {
        return "QuestionAnswerPaperVO{" +
                "questionId=" + questionId +
                ", answerPaperId=" + answerPaperId +
                '}';
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerPaperId() {
        return answerPaperId;
    }

    public void setAnswerPaperId(Long answerPaperId) {
        this.answerPaperId = answerPaperId;
    }
}
