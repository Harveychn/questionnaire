package com.questionnaire.ssm.module.notice.pojo;

import java.util.Date;

public class Notice {

    private Long noticeId;

    private String noticeTitle;

    private String noticeContext;

    private String userTel;

    private Date noticeCreateTime;

    private Boolean isDone;

    private Date noticeLaunchTime;

    private String noticeUnitText;

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContext() {
        return noticeContext;
    }

    public void setNoticeContext(String noticeContext) {
        this.noticeContext = noticeContext;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public Date getNoticeCreateTime() {
        return noticeCreateTime;
    }

    public void setNoticeCreateTime(Date noticeCreateTime) {
        this.noticeCreateTime = noticeCreateTime;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public Date getNoticeLaunchTime() {
        return noticeLaunchTime;
    }

    public void setNoticeLaunchTime(Date noticeLaunchTime) {
        this.noticeLaunchTime = noticeLaunchTime;
    }

    public String getNoticeUnitText() {
        return noticeUnitText;
    }

    public void setNoticeUnitText(String noticeUnitText) {
        this.noticeUnitText = noticeUnitText;
    }

}