package com.questionnaire.ssm.module.global.aspect;

import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by 郑晓辉 on 2017/4/18.
 * Description: 问卷模板管理切面
 */
@Aspect
@Component
public class QesTemplateManageAspect {

    @Pointcut("execution(public * com.questionnaire.ssm.module.qesTemplateManage.controller.QesTemplateRestController.*(..))")
    public void qesTemplateManagePointcut() {

    }

    @Before("qesTemplateManagePointcut()")
    public void beforeQesTemplateManage() throws Exception {
        UserValidationUtil.checkUserValid(logger);
    }

    private final static Logger logger = LoggerFactory.getLogger(QesTemplateManageAspect.class);
}
