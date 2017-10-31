package com.questionnaire.ssm.module.login.pojo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by 郑晓辉 on 2017/3/26.
 * Description: 更改密码界面实体
 */
public class NewPasswordVO {

    @NotEmpty(message = "旧密码不为空")
    @Size(min = 6, max = 30, message = "旧密码密码长度不少于6、不大于30个字符")
    private String oldPassword;

    @NotEmpty(message = "新密码不为空")
    @Size(min = 6, max = 30, message = "新密码长度不少于6、不大于30个字符")
    private String newPassword;

    @NotEmpty(message = "验证码不为空")


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
