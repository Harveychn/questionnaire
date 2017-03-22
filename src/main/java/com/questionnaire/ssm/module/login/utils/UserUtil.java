package com.questionnaire.ssm.module.login.utils;

import com.questionnaire.ssm.module.generated.pojo.User;

/**
 * Created by 郑晓辉 on 2017/3/20.
 * Description:
 */
public class UserUtil {

    public static User cloneUser(User oldUser) {
        User userNew = new User();
        if (null != oldUser.getUserTel() && !"".equals(oldUser.getUserTel())) {
            userNew.setUserTel(oldUser.getUserTel());
        }
        if (null != oldUser.getPassword() && !"".equals(oldUser.getPassword())) {
            userNew.setPassword(oldUser.getPassword());
        }
        if (0 != oldUser.getRoleId()) {
            userNew.setRoleId(oldUser.getRoleId());
        }
        if (0 != oldUser.getUnitId()) {
            userNew.setUnitId(oldUser.getUnitId());
        }
        if (null != oldUser.getUserRealName() && !"".equals(oldUser.getUserRealName())) {
            userNew.setUserRealName(oldUser.getUserRealName());
        }
        if (null != oldUser.getMailAddress() && !"".equals(oldUser.getMailAddress())) {
            userNew.setMailAddress(oldUser.getMailAddress());
        }
        if (null != oldUser.getUserPicUrl() && !"".equals(oldUser.getUserPicUrl())) {
            userNew.setUserPicUrl(oldUser.getUserPicUrl());
        }
        if (0 != oldUser.getUserSex()) {
            userNew.setUserSex(oldUser.getUserSex());
        }
        if (null != oldUser.getUserBirthday()) {
            userNew.setUserBirthday(oldUser.getUserBirthday());
        }
        if (null != oldUser.getId() && !"".equals(oldUser.getId())) {
            userNew.setId(oldUser.getId());
        }
        if (!oldUser.getIsValid()) {
            userNew.setIsValid(true);
        }
        return userNew;
    }
}
