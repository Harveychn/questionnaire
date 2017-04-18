package com.questionnaire.ssm.module.login.utils;

import com.questionnaire.ssm.module.login.pojo.NewPasswordVO;

/**
 * Created by 郑晓辉 on 2017/3/26.
 * Description: 提取前台数据到数据库相应实体
 */
public class UserDBObjUtil {
    /**
     * 提取前台数据到数据库 user 表实体
     *
     * @param newPasswordVO 前台数据实体
     * @return
     * @throws Exception
     */
    public static User toUserDO(NewPasswordVO newPasswordVO) throws Exception {
        User user = new User();
//        if (newPasswordVO.getUserTel() != null) {
//            user.setUserTel(newPasswordVO.getUserTel());
//        }
//        if (newPasswordVO.getUserRealName() != null) {
//            user.setUserRealName(newPasswordVO.getUserRealName());
//        }
//        if (newPasswordVO.getPassword() != null) {
//            user.setPassword(newPasswordVO.getPassword());
//        }
        return user;
    }
}
