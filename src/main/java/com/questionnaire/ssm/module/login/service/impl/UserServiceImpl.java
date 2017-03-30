package com.questionnaire.ssm.module.login.service.impl;

import com.questionnaire.ssm.module.generated.mapper.UserMapper;
import com.questionnaire.ssm.module.generated.pojo.User;
import com.questionnaire.ssm.module.global.enums.CodeEnum;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.enums.CodeEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.global.exception.UserValidaException;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.login.pojo.NewPasswordVO;
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

    @Override
    public void updateUserPassword(NewPasswordVO newPasswordVO) throws Exception {

        User userNew = null;

        String userTel = UserValidationUtil.getUserTel(logger);
        try {
            userNew = userMapper.selectByPrimaryKey(userTel);
        } catch (Exception e) {
            logger.error(CodeEnum.UNKNOWN_ERROR.getMessage() + "\n" + DBTableEnum.USER.getTableName());
            throw new OperateDBException(CodeEnum.UNKNOWN_ERROR, DBTableEnum.USER.getTableName());
        }
        if (userNew == null) {
            throw new OperateDBException(CodeEnum.DB_SELECT_FAIL, DBTableEnum.USER.getTableName());
        }

        String oldPassword = userNew.getPassword();
        userNew.setPassword(newPasswordVO.getOldPassword());
        /*旧密码不相同*/
        if (!oldPassword.equals(UserUtil.encodePassword(userNew))) {
            throw new UserValidaException(CodeEnum.OLD_PASSWORD_ERROR);
        }

        userNew.setPassword(newPasswordVO.getNewPassword());

        int result = 0;
        try {
            result = userMapper.updateByPrimaryKeySelective(UserUtil.encoded(userNew));
        } catch (Exception e) {
            logger.error(CodeEnum.UNKNOWN_ERROR.getMessage() + "\n" + DBTableEnum.USER.getTableName());
            throw new OperateDBException(CodeEnum.UNKNOWN_ERROR, DBTableEnum.USER.getTableName());
        }
        if (result != 1) {
            throw new OperateDBException(CodeEnum.DB_UPDATE_FAIL, DBTableEnum.USER.getTableName());
        }
    }


    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
