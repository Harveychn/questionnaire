package com.questionnaire.ssm.module.global.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by 郑晓辉 on 2017/3/24.
 * Description:问卷管理切面：包括登录记录操作记录等等业务功能
 */
@Aspect
@Component
public class QuestionnaireManagerAspect {

    private static final Logger logger = LoggerFactory.getLogger(QuestionnaireManagerAspect.class);

    @Pointcut("execution(public * com.questionnaire.ssm.module.questionnaireManager.controller.QuestionnaireManagerController.Create(..))")
    public void createQuestionnaire() {

    }
}
