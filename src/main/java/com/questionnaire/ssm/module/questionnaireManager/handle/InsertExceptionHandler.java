package com.questionnaire.ssm.module.questionnaireManager.handle;

import com.questionnaire.ssm.module.global.enums.RequestResultEnum;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.questionnaireManager.exception.InsertException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:
 */
@ControllerAdvice
public class InsertExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponsePkt insertHandler(Exception e) {
        if (e instanceof InsertException) {
            InsertException insertException = (InsertException) e;
            return ResultUtil.error(insertException.getCode(), insertException.getMessage());
        }
        return ResultUtil.error(RequestResultEnum.ERROR.getCode(), RequestResultEnum.ERROR.getMessage());
    }
}
