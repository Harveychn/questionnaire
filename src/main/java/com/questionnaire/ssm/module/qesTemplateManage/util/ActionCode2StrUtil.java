package com.questionnaire.ssm.module.qesTemplateManage.util;

import com.questionnaire.ssm.module.global.enums.UserActionEnum;

/**
 * Created by 郑晓辉 on 2017/4/8.
 * Description: 将数据库中action代码转换为视图界面中用户可以理解的字符串
 */
public class ActionCode2StrUtil {

    public static String toVOString(String codeFromDB) throws Exception {
        if (codeFromDB.equals(String.valueOf(UserActionEnum.CREATE_QUESTIONNAIRE.getCode()))) {
            return UserActionEnum.CREATE_QUESTIONNAIRE.getAction();
        }
        if (codeFromDB.equals(String.valueOf(UserActionEnum.CREATE_QUESTIONNAIRE_AS_TEMPLATE.getCode()))) {
            return UserActionEnum.CREATE_QUESTIONNAIRE_AS_TEMPLATE.getAction();
        }
        if (codeFromDB.equals(String.valueOf(UserActionEnum.DELETE_TEMPORARY_QUESTIONNAIRE.getCode()))) {
            return UserActionEnum.DELETE_TEMPORARY_QUESTIONNAIRE.getAction();
        }
        if (codeFromDB.equals(String.valueOf(UserActionEnum.DELETE_FOREVER_QUESTIONNAIRE.getCode()))) {
            return UserActionEnum.DELETE_FOREVER_QUESTIONNAIRE.getAction();
        }
        if (codeFromDB.equals(String.valueOf(UserActionEnum.ADD_2_PRIVATE_TEMPLATE.getCode()))) {
            return UserActionEnum.ADD_2_PRIVATE_TEMPLATE.getAction();
        }
        if (codeFromDB.equals(String.valueOf(UserActionEnum.SHARE_QUESTIONNAIRE_2_PUBLIC_TEMPLATE.getCode()))) {
            return UserActionEnum.SHARE_QUESTIONNAIRE_2_PUBLIC_TEMPLATE.getAction();
        }
        if (codeFromDB.equals(String.valueOf(UserActionEnum.ADD_2_PUBLIC_TEMPLATE.getCode()))) {
            return UserActionEnum.ADD_2_PUBLIC_TEMPLATE.getAction();
        }
        if (codeFromDB.equals(String.valueOf(UserActionEnum.COPY_FROM_PUBLIC_TEMPLATE.getCode()))) {
            return UserActionEnum.COPY_FROM_PUBLIC_TEMPLATE.getAction();
        }if (codeFromDB.equals(String.valueOf(UserActionEnum.ADD_PUBLIC_TEMPLATE_2_MY_TEMPLATE_LIBRARY.getCode()))) {
            return UserActionEnum.ADD_PUBLIC_TEMPLATE_2_MY_TEMPLATE_LIBRARY.getAction();
        }
        return "--";
    }
}
