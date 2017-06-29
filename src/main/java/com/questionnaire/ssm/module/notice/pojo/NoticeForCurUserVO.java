package com.questionnaire.ssm.module.notice.pojo;

/**
 * Created by 郑晓辉 on 2017/5/2.
 * Description: 展示公告公告单位人员实体类
 */
public class NoticeForCurUserVO {
    //公告编号
    private Long noticeId;
    //公告标题
    private String noticeTitle;
    //公告内容
    private String noticeContent;
    //公告发布时间
    private String noticeLaunchDate;
    //公告创建单位
    private String createUnit;

    @Override
    public String toString() {
        return "NoticeForCurUserVO{" +
                "noticeId=" + noticeId +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeLaunchDate='" + noticeLaunchDate + '\'' +
                ", createUnit='" + createUnit + '\'' +
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

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeLaunchDate() {
        return noticeLaunchDate;
    }

    public void setNoticeLaunchDate(String noticeLaunchDate) {
        this.noticeLaunchDate = noticeLaunchDate;
    }

    public String getCreateUnit() {
        return createUnit;
    }

    public void setCreateUnit(String createUnit) {
        this.createUnit = createUnit;
    }
}
