package com.questionnaire.ssm.module.login.utils;

/**
 * Created by 郑晓辉 on 2017/6/19.
 * Description: 生成验证码工具
 */
public class VCodeUtils {
    /**
     * 获取随机生成的 nBytes 位数字字符
     *
     * @param nBytes 随机字符位数
     * @return
     */
    public static synchronized String gentVcode(int nBytes) {
        int randomCode = (int) ((Math.random() * 9 + 1) * Math.pow(10, nBytes - 1));
        return String.valueOf(randomCode);
    }
}
