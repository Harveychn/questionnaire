package com.questionnaire.ssm.module.global.util;

import com.questionnaire.ssm.module.global.enums.RequestResultEnum;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:操作结果包的工具包
 */
public class ResultUtil {
    /**
     * 响应状态以及操作的数据
     *
     * @param object 返回的数据
     * @return
     */
    public static ResponsePkt success(Object object) {
        ResponsePkt responsePkt = new ResponsePkt();
        responsePkt.setCode(RequestResultEnum.SUCCESS.getCode());
        responsePkt.setMessage(RequestResultEnum.SUCCESS.getMessage());
        responsePkt.setData(object);
        return responsePkt;
    }

    /**
     * 响应操作状态不返回数据
     *
     * @return
     */
    public static ResponsePkt success() {
        return success(null);
    }

    /**
     * 响应错误状态以及错误信息
     *
     * @param code
     * @param message
     * @return
     */
    public static ResponsePkt error(int code, String message) {
        ResponsePkt responsePkt = new ResponsePkt();
        responsePkt.setCode(code);
        responsePkt.setMessage(message);
        return responsePkt;
    }
}
