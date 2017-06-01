package com.questionnaire.ssm.module.login.service.impl;

import com.questionnaire.ssm.module.generated.mapper.UserMapper;
import com.questionnaire.ssm.module.generated.pojo.User;
import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.global.exception.UserValidaException;
import com.questionnaire.ssm.module.global.service.UnitService;
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

    /**
     * 更新用户密码
     *
     * @param newPasswordVO 新密码实体
     * @throws Exception
     */
    @Override
    public void updateUserPassword(NewPasswordVO newPasswordVO) throws Exception {

        User userNew = null;

        String userTel = UserValidationUtil.getUserTel(logger);
        try {
            userNew = userMapper.selectByPrimaryKey(userTel);
        } catch (Exception e) {
            logger.error(CodeForVOEnum.UNKNOWN_ERROR.getMessage() + "\n" + DBTableEnum.USER.getTableName());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, DBTableEnum.USER.getTableName());
        }
        if (userNew == null) {
            throw new OperateDBException(CodeForVOEnum.DB_SELECT_FAIL, DBTableEnum.USER.getTableName());
        }

        String oldPassword = userNew.getPassword();
        userNew.setPassword(newPasswordVO.getOldPassword());
        /*旧密码不相同*/
        if (!oldPassword.equals(UserUtil.encodePassword(userNew))) {
            throw new UserValidaException(CodeForVOEnum.OLD_PASSWORD_ERROR);
        }

        userNew.setPassword(newPasswordVO.getNewPassword());

        int result = 0;
        try {
            result = userMapper.updateByPrimaryKeySelective(UserUtil.encoded(userNew));
        } catch (Exception e) {
            logger.error(CodeForVOEnum.UNKNOWN_ERROR.getMessage() + "\n" + DBTableEnum.USER.getTableName());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, DBTableEnum.USER.getTableName());
        }
        if (result != 1) {
            throw new OperateDBException(CodeForVOEnum.DB_UPDATE_FAIL, DBTableEnum.USER.getTableName());
        }
    }

    /**
     * 用户登录系统记录信息
     *
     * @param userLoginRecord 用户登录系统数据
     * @throws Exception
     */
    @Override
    public void updateUserLoginRecord(User userLoginRecord) throws Exception {
        userMapper.updateByPrimaryKeySelective(userLoginRecord);
    }

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserMapper userMapper;
    private UnitService unitService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,
                           UnitService unitService) {
        this.userMapper = userMapper;
        this.unitService = unitService;
    }
}
