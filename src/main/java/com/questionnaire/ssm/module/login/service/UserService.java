package com.questionnaire.ssm.module.login.service;

import com.questionnaire.ssm.module.generated.pojo.User;
import com.questionnaire.ssm.module.login.pojo.ForgetPasswordVO;
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

    /**
     * 用户登录系统记录
     *
     * @param userLoginRecord 用户登录系统数据
     * @throws Exception
     */
    void updateUserLoginRecord(User userLoginRecord) throws Exception;

    /**
     * 更改忘记的密码
     * @param forgetPasswordVO
     * @throws Exception
     */
    void changeForgetPassword(ForgetPasswordVO forgetPasswordVO)throws Exception;
}
