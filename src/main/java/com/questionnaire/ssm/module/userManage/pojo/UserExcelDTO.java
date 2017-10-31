package com.questionnaire.ssm.module.userManage.pojo;

/**
 * Created by 郑晓辉 on 2017/4/21.
 * Description: 用户上传模板数据传输类
 */
public class UserExcelDTO {
    //用户手机号码(账户)
    private String userTel;
    //用户角色
    private String userRole;
    //用户初始密码
    private String userPassword;
    //用户姓名
    private String userRealName;
    //是否激活账户
    private String isValid;
    //用户所在单位名
    private String unitName;
    //用户所在单位编号
    private String unitCode;


    @Override
    public String toString() {
        return "UserExcelDTO{" +
                "userTel='" + userTel + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", isValid='" + isValid + '\'' +
                ", unitName='" + unitName + '\'' +
                ", unitCode='" + unitCode + '\'' +
                '}';
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
}
