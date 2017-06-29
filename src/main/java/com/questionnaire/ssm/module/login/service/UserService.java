package com.questionnaire.ssm.module.login.service;

import com.questionnaire.ssm.module.generated.pojo.User;
import com.questionnaire.ssm.module.login.pojo.NewPasswordVO;

/**
 * Created by 郑晓辉 on 2017/3/20.
 * Description:修改变动用户表信息服务
 */
public interface UserService {
    /**
     * 更新用户密码
     *
     * @param userTel       账户
     * @param newPasswordVO
     * @throws Exception
     */
    void updateUserPassword(String userTel, NewPasswordVO newPasswordVO) throws Exception;

    /**
     * 重置用户密码
     *
     * @param userTel 要重置的用户密码
     * @param aNewPwd 新的密码
     * @throws Exception
     */
    void resetUserPwd(String userTel, String aNewPwd) throws Exception;

    /**
     * 用户登录系统记录
     *
     * @param userLoginRecord 用户登录系统数据
     * @throws Exception
     */
    void updateUserLoginRecord(User userLoginRecord) throws Exception;
}
