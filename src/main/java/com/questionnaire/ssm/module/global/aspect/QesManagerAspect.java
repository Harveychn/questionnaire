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
public class QesManagerAspect {

    private static final Logger logger = LoggerFactory.getLogger(QesManagerAspect.class);

    /**
     * 获取创建问卷视图切入点
     */
    @Pointcut("execution(public * com.questionnaire.ssm.module.questionnaireManager.controller.QesManagerController.getCreateView(..))")
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


    /**
     * 创建问卷过程切点
     */
    @Pointcut("execution(public * com.questionnaire.ssm.module.questionnaireManager.service.impl.QesManagerServiceImpl.insertQuestionnaire(..))")
    public void createQuestionnaireProcess() {

    }

    /**
     * 完成问卷插入后，插入用户操作问卷的记录
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("createQuestionnaireProcess()")
    public long aroundCreateQuestionnaireProcess(ProceedingJoinPoint joinPoint) throws Throwable {
        //检查用户是否登录
        UserValidationUtil.checkUserValid(logger);
        Date currentDate = new Date();
        String userTel = UserValidationUtil.getUserTel(logger);
        Long questionnaireId = (Long) joinPoint.proceed();

        RecordOperateQuestionnaire recordOperateQuestionnaire = new RecordOperateQuestionnaire();
        recordOperateQuestionnaire.setOperateDate(currentDate);
        recordOperateQuestionnaire.setQuestionnaireId(questionnaireId);
        recordOperateQuestionnaire.setUserTel(userTel);
        recordOperateQuestionnaire.setAction(UserActionEnum.INSERT_ACTION.getAction());

        int result = 0;
        try {
            result = recordOperateQuestionnaireMapper.insertSelective(recordOperateQuestionnaire);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(OperateDBEnum.UNKNOWN_ERROR, DBTableEnum.RECORD_OPERATE_QUESTIONNAIRE.getTableName());
        }
        if (result != 1) {
            throw new OperateDBException(OperateDBEnum.INSERT_FAIL, DBTableEnum.RECORD_OPERATE_QUESTIONNAIRE.getTableName());
        }

        return questionnaireId;
    }

    private RecordOperateQuestionnaireMapper recordOperateQuestionnaireMapper;

    @Autowired
    public QesManagerAspect(RecordOperateQuestionnaireMapper recordOperateQuestionnaireMapper) {
        this.recordOperateQuestionnaireMapper = recordOperateQuestionnaireMapper;
    }
}
