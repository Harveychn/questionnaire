package com.questionnaire.ssm.module.login.pojo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by 95884 on 2017/6/7.
 */
public class ForgetPasswordVO {
    /*用户账号(手机号)*/
    @NotEmpty(message = "账号不为空")
    @Size(min = 11, max = 11, message = "请输入正确的11位手机号码！")
    private String userTel;
    /*验证码*/
    @NotEmpty(message = "验证码不为空")
    @Size(min = 6,max = 6,message = "请输入正确的6位验证码！")
    private String verificationCode;
    /*新密码*/
    @NotEmpty(message = "新密码不为空")
    @Size(min = 6, max = 30, message = "新密码长度不少于6、不大于30个字符")
    private String newPassword;

    @Override
    public String toString() {
        return "ForgetPasswordVO{" +
                "userTel='" + userTel + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
