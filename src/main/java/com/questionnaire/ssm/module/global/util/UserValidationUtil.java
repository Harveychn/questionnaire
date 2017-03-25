package com.questionnaire.ssm.module.global.util;

import com.questionnaire.ssm.module.global.enums.UserValidaEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.Subject;

import java.util.Set;


/**
 * Created by 郑晓辉 on 2017/3/25.
 * Description:
 * 1、检查当前登录用户是否session过期
 * 2、检查当前用户角色是否符合要求
 * 3、检查当前用户权限是否符合要求
 */
public class UserValidationUtil {

    /**
     * 检查用户是否登录
     *
     * @return
     * @throws Exception
     */
    public static int isValid() throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            return UserValidaEnum.VALIDA_OK.getCode();
        }
        return UserValidaEnum.NOT_LOGIN.getCode();
    }

    /**
     * 检查用户是否登录、是否具有相应角色
     *
     * @param role
     * @return
     * @throws Exception
     */
    public static int isValid(String role) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            if (subject.hasRole(role)) {
                return UserValidaEnum.VALIDA_OK.getCode();
            }
            return UserValidaEnum.NO_ROLE.getCode();
        }
        return UserValidaEnum.NOT_LOGIN.getCode();
    }

    /**
     * 检查用户是否登录、是否有权限
     *
     * @param permissions
     * @return
     */
    public static int isValid(Set<String> permissions) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            for (String permission : permissions) {
                if (!subject.isPermitted(permission)) {
                    return UserValidaEnum.NO_PERMISSION.getCode();
                }
            }
            return UserValidaEnum.VALIDA_OK.getCode();

        }
        return UserValidaEnum.NOT_LOGIN.getCode();
    }

    /**
     * 检查用户是否登录、是否具有角色、是否具有权限
     *
     * @param role
     * @param permissions
     * @return
     */
    public static int isValid(String role, Set<String> permissions) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            if (!subject.hasRole(role)) {
                return UserValidaEnum.NO_ROLE.getCode();
            }
            for (String permission : permissions) {
                if (!subject.isPermitted(permission)) {
                    return UserValidaEnum.NO_PERMISSION.getCode();
                }
            }
            return UserValidaEnum.VALIDA_OK.getCode();
        }
        return UserValidaEnum.NOT_LOGIN.getCode();
    }
}
