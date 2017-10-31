package com.questionnaire.ssm.module.researchManage.pojo;

import java.util.Date;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/5/24.
 * Description: 调查任务信息 视图实体
 */
public class MissionInfoVO {
    //任务id
    private Long missionId;
    //创建人
    private String creator;
    //创建人单位
    private String creatorUnit;
    //开始时间
    private Date beginDate;
    //截止时间
    private Date endDate;
    //调查任务描述
    private String description;
    //执行单位id字符串
    private String unitIdStr;
    //执行单位信息
    private List<String> executeUnitList;
    //任务问卷信息
    private List<MissionPaperDTO> missionPaperDTOList;

    @Override
    public String toString() {
        return "MissionInfoVO{" +
                "missionId=" + missionId +
                ", creator='" + creator + '\'' +
                ", creatorUnit='" + creatorUnit + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", unitIdStr='" + unitIdStr + '\'' +
                ", executeUnitList=" + executeUnitList +
                ", missionPaperDTOList=" + missionPaperDTOList +
                '}';
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorUnit() {
        return creatorUnit;
    }

    public void setCreatorUnit(String creatorUnit) {
        this.creatorUnit = creatorUnit;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnitIdStr() {
        return unitIdStr;
    }

    public void setUnitIdStr(String unitIdStr) {
        this.unitIdStr = unitIdStr;
    }

    public List<String> getExecuteUnitList() {
        return executeUnitList;
    }

    public void setExecuteUnitList(List<String> executeUnitList) {
        this.executeUnitList = executeUnitList;
    }

    public List<MissionPaperDTO> getMissionPaperDTOList() {
        return missionPaperDTOList;
    }

    public void setMissionPaperDTOList(List<MissionPaperDTO> missionPaperDTOList) {
        this.missionPaperDTOList = missionPaperDTOList;
    }
}
