package com.questionnaire.ssm.module.login.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by 郑晓辉 on 2017/3/20.
 * Description: login模块用户工具类
 */
public class UserUtil {

    /**
     * 如果更新密码，则加密密码
     *
     * @param user
     * @return 密码加密后的user
     * @throws Exception
     */
    public static User encoded(User user) throws Exception {
        if (user.getPassword() != null) {
            user.setPassword(encodePassword(user));
        }
        return user;
    }

    /**
     * 获取设置的新密码
     *
     * @param user
     * @return
     */
    public static String encodePassword(User user) {
        String algorithmName = "md5";
        int hashIterations = 3;
        SimpleHash hash = new SimpleHash(algorithmName, user.getPassword(),
                user.getUserTel() + user.getUserRealName(), hashIterations);
        return hash.toHex();
    }
}
