package com.questionnaire.ssm.module.global.aspect;

import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by 郑晓辉 on 2017/3/26.
 * Description: 登录管理模块切面
 */
@Aspect
@Component
public class LoginManageAspect {

    @Pointcut("execution(public * com.questionnaire.ssm.module.login.controller.SysUserController.newPasswordView(..))")
    public void getNewKeyViewPointcut() {

    }

//    @Before("getNewKeyViewPointcut()")
//    public void beforeGetNewKeyView() throws Exception {
//        UserValidationUtil.checkUserValid(logger);
//    }

    private static final Logger logger = LoggerFactory.getLogger(LoginManageAspect.class);
}
