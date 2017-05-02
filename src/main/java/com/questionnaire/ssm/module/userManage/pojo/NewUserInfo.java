package com.questionnaire.ssm.module.userManage.pojo;

/**
 * Created by 郑晓辉 on 2017/5/2.
 * Description:
 */
public class NewUserInfo {
    private String userSex;
    private String userBirthday;
    private String userID;
    private String userMailAddress;

    @Override
    public String toString() {
        return "NewUserInfo{" +
                "userSex=" + userSex +
                ", userBirthday=" + userBirthday +
                ", userID='" + userID + '\'' +
                ", userMailAddress='" + userMailAddress + '\'' +
                '}';
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserMailAddress() {
        return userMailAddress;
    }

    public void setUserMailAddress(String userMailAddress) {
        this.userMailAddress = userMailAddress;
    }
}
