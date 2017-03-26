package com.questionnaire.ssm.module.global.aspect;

import com.questionnaire.ssm.module.global.enums.PermissionEnum;
import com.questionnaire.ssm.module.global.enums.RequestResultEnum;
import com.questionnaire.ssm.module.global.enums.UserValidaEnum;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.questionnaireManager.exception.UserValidaException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 郑晓辉 on 2017/3/24.
 * Description:问卷管理切面：包括登录记录操作记录等等业务功能
 */
@Aspect
@Component
public class QesManagerAspect {

    private static final Logger logger = LoggerFactory.getLogger(QesManagerAspect.class);

    /**
     * 获取创建问卷视图切入点
     */
    @Pointcut("execution(public * com.questionnaire.ssm.module.questionnaireManager.controller.QesManagerController.getCreateView(..))")
    public void getCreateQuestionnaireViewPointcut() {

    }

    /**
     * 创建问卷过程切点
     */
    @Pointcut("execution(public * com.questionnaire.ssm.module.questionnaireManager.controller.QesManagerController.create(..))")
    public void createQuestionnaireProcess(){

    }
    /**
     * 获取创建问卷的视图之前，校验用户是否有相应的权限、角色
     */
    @Before("getCreateQuestionnaireViewPointcut()")
    public void beforeCreateQuestionnaire() throws Exception {
        Set<String> needPermissions = new HashSet<>();
        needPermissions.add(PermissionEnum.CREATE_QUESTIONNAIRE.getPermission());
        String role = "疾控中心管理员";
        UserValidationUtil.checkUserValid(role,needPermissions,logger);
//        int result = 0;
//
//        try {
//            Set<String> needPermissions = new HashSet<>();
//            needPermissions.add(PermissionEnum.CREATE_QUESTIONNAIRE.getPermission());
//            result = UserValidationUtil.isValid("疾控中心管理员", needPermissions);
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            throw new UserValidaException(UserValidaEnum.UNKNOWN_ERROR);
//        }
//
//        if (result == UserValidaEnum.NOT_LOGIN.getCode()) {
//            throw new UserValidaException(UserValidaEnum.NOT_LOGIN);
//        }
//        if (result == UserValidaEnum.NO_ROLE.getCode()) {
//            throw new UserValidaException(UserValidaEnum.NO_ROLE);
//        }
//        if (result == UserValidaEnum.NO_PERMISSION.getCode()) {
//            throw new UserValidaException(UserValidaEnum.NO_PERMISSION);
//        }
    }

    @Around("createQuestionnaireProcess()")
    public void aroundCreateQuestionnaireProcess(ProceedingJoinPoint joinPoint){

    }
}
