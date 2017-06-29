package com.questionnaire.ssm.module.login.service.impl;

import com.questionnaire.ssm.module.generated.mapper.UserMapper;
import com.questionnaire.ssm.module.generated.pojo.User;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.global.exception.UserValidaException;
import com.questionnaire.ssm.module.global.service.UnitService;
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
    public void updateUserPassword(String userTel, NewPasswordVO newPasswordVO) throws Exception {
        User userNew = getUserByUserTel(userTel);

        String oldPassword = userNew.getPassword();
        userNew.setPassword(newPasswordVO.getOldPassword());
        /*旧密码不相同*/
        if (!oldPassword.equals(UserUtil.encodePassword(userNew))) {
            throw new UserValidaException(CodeForVOEnum.OLD_PASSWORD_ERROR);
        }
        //设置新密码
        userNew.setPassword(newPasswordVO.getNewPassword());
        //加密后保存
        updateUserPwd(userNew);
    }

    @Override
    public void resetUserPwd(String userTel, String aNewPwd) throws Exception {
        User curUserInfo = getUserByUserTel(userTel);
        //设置新密码
        curUserInfo.setPassword(aNewPwd);
        //加密后保存
        updateUserPwd(curUserInfo);
    }

    @Override
    public void updateUserLoginRecord(User userLoginRecord) throws Exception {
        userMapper.updateByPrimaryKeySelective(userLoginRecord);
    }

    /**
     * 根据用户名查找数据库中用户信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    private User getUserByUserTel(String userTel) throws Exception {
        User curUser = null;

        try {
            curUser = userMapper.selectByPrimaryKey(userTel);
        } catch (Exception e) {
            logger.error(CodeForVOEnum.UNKNOWN_ERROR.getMessage() + "\n" + DBTableEnum.USER.getTableName());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, DBTableEnum.USER.getTableName());
        }
        if (curUser == null) {
            throw new OperateDBException(CodeForVOEnum.DB_SELECT_FAIL, DBTableEnum.USER.getTableName());
        }
        return curUser;
    }

    /**
     * 加密新的密码并更新保存到数据库中
     *
     * @param aNewPwdInfo
     * @throws Exception
     */
    private void updateUserPwd(User aNewPwdInfo) throws Exception {
        int result = 0;
        try {
            result = userMapper.updateByPrimaryKeySelective(UserUtil.encoded(aNewPwdInfo));
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
