package com.questionnaire.ssm.module.notice.pojo;

/**
 * Created by 郑晓辉 on 2017/5/2.
 * Description: 展示公告公告单位人员数据库查询信息实体
 */
public class NoticeForCurUserDTO {
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
    //公告对象单位
    private String objectUnitText;

    @Override
    public String toString() {
        return "NoticeForCurUserDTO{" +
                "noticeId=" + noticeId +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeLaunchDate='" + noticeLaunchDate + '\'' +
                ", createUnit='" + createUnit + '\'' +
                ", objectUnitText='" + objectUnitText + '\'' +
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

    public String getObjectUnitText() {
        return objectUnitText;
    }

    public void setObjectUnitText(String objectUnitText) {
        this.objectUnitText = objectUnitText;
    }
}
