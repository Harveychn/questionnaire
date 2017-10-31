package com.questionnaire.ssm.module.login.pojo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by 郑晓辉 on 2017/3/24.
 * Description:用户登录界面实体
 */
public class LoginVO {

    /*用户账号*/
    @NotEmpty(message = "账号不得为空!")
    @Size(min = 11, max = 11, message = "请输入正确的11位手机号码！")
    private String userTel;
    /*用户密码*/
    @NotEmpty(message = "密码不能为空!")
    @Size(min = 6,max = 30,message = "密码最少6位、最多30位!")
    private String password;
    /*是否记住我*/
    private Boolean rememberMe;

    @Override
    public String toString() {
        return "LoginVO{" +
                "userTel='" + userTel + '\'' +
                ", password='" + password + '\'' +
                ", rememberMe=" + rememberMe +
                '}';
    }

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
