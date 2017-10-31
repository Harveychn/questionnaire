package com.questionnaire.ssm.module.userManage.pojo;

/**
 * Created by 郑晓辉 on 2017/4/26.
 * Description: 调查员信息vo实体类
 */
public class SurveyorInfoVO {
    //用户名
    private String userTel;
    //用户真实姓名
    private String userRealName;
    //用户所属单位
    private String userUnit;
    //用户头像服务器地址
    private String picAddress;

    @Override
    public String toString() {
        return "SurveyorInfoVO{" +
                "userTel='" + userTel + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userUnit='" + userUnit + '\'' +
                ", picAddress='" + picAddress + '\'' +
                '}';
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserUnit() {
        return userUnit;
    }

    public void setUserUnit(String userUnit) {
        this.userUnit = userUnit;
    }

    public String getPicAddress() {
        return picAddress;
    }

    public void setPicAddress(String picAddress) {
        this.picAddress = picAddress;
    }
}
