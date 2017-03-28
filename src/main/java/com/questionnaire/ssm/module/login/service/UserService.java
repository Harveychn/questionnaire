package com.questionnaire.ssm.module.login.service;

import com.questionnaire.ssm.module.login.pojo.NewPasswordVO;

/**
 * Created by 郑晓辉 on 2017/3/20.
 * Description:修改变动用户表信息服务
 */
public interface UserService {
    /**
     * 更新用户密码
     *
     * @param newPasswordVO
     * @throws Exception
     */
    void updateUserPassword(NewPasswordVO newPasswordVO) throws Exception;
}
