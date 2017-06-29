package com.questionnaire.ssm.module.researchManage.pojo;

/**
 * Created by 95884 on 2017/5/23.
 */
public class ListMissionVO {
    /*调查任务id*/
    private Long missionId;
    /*任务描述*/
    private String missionDescription;
    /*问卷id*/
    private Long questionnaireId;
    /*问卷标题*/
    private String questionnaireTitle;
    /*问卷完成量*/
    private Long questionnaireCount;
    /*问卷最低提交量*/
    private Long minSubmitCount;
    /*任务开始时间*/
    private String missionStartDate;
    /*任务结束时间*/
    private String missionFinalDate;
    /*任务完成状态*/
    private String missionStatus;

    @Override
    public String toString() {
        return "ListMissionVO{" +
                "missionId=" + missionId +
                ", missiondescription='" + missionDescription + '\'' +
                ", questionnaireId=" + questionnaireId +
                ", questionnaireTitle='" + questionnaireTitle + '\'' +
                ", questionnaireCount=" + questionnaireCount +
                ", minSubmitCount=" + minSubmitCount +
                ", missionStartDate='" + missionStartDate + '\'' +
                ", missionFinalDate='" + missionFinalDate + '\'' +
                ", missionStatus='" + missionStatus + '\'' +
                '}';
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public String getMissionDescription() {
        return missionDescription;
    }

    public void setMissionDescription(String missionDescription) {
        this.missionDescription = missionDescription;
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

    public Long getQuestionnaireCount() {
        return questionnaireCount;
    }

    public void setQuestionnaireCount(Long questionnaireCount) {
        this.questionnaireCount = questionnaireCount;
    }

    public Long getMinSubmitCount() {
        return minSubmitCount;
    }

    public void setMinSubmitCount(Long minSubmitCount) {
        this.minSubmitCount = minSubmitCount;
    }

    public String getMissionStartDate() {
        return missionStartDate;
    }

    public void setMissionStartDate(String missionStartDate) {
        this.missionStartDate = missionStartDate;
    }

    public String getMissionFinalDate() {
        return missionFinalDate;
    }

    public void setMissionFinalDate(String missionFinalDate) {
        this.missionFinalDate = missionFinalDate;
    }

    public String getMissionStatus() {
        return missionStatus;
    }

    public void setMissionStatus(String missionStatus) {
        this.missionStatus = missionStatus;
    }
}
