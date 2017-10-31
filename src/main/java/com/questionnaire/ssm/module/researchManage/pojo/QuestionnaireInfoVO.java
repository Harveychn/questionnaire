package com.questionnaire.ssm.module.researchManage.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 郑晓辉 on 2017/4/27.
 * Description: 可发布问卷信息视图类
 */
public class QuestionnaireInfoVO {
    //问卷ID
    private Long qesId;
    //问卷标题
    private String qesTitle;
    //模板问卷
    private String isTemplate;
    //问卷创建时间
    private String createDate;

    //处理过的getter和setter方法
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createDate = sdf.format(createDate);
    }

    @Override
    public String toString() {
        return "QuestionnaireInfoVO{" +
                "qesId=" + qesId +
                ", qesTitle='" + qesTitle + '\'' +
                ", isTemplate='" + isTemplate + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public Long getQesId() {
        return qesId;
    }

    public void setQesId(Long qesId) {
        this.qesId = qesId;
    }

    public String getQesTitle() {
        return qesTitle;
    }

    public void setQesTitle(String qesTitle) {
        this.qesTitle = qesTitle;
    }

    public String getIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(String isTemplate) {
        this.isTemplate = isTemplate;
    }
}
