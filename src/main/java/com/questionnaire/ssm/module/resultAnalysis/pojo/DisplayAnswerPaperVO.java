package com.questionnaire.ssm.module.resultAnalysis.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 95884 on 2017/5/16.
 */
public class DisplayAnswerPaperVO {
    /*答卷信息*/
    private Long answerPaperId;//答卷id
    private Long questionnaireId;//问卷id
    private String questionnaireTitle;//问卷标题
    private String questionnaireSubtitle;//问卷副标题
    private String questionnaireDescription;//问卷描述
    private BigDecimal longitude;//经度
    private BigDecimal latitude;//纬度
    /*答卷题目*/
    private List<AnswerQuestionVO> answerQuestions;

    @Override
    public String toString() {
        return "DisplayAnswerPaperVO{" +
                "answerPaperId=" + answerPaperId +
                ", questionnaireId=" + questionnaireId +
                ", questionnaireTitle='" + questionnaireTitle + '\'' +
                ", questionnaireSubtitle='" + questionnaireSubtitle + '\'' +
                ", questionnaireDescription='" + questionnaireDescription + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", answerQuestions=" + answerQuestions +
                '}';
    }

    public Long getAnswerPaperId() {
        return answerPaperId;
    }

    public void setAnswerPaperId(Long answerPaperId) {
        this.answerPaperId = answerPaperId;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestionnaireTitle() {
        return questionnaireTitle;
    }

    public void setQuestionnaireTitle(String questionnaireTitle) {
        this.questionnaireTitle = questionnaireTitle;
    }

    public String getQuestionnaireSubtitle() {
        return questionnaireSubtitle;
    }

    public void setQuestionnaireSubtitle(String questionnaireSubtitle) {
        this.questionnaireSubtitle = questionnaireSubtitle;
    }

    public String getQuestionnaireDescription() {
        return questionnaireDescription;
    }

    public void setQuestionnaireDescription(String questionnaireDescription) {
        this.questionnaireDescription = questionnaireDescription;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public List<AnswerQuestionVO> getAnswerQuestions() {
        return answerQuestions;
    }

    public void setAnswerQuestions(List<AnswerQuestionVO> answerQuestions) {
        this.answerQuestions = answerQuestions;
    }
}
