package com.questionnaire.ssm.module.global.util;

import com.questionnaire.ssm.module.global.enums.RequestResultEnum;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:操作结果包的工具包
 */
public class ResultUtil {
    public static ResponsePkt success(Object object) {
        ResponsePkt responsePkt = new ResponsePkt();
        responsePkt.setCode(RequestResultEnum.SUCCESS.getCode());
        responsePkt.setMessge(RequestResultEnum.SUCCESS.getMessage());
        responsePkt.setData(object);
        return responsePkt;
    }

    public static ResponsePkt success() {
        return success(null);
    }

    public static ResponsePkt error(int code, String message) {
        ResponsePkt responsePkt = new ResponsePkt();
        responsePkt.setCode(code);
        responsePkt.setMessge(message);
        return responsePkt;
    }
}
