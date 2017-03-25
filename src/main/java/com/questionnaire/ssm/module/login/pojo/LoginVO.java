package com.questionnaire.ssm.module.login.pojo;

/**
 * Created by 郑晓辉 on 2017/3/24.
 * Description:用户登录界面实体
 */
public class LoginVO {

    /*用户账号*/
    private String userTel;
    /*用户密码*/
    private String password;
    /*是否记住我*/
    private Boolean rememberMe;

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
