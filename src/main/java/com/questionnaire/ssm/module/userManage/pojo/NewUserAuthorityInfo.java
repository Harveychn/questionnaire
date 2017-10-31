package com.questionnaire.ssm.module.userManage.pojo;

/**
 * Created by 郑晓辉 on 2017/5/25.
 * Description: 用户新的权限信息
 */
public class NewUserAuthorityInfo {
    //用户账户
    private String userAccount;
    //用户角色
    private Long userRoleId;
    //用户真实姓名
    private String userRealName;

    @Override
    public String toString() {
        return "NewUserAuthorityInfo{" +
                "userAccount='" + userAccount + '\'' +
                ", userRoleId=" + userRoleId +
                ", userRealName='" + userRealName + '\'' +
                '}';
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }
}
