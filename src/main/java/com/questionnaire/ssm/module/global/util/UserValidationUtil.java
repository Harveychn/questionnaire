package com.questionnaire.ssm.module.global.util;

import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.exception.UserValidaException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;

import java.util.Set;

/**
 * Created by 郑晓辉 on 2017/3/25.
 * Description:
 * 1、检查当前登录用户是否session过期
 * 2、检查当前用户角色是否符合要求
 * 3、检查当前用户权限是否符合要求
 * 4、获取在线用户的用户名
 */
public class UserValidationUtil {


    /**
     * 检查用户是否登录
     *
     * @param logger 异常日志
     * @throws Exception
     */
    public static void checkUserValid(Logger logger) throws Exception {
        int result = 0;
        try {
            result = UserValidationUtil.isValid();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new UserValidaException(CodeForVOEnum.UNKNOWN_ERROR);
        }
        if (result == CodeForVOEnum.NOT_LOGIN.getCode()) {
            throw new UserValidaException(CodeForVOEnum.NOT_LOGIN);
        }
    }

    /**
     * 1.检查用户是否登录
     * 2.检查用户是否具有相应的权限
     *
     * @param needPermissions 需要的权限
     * @param logger          异常日志
     * @throws Exception
     */
    public static void checkUserValid(Set<String> needPermissions, Logger logger) throws Exception {
        int result = 0;

        try {
            result = UserValidationUtil.isValid(needPermissions);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new UserValidaException(CodeForVOEnum.UNKNOWN_ERROR);
        }
        if (result == CodeForVOEnum.NOT_LOGIN.getCode()) {
            throw new UserValidaException(CodeForVOEnum.NOT_LOGIN);
        }
        if (result == CodeForVOEnum.NO_PERMISSION.getCode()) {
            throw new UserValidaException(CodeForVOEnum.NO_PERMISSION);
        }
    }

    /**
     * 1.检查用户是否登录
     * 2.检查是否有角色
     *
     * @param needRole 需要的角色
     * @param logger   异常日志
     * @throws Exception
     */
    public static void checkUserValid(String needRole, Logger logger) throws Exception {
        int result = 0;

        try {
            result = UserValidationUtil.isValid(needRole);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new UserValidaException(CodeForVOEnum.UNKNOWN_ERROR);
        }

        if (result == CodeForVOEnum.NOT_LOGIN.getCode()) {
            throw new UserValidaException(CodeForVOEnum.NOT_LOGIN);
        }
        if (result == CodeForVOEnum.NO_ROLE.getCode()) {
            throw new UserValidaException(CodeForVOEnum.NO_ROLE);
        }
    }

    /**
     * 1.检查用户是否登录
     * 2.检查用户是否具有角色、权限操作
     *
     * @param needRole        需要的角色
     * @param needPermissions 需要的权限
     * @param logger          异常日志
     * @throws Exception
     */
    public static void checkUserValid(String needRole, Set<String> needPermissions, Logger logger)
            throws Exception {
        int result = 0;

        try {
            result = UserValidationUtil.isValid(needRole, needPermissions);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new UserValidaException(CodeForVOEnum.UNKNOWN_ERROR);
        }

        if (result == CodeForVOEnum.NOT_LOGIN.getCode()) {
            throw new UserValidaException(CodeForVOEnum.NOT_LOGIN);
        }
        if (result == CodeForVOEnum.NO_ROLE.getCode()) {
            throw new UserValidaException(CodeForVOEnum.NO_ROLE);
        }
        if (result == CodeForVOEnum.NO_PERMISSION.getCode()) {
            throw new UserValidaException(CodeForVOEnum.NO_PERMISSION);
        }
    }

    /**
     * 获取在线用户的用户名(用户账号)
     *
     * @param logger 异常日志记录
     * @return
     * @throws Exception
     */
    public static String getUserTel(Logger logger) throws Exception {
        checkUserValid(logger);
        Subject subject = SecurityUtils.getSubject();
        return (String) subject.getPrincipal();
    }

    /**
     * 检查用户是否登录
     *
     * @return
     * @throws Exception
     */
    protected static int isValid() throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            return CodeForVOEnum.VALIDA_OK.getCode();
        }
        return CodeForVOEnum.NOT_LOGIN.getCode();
    }

    /**
     * 检查用户是否登录、是否具有相应角色
     *
     * @param role
     * @return
     * @throws Exception
     */
    protected static int isValid(String role) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            if (subject.hasRole(role)) {
                return CodeForVOEnum.VALIDA_OK.getCode();
            }
            return CodeForVOEnum.NO_ROLE.getCode();
        }
        return CodeForVOEnum.NOT_LOGIN.getCode();
    }

    /**
     * 检查用户是否登录、是否有权限
     *
     * @param permissions
     * @return
     */
    protected static int isValid(Set<String> permissions) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            for (String permission : permissions) {
                if (!subject.isPermitted(permission)) {
                    return CodeForVOEnum.NO_PERMISSION.getCode();
                }
            }
            return CodeForVOEnum.VALIDA_OK.getCode();

        }
        return CodeForVOEnum.NOT_LOGIN.getCode();
    }

    /**
     * 检查用户是否登录、是否具有角色、是否具有权限
     *
     * @param role
     * @param permissions
     * @return
     */
    protected static int isValid(String role, Set<String> permissions) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            if (!subject.hasRole(role)) {
                return CodeForVOEnum.NO_ROLE.getCode();
            }
            for (String permission : permissions) {
                if (!subject.isPermitted(permission)) {
                    return CodeForVOEnum.NO_PERMISSION.getCode();
                }
            }
            return CodeForVOEnum.VALIDA_OK.getCode();
        }
        return CodeForVOEnum.NOT_LOGIN.getCode();
    }

}
