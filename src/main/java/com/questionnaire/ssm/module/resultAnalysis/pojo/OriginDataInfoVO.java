package com.questionnaire.ssm.module.resultAnalysis.pojo;

/**
 * Created by 95884 on 2017/5/6.
 * Description: 展示原始数据视图实体类
 */
public class OriginDataInfoVO {
    /*调查任务id*/
    private Long missionId;
    /*任务描述*/
    private String missionDescription;
    /*问卷id*/
    private Long questionnaireId;
    /*问卷标题*/
    private String questionnaireTitle;
    /*问卷副标题*/
    private String questionnaireSubtitle;
    /*问卷完成量*/
    private Long questionnaireCount;
    /*问卷最低提交量*/
    private Long minSubmitCount;

    @Override
    public String toString() {
        return "OriginDataInfoVO{" +
                "missionId=" + missionId +
                ", missionDescription='" + missionDescription + '\'' +
                ", questionnaireId=" + questionnaireId +
                ", questionnaireTitle='" + questionnaireTitle + '\'' +
                ", questionnaireSubtitle='" + questionnaireSubtitle + '\'' +
                ", questionnaireCount=" + questionnaireCount +
                ", minSubmitCount=" + minSubmitCount +
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

    public String getQuestionnaireSubtitle() {
        return questionnaireSubtitle;
    }

    public void setQuestionnaireSubtitle(String questionnaireSubtitle) {
        this.questionnaireSubtitle = questionnaireSubtitle;
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
}
