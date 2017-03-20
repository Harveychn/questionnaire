package com.questionnaire.ssm.module.login.service.impl;

import com.questionnaire.ssm.module.global.enums.ModifyUserEnum;
import com.questionnaire.ssm.module.global.mapper.UserMapper;
import com.questionnaire.ssm.module.global.pojo.User;
import com.questionnaire.ssm.module.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 郑晓辉 on 2017/3/20.
 * Description:对用户信息基本操作：增删改查
 */
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int updateUserPassword(User userNew) throws Exception {
        if (null == userNew.getUserTel() || userNew.getUserTel().equals("")) {
            return ModifyUserEnum.ARGS_NULL.getCode();
        }
        if (1 == userMapper.updateByPrimaryKeySelective(userNew)) {
            return ModifyUserEnum.UPDATE_SUCCESS.getCode();
        }
        return ModifyUserEnum.UPDATE_FAIL.getCode();
    }
}
