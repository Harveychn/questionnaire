package com.questionnaire.ssm.module.login.service.impl;

import com.questionnaire.ssm.module.global.enums.ModifyUserEnum;
import com.questionnaire.ssm.module.generated.mapper.UserMapper;
import com.questionnaire.ssm.module.generated.pojo.User;
import com.questionnaire.ssm.module.login.service.UserService;
import com.questionnaire.ssm.module.login.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 郑晓辉 on 2017/3/20.
 * Description:对用户信息基本操作：增删改查
 */
@Service
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int updateUserInfoSelective(User userNew) throws Exception {

        int updateResult = 0;
        try {
            updateResult = userMapper.updateByPrimaryKeySelective(UserUtil.cloneUser(userNew));
        } catch (Exception e) {
            logger.error("[MESSAGE]:" + e.getMessage() + "\n[CAUSE]:" + e.getCause());
            return ModifyUserEnum.UPDATE_FAIL.getCode();
        }

        if (1 == updateResult) {
            return ModifyUserEnum.UPDATE_SUCCESS.getCode();
        }
        return ModifyUserEnum.UPDATE_FAIL.getCode();
    }
}
