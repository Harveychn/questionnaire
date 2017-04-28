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
import com.questionnaire.ssm.module.login.pojo.UserInfoVO;
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
     * 获取用户信息
     *
     * @param userTel 用户名
     * @return
     * @throws Exception
     */
    @Override
    public UserInfoVO getUserInfoByUserTel(String userTel) throws Exception {
        UserInfoVO userInfoVO = new UserInfoVO();
        User user = userMapper.selectByPrimaryKey(userTel);
        if (user == null) {
            return null;
        }
        //用户账户
        userInfoVO.setUserTel(userTel);
        //用户真实姓名
        userInfoVO.setUserRealName(user.getUserRealName());
        //用户单位信息--单位名
        userInfoVO.setUserUnit(unitService.getUnitInfoByUnitId(user.getUnitId()).getUnitName());
        //用户头像名
        if (user.getUserPicUrl() == null) {
            //若未上传图片,使用默认图片
            userInfoVO.setPicAddress(CONSTANT.getUserDefaultPicture());
        } else {
            userInfoVO.setPicAddress(user.getUserPicUrl());
        }
        return userInfoVO;
    }

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
