package com.questionnaire.ssm.module.questionnaireManager.handle;

import com.questionnaire.ssm.module.global.enums.RequestResultEnum;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.questionnaireManager.exception.InsertException;
import com.questionnaire.ssm.module.questionnaireManager.exception.UserValidaException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description: 异常处理器
 */
@ControllerAdvice
public class QesManagerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponsePkt insertHandler(Exception e) {
        /*插入异常*/
        if (e instanceof InsertException) {
            InsertException insertException = (InsertException) e;
            return ResultUtil.error(insertException.getCode(), insertException.getMessage());
        }
        /*用户校验异常（未登录、无角色、无权限）*/
        if (e instanceof UserValidaException) {
            UserValidaException userValidaException = (UserValidaException) e;
            return ResultUtil.error(userValidaException.getCode(), userValidaException.getMessage());
        }
        return ResultUtil.error(RequestResultEnum.ERROR.getCode(), RequestResultEnum.ERROR.getMessage());
    }
}
