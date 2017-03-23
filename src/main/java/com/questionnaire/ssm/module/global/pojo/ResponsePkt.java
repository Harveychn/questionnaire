package com.questionnaire.ssm.module.global.pojo;

/**
 * Created by 郑晓辉 on 2017/3/21.
 * Description:统一回复包，以此作为json结果的装换器
 */
public class ResponsePkt<T> {
    /*状态码*/
    private int code;
    /*简要信息*/
    private String Message;
    /*具体数据*/
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
