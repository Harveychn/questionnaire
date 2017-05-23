package com.questionnaire.ssm.module.researchManage.pojo;

/**
 * Created by 95884 on 2017/5/23.
 */
public class ListMissionVO {
    /*调查任务id*/
    private Long missionId;
    /*问卷id*/
    private Long questionnaireId;
    /*问卷标题*/
    private String questionnaireTitle;
    /*问卷最低提交量*/
    private Long minSubmitCount;
    /*任务开始时间*/
    private String missionStartDate;
    /*任务结束时间*/
    private String missionFinalDate;
    /*任务创建者 */
    private String createUser;

    @Override
    public String toString() {
        return "ListMissionVO{" +
                "missionId=" + missionId +
                ", questionnaireId=" + questionnaireId +
                ", questionnaireTitle='" + questionnaireTitle + '\'' +
                ", minSubmitCount=" + minSubmitCount +
                ", missionStartDate='" + missionStartDate + '\'' +
                ", missionFinalDate='" + missionFinalDate + '\'' +
                ", createUser='" + createUser + '\'' +
                '}';
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
