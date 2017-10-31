package com.questionnaire.ssm.module.global.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * creator: xiaohui zheng
 * time: 2017/10/29  16:48
 * function: 检查图片格式工具类
 */
public class CheckPicUtil {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取保存今天图片的文件夹
     *
     * @return
     */
    public static String getTodayFolder() {
        return dateFormat.format(new Date());
    }

    /**
     * 获取文件类型结尾
     *
     * @param originName 原始文件名
     * @return
     */
    public static String getPicType(String originName) {
        String[] result = originName.split("\\.");
        return "." + result[1];
    }

    /**
     * 检查是否图片格式的文件
     *
     * @param originName  文件原始名，带文件类型后缀
     * @param contentType 请求中的文件格式
     * @return
     */
    public static boolean isPictureType(String originName, String contentType) {
        return contentType.contains("image/") && (originName.contains(".jpg") || originName.contains(".png") || originName.contains(".jpeg"));
    }
}
