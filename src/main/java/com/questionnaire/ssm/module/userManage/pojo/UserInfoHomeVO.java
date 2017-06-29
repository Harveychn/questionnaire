package com.questionnaire.ssm.module.userManage.pojo;

import java.util.Date;

/**
 * Created by 郑晓辉 on 2017/6/1.
 * Description: 用户信息首页信息实体
 */
public class UserInfoHomeVO {
    //用户账户
    private String userAccount;
    //用户真实姓名
    private String userRealName;
    //用户角色
    private String userRole;
    //用户工作单位
    private String workingUnit;
    //用户上次登录日期
    private Date lastLoginDate;
    //用户上次登录ip
    private String lastLoginIp;

    @Override
    public String toString() {
        return "UserInfoHomeVO{" +
                "userAccount='" + userAccount + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userRole='" + userRole + '\'' +
                ", workingUnit='" + workingUnit + '\'' +
                ", lastLoginDate=" + lastLoginDate +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                '}';
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
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

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }
}
