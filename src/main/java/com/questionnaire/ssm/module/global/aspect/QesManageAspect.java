package com.questionnaire.ssm.module.global.aspect;

import com.questionnaire.ssm.module.global.enums.PermissionEnum;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 郑晓辉 on 2017/3/24.
 * Description:问卷管理切面：主要业务权限控制
 */
@Aspect
@Component
public class QesManageAspect {

    /**
     * 问卷管理切入点
     */
    @Pointcut("execution(public * com.questionnaire.ssm.module.questionnaireManage.controller.QesManageController.*(..))")
    public void createQuestionnairePointcut() {

    }

    /**
     * 创建问卷卷之前，校验用户是否登录
     *
     * @throws Exception
     */
    @Before("createQuestionnairePointcut()")
    public void beforeCreateQuestionnaire() throws Exception {
        UserValidationUtil.checkUserValid(logger);
    }

    /**
     * 获取创建问卷视图切入点
     */
    @Pointcut("execution(public * com.questionnaire.ssm.module.questionnaireManage.controller.QesManageController.getCreateView(..))")
    public void getCreateQuestionnaireViewPointcut() {

    }
// 2017-7-4 去掉细粒度的权限控制
//    /**
//     * 获取创建问卷的视图之前，校验用户是否有相应的权限、角色
//     */
//    @Before("getCreateQuestionnaireViewPointcut()")
//    public void beforeGetCreateQuestionnaireView() throws Exception {
//        Set<String> needPermissions = new HashSet<>();
//        needPermissions.add(PermissionEnum.CREATE_QUESTIONNAIRE.getPermission());
//        String role = "疾控中心管理员";
//        UserValidationUtil.checkUserValid(role, needPermissions, logger);
//    }

    private static final Logger logger = LoggerFactory.getLogger(QesManageAspect.class);
}
