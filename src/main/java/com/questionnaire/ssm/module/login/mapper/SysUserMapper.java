package com.questionnaire.ssm.module.login.mapper;

import java.util.Set;

public interface SysUserMapper {

    Set<String> listUserRole(String userTel) throws Exception;

    Set<String> listUserPermission(String userTel) throws Exception;

}