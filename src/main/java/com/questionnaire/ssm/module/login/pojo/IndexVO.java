package com.questionnaire.ssm.module.login.pojo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * 首页即登录页面登录用户账号信息
 */

public class IndexVO {

    @NotEmpty(message = "账户不予许为空")
    @Size(min = 11, max = 11, message = "请填写11位的手机号码信息")
    private String userTel;
    @NotEmpty(message = "密码不予许为空")
    @Size(min = 4, max = 30, message = "密码不能少于4个字符、大于30个字符")
    private String password;

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
}