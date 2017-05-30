package com.questionnaire.ssm.module.researchManage.pojo;

/**
 * Created by 95884 on 2017/5/30.
 */
public class MissionVO {
    /*任务结束时间*/
    private String missionFinalDate;

    @Override
    public String toString() {
        return "MissionVO{" +
                "missionFinalDate='" + missionFinalDate + '\'' +
                '}';
    }

    public String getMissionFinalDate() {
        return missionFinalDate;
    }

    public void setMissionFinalDate(String missionFinalDate) {
        this.missionFinalDate = missionFinalDate;
    }
}
