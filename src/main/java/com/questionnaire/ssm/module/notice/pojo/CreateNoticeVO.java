package com.questionnaire.ssm.module.notice.pojo;

import java.util.Date;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/28.
 * Description: 创建公告视图类
 */
public class CreateNoticeVO {
    //公告标题
    private String noticeTitle;
    //公告标题
    private String noticeContent;
    //公告发布时间
    private Date launchDate;
    //公告对象单位
    private List<Long> unitObjectIds;

    @Override
    public String toString() {
        return "CreateNoticeVO{" +
                "noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", launchDate=" + launchDate +
                ", unitObjectIds=" + unitObjectIds +
                '}';
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

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public List<Long> getUnitObjectIds() {
        return unitObjectIds;
    }

    public void setUnitObjectIds(List<Long> unitObjectIds) {
        this.unitObjectIds = unitObjectIds;
    }
}
