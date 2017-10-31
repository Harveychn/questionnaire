package com.questionnaire.ssm.module.researchManage.pojo;

import java.util.Date;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/24.
 * Description: 创建调查任务视图实体
 */
public class CreateResearchMissionVO {
    //调查任务描述
    private String missionDescription;
    //任务开始日期
    private Date missionStartDate;
    //任务截止日期
    private Date missionDeadLine;
    //对象单位ID
    private List<Long> missionObjectUnitId;
    //调查任务问卷信息
    private List<MissionQesPaperVO> missionQuestionnaireInfo;

    @Override
    public String toString() {
        return "CreateResearchMissionVO{" +
                "missionDescription='" + missionDescription + '\'' +
                ", missionStartDate=" + missionStartDate +
                ", missionDeadLine=" + missionDeadLine +
                ", missionObjectUnitId=" + missionObjectUnitId +
                ", missionQuestionnaireInfo=" + missionQuestionnaireInfo +
                '}';
    }

    public String getMissionDescription() {
        return missionDescription;
    }

    public void setMissionDescription(String missionDescription) {
        this.missionDescription = missionDescription;
    }

    public Date getMissionStartDate() {
        return missionStartDate;
    }

    public void setMissionStartDate(Date missionStartDate) {
        this.missionStartDate = missionStartDate;
    }

    public Date getMissionDeadLine() {
        return missionDeadLine;
    }

    public void setMissionDeadLine(Date missionDeadLine) {
        this.missionDeadLine = missionDeadLine;
    }

    public List<Long> getMissionObjectUnitId() {
        return missionObjectUnitId;
    }

    public void setMissionObjectUnitId(List<Long> missionObjectUnitId) {
        this.missionObjectUnitId = missionObjectUnitId;
    }

    public List<MissionQesPaperVO> getMissionQuestionnaireInfo() {
        return missionQuestionnaireInfo;
    }

    public void setMissionQuestionnaireInfo(List<MissionQesPaperVO> missionQuestionnaireInfo) {
        this.missionQuestionnaireInfo = missionQuestionnaireInfo;
    }
}
