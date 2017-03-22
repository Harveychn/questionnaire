package com.questionnaire.ssm.module.login.service;

import com.questionnaire.ssm.module.generated.pojo.User;

/**
 * Created by 郑晓辉 on 2017/3/20.
 * Description:修改变动用户表信息服务
 */
public interface UserService {
    /**
     * 更新用户密码
     * @param userNew //修改过的用户信息类
     * @return 修改结果
     * @throws Exception
     */
    int updateUserInfoSelective(User userNew) throws Exception;
}
