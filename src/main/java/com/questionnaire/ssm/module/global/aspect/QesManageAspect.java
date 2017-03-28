package com.questionnaire.ssm.module.global.aspect;

import com.questionnaire.ssm.module.generated.mapper.RecordOperateQuestionnaireMapper;
import com.questionnaire.ssm.module.generated.pojo.RecordOperateQuestionnaire;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.enums.OperateDBEnum;
import com.questionnaire.ssm.module.global.enums.PermissionEnum;
import com.questionnaire.ssm.module.global.enums.UserActionEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 郑晓辉 on 2017/3/24.
 * Description:问卷管理切面：包括登录记录操作记录等等业务功能
 */
@Aspect
@Component
public class QesManageAspect {

    private static final Logger logger = LoggerFactory.getLogger(QesManageAspect.class);

    /**
     * 获取创建问卷视图切入点
     */
    @Pointcut("execution(public * com.questionnaire.ssm.module.questionnaireManage.controller.QesManageController.getCreateView(..))")
    public void getCreateQuestionnaireViewPointcut() {

    }

    /**
     * 获取创建问卷的视图之前，校验用户是否有相应的权限、角色
     */
    @Before("getCreateQuestionnaireViewPointcut()")
    public void beforeCreateQuestionnaire() throws Exception {
        Set<String> needPermissions = new HashSet<>();
        needPermissions.add(PermissionEnum.CREATE_QUESTIONNAIRE.getPermission());
        String role = "疾控中心管理员";
        UserValidationUtil.checkUserValid(role, needPermissions, logger);
    }
}
