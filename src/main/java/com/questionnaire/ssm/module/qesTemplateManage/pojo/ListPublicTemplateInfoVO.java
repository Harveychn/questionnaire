package com.questionnaire.ssm.module.qesTemplateManage.pojo;

import java.util.Date;

/**
 * Created by 郑晓辉 on 2017/4/2.
 * Description: 展示公共模板信息的视图实体类
 */
public class ListPublicTemplateInfoVO {
    /*问卷ID*/
    private Long questionnaireId;
    /*问卷标题*/
    private String questionnaireTitle;
    /*问卷副标题*/
    private String questionnaireSubtitle;
    /*问卷描述*/
    private String questionnaireDescription;
    /*是否已经完成*/
    private boolean isDone;
    /*分享用户真实姓名*/
    private String sharedUser;
    /*分享日期*/
    private Date shareDate;

    @Override
    public String toString() {
        return "ListPublicTemplateInfoVO{" +
                "questionnaireId=" + questionnaireId +
                ", questionnaireTitle='" + questionnaireTitle + '\'' +
                ", questionnaireSubtitle='" + questionnaireSubtitle + '\'' +
                ", questionnaireDescription='" + questionnaireDescription + '\'' +
                ", isDone=" + isDone +
                ", sharedUser='" + sharedUser + '\'' +
                ", shareDate=" + shareDate +
                '}';
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

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getQuestionnaireDescription() {
        return questionnaireDescription;
    }

    public void setQuestionnaireDescription(String questionnaireDescription) {
        this.questionnaireDescription = questionnaireDescription;
    }

    public String getSharedUser() {
        return sharedUser;
    }

    public void setSharedUser(String sharedUser) {
        this.sharedUser = sharedUser;
    }

    public Date getShareDate() {
        return shareDate;
    }

    public void setShareDate(Date shareDate) {
        this.shareDate = shareDate;
    }
}
