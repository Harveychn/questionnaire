package com.questionnaire.ssm.module.userManage.pojo;

import java.util.Date;

/**
 * Created by 郑晓辉 on 2017/5/1.
 * Description: 用户信息视图实体类
 */
public class MyInfoVO {
    //用户真实姓名
    private String userRealName;
    //用户角色
    private String userRole;
    //用户单位
    private String workingUnit;
    //用户手机号
    private String userTel;
    //用户性别
    private int userSex;
    //用户身份ID
    private Date userBirthday;
    //用户出生日期
    private String userID;
    //用户邮箱
    private String mailAddress;

    @Override
    public String toString() {
        return "MyInfoVO{" +
                "userRealName='" + userRealName + '\'' +
                ", userRole='" + userRole + '\'' +
                ", workingUnit='" + workingUnit + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userSex=" + userSex +
                ", userBirthday=" + userBirthday +
                ", userID='" + userID + '\'' +
                ", mailAddress='" + mailAddress + '\'' +
                '}';
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getWorkingUnit() {
        return workingUnit;
    }

    public void setWorkingUnit(String workingUnit) {
        this.workingUnit = workingUnit;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}
