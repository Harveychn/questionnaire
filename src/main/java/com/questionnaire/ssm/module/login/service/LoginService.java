package com.questionnaire.ssm.module.login.service;

import com.questionnaire.ssm.module.generated.pojo.User;

import java.util.Set;

/**
 * Created by 郑晓辉 on 2017/3/13.
 */
public interface LoginService {

    User getUser(String userTel) throws Exception;

    Set<String> listUserRole(String userTel) throws Exception;

    Set<String> listUserPermission(String userTel) throws Exception;
}
