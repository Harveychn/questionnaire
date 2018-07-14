package com.questionnaire.ssm.module.resultAnalysis.pojo;

/**
 * Created by 95884 on 2017/5/16.
 */
public class AnswerQuestionVO {
    /**
     * 题目信息
     */
    private Long questionId;//问题id
    private String questionContext;//题目
    private String questionType;//题目类型
    private String questionDescription;//题目描述
    private Boolean isMust;//是否必做题
    private String answerDetail;//答案

    @Override
    public String toString() {
        return "AnswerQuestionVO{" +
                "questionId=" + questionId +
                ", questionContext='" + questionContext + '\'' +
                ", questionType='" + questionType + '\'' +
                ", questionDescription='" + questionDescription + '\'' +
                ", isMust=" + isMust +
                ", answerDetail='" + answerDetail + '\'' +
                '}';
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContext() {
        return questionContext;
    }

    public void setQuestionContext(String questionContext) {
        this.questionContext = questionContext;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public Boolean getMust() {
        return isMust;
    }

    public void setMust(Boolean must) {
        isMust = must;
    }

    public String getAnswerDetail() {
        return answerDetail;
    }

    public void setAnswerDetail(String answerDetail) {
        this.answerDetail = answerDetail;
    }
}
