package com.questionnaire.ssm.module.notice.pojo;

/**
 * Created by 郑晓辉 on 2017/4/30.
 * Description: 用户创建的公告信息
 */
public class ListMyNoticeDTO {
    //公告ID
    private Long noticeId;
    //公告标题
    private String noticeTitle;
    //公告内容
    private String noticeContext;
    //公告创建时间
    private String noticeCreateTime;
    //公告发布状态
    private String noticeLaunchDate;
    //公告对象单位名
    private String noticeUnitText;

    @Override
    public String toString() {
        return "ListMyNoticeDTO{" +
                "noticeId=" + noticeId +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContext='" + noticeContext + '\'' +
                ", noticeCreateTime='" + noticeCreateTime + '\'' +
                ", noticeLaunchDate='" + noticeLaunchDate + '\'' +
                ", noticeUnitText='" + noticeUnitText + '\'' +
                '}';
    }

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

    public String getNoticeCreateTime() {
        return noticeCreateTime;
    }

    public void setNoticeCreateTime(String noticeCreateTime) {
        this.noticeCreateTime = noticeCreateTime;
    }

    public String getNoticeLaunchDate() {
        return noticeLaunchDate;
    }

    public void setNoticeLaunchDate(String noticeLaunchDate) {
        this.noticeLaunchDate = noticeLaunchDate;
    }

    public String getNoticeUnitText() {
        return noticeUnitText;
    }

    public void setNoticeUnitText(String noticeUnitText) {
        this.noticeUnitText = noticeUnitText;
    }
}
