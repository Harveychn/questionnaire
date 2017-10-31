package com.questionnaire.ssm.module.notice.pojo;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/30.
 * Description: 展示用户创建的公告信息视图实体类
 */
public class ListMyNoticeInfoVO {
    //公告ID
    private Long noticeId;
    //公告标题
    private String noticeTitle;
    //公告内容
    private String noticeContext;
    //公告创建时间
    private String noticeCreateTime;
    //公告发布日期
    private String noticeLaunchDate;
    //公告对象单位名
    private List<String> noticeUnitName;

    @Override
    public String toString() {
        return "ListMyNoticeInfoVO{" +
                "noticeId=" + noticeId +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContext='" + noticeContext + '\'' +
                ", noticeCreateTime='" + noticeCreateTime + '\'' +
                ", noticeLaunchDate='" + noticeLaunchDate + '\'' +
                ", noticeUnitName=" + noticeUnitName +
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

    public List<String> getNoticeUnitName() {
        return noticeUnitName;
    }

    public void setNoticeUnitName(List<String> noticeUnitName) {
        this.noticeUnitName = noticeUnitName;
    }
}
