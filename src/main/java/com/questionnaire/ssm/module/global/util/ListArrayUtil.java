package com.questionnaire.ssm.module.global.util;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/7.
 * Description: List与Array相互转换工具类
 */
public class ListArrayUtil {

    public static Long[] list2Array(List<Long> list) throws Exception {
        Long[] longValues = new Long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            longValues[i] = list.get(i);
        }
        return longValues;
    }
}
