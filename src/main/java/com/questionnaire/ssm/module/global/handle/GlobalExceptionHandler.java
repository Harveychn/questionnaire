package com.questionnaire.ssm.module.global.handle;

import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.global.exception.UserValidaException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description: 异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponsePkt insertHandler(Exception e) {
        e.printStackTrace();
        /*操作数据库异常*/
        if (e instanceof OperateDBException) {
            OperateDBException operateDBException = (OperateDBException) e;
            return ResultUtil.error(operateDBException.getCode(), operateDBException.getMessage());
        }
        /*用户校验异常（未登录、无角色、无权限）*/
        if (e instanceof UserValidaException) {
            UserValidaException userValidaException = (UserValidaException) e;
            return ResultUtil.error(userValidaException.getCode(), userValidaException.getMessage());
        }
        return ResultUtil.error(CodeForVOEnum.REQUEST_ERROR.getCode(), CodeForVOEnum.REQUEST_ERROR.getMessage());
    }
}
