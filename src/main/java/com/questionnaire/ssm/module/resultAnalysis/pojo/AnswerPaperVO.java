package com.questionnaire.ssm.module.resultAnalysis.pojo;

/**
 * Created by 95884 on 2017/5/7.
 */
public class AnswerPaperVO {
    /*答卷id*/
    private Long answerPaperId;
    /*交卷人id*/
    private String submitUserTel;
    /*最后回答时间*/
    private String fillAnswerTime;
    /*提交答卷时间*/
    private String submitTime;

    @Override
    public String toString() {
        return "AnswerPaperVO{" +
                "answerPaperId=" + answerPaperId +
                ", submitUserTel='" + submitUserTel + '\'' +
                ", fillAnswerTime='" + fillAnswerTime + '\'' +
                ", submitTime='" + submitTime + '\'' +
                '}';
    }

    public Long getAnswerPaperId() {
        return answerPaperId;
    }

    public void setAnswerPaperId(Long answerPaperId) {
        this.answerPaperId = answerPaperId;
    }

    public String getSubmitUserTel() {
        return submitUserTel;
    }

    public void setSubmitUserTel(String submitUserTel) {
        this.submitUserTel = submitUserTel;
    }

    public String getFillAnswerTime() {
        return fillAnswerTime;
    }

    public void setFillAnswerTime(String fillAnswerTime) {
        this.fillAnswerTime = fillAnswerTime;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }
}
