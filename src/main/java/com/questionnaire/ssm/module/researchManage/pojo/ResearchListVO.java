package com.questionnaire.ssm.module.researchManage.pojo;

import java.util.Date;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/23.
 * Description: 全部调查信息列表
 */
public class ResearchListVO {
    //调查任务id
    private Long researchId;
    //调查发布日期
    private Date researchLaunchDate;
    //调查截止日期
    private Date researchDeadline;
    //当前调查的问卷信息
    private List<ResearchQesPaperVO> researchQesPaperVOList;

    @Override
    public String toString() {
        return "ResearchListVO{" +
                "researchId=" + researchId +
                ", researchLaunchDate=" + researchLaunchDate +
                ", researchDeadline=" + researchDeadline +
                ", researchQesPaperVOList=" + researchQesPaperVOList +
                '}';
    }

    public Long getResearchId() {
        return researchId;
    }

    public void setResearchId(Long researchId) {
        this.researchId = researchId;
    }

    public Date getResearchLaunchDate() {
        return researchLaunchDate;
    }

    public void setResearchLaunchDate(Date researchLaunchDate) {
        this.researchLaunchDate = researchLaunchDate;
    }

    public Date getResearchDeadline() {
        return researchDeadline;
    }

    public void setResearchDeadline(Date researchDeadline) {
        this.researchDeadline = researchDeadline;
    }

    public List<ResearchQesPaperVO> getResearchQesPaperVOList() {
        return researchQesPaperVOList;
    }

    public void setResearchQesPaperVOList(List<ResearchQesPaperVO> researchQesPaperVOList) {
        this.researchQesPaperVOList = researchQesPaperVOList;
    }
}
