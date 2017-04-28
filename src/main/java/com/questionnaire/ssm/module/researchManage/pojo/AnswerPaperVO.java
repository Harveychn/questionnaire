package com.questionnaire.ssm.module.researchManage.pojo;

import java.util.Date;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/23.
 * Description: 答卷信息
 */
public class AnswerPaperVO {
    //调查任务ID（标识哪一次调查）
    private Long researchId;
    //问卷ID（标识当前调查任务的哪一张问卷）
    private Long questionnaireId;
    //填写时经度
    private Double longitude;
    //填写时纬度
    private Double latitude;
    //开始答题时间
    private Date fillAnswerTime;
    //答案详细信息
    private List<AnswerDetailVO> answerDetailVOList;

    @Override
    public String toString() {
        return "AnswerPaperVO{" +
                "researchId=" + researchId +
                ", questionnaireId=" + questionnaireId +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", fillAnswerTime=" + fillAnswerTime +
                ", answerDetailVOList=" + answerDetailVOList +
                '}';
    }

    public Long getResearchId() {
        return researchId;
    }

    public void setResearchId(Long researchId) {
        this.researchId = researchId;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Date getFillAnswerTime() {
        return fillAnswerTime;
    }

    public void setFillAnswerTime(Date fillAnswerTime) {
        this.fillAnswerTime = fillAnswerTime;
    }

    public List<AnswerDetailVO> getAnswerDetailVOList() {
        return answerDetailVOList;
    }

    public void setAnswerDetailVOList(List<AnswerDetailVO> answerDetailVOList) {
        this.answerDetailVOList = answerDetailVOList;
    }
}
