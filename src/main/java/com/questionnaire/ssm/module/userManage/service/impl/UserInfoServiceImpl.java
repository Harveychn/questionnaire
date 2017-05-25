package com.questionnaire.ssm.module.userManage.service.impl;

import com.questionnaire.ssm.module.generated.mapper.UserMapper;
import com.questionnaire.ssm.module.generated.pojo.Role;
import com.questionnaire.ssm.module.generated.pojo.User;
import com.questionnaire.ssm.module.generated.pojo.UserExample;
import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.login.mapper.SysUserMapper;
import com.questionnaire.ssm.module.userManage.enums.UserActionEnum;
import com.questionnaire.ssm.module.userManage.mapper.UserInfoMapper;
import com.questionnaire.ssm.module.userManage.pojo.*;
import com.questionnaire.ssm.module.userManage.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by 郑晓辉 on 2017/5/1.
 * Description:
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Override
    public MyInfoVO getMyInfo(String userTel) throws Exception {
        return userInfoMapper.selectUserInfoByUserTel(userTel);
    }

    @Override
    public SurveyorInfoVO getSurveyorInfo(String userTel) throws Exception {
        SurveyorInfoVO surveyorInfoVO = userInfoMapper.selectSurveyorInfoByUserTel(userTel);
        //用户头像不存在,使用默认头像
        if (surveyorInfoVO.getPicAddress() == null) {
            surveyorInfoVO.setPicAddress(CONSTANT.getUserPicturePath() + "\\" + CONSTANT.getUserDefaultPicture());
        }
        return surveyorInfoVO;
    }

    @Transactional
    @Override
    public void changeMyInfo(String userTel, NewUserInfo newUserInfo) throws Exception {
        User user = new User();
        user.setUserTel(userTel);
        //用户性别
        if (newUserInfo.getUserSex() != null) {
            user.setUserSex(newUserInfo.getUserSex().trim().equals("男") ? 1 : 0);
        }
        //用户出生日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (newUserInfo.getUserBirthday() != null) {
            user.setUserBirthday(sdf.parse(newUserInfo.getUserBirthday()));
        }
        //用户ID
        if (newUserInfo.getUserID() != null) {
            user.setId(newUserInfo.getUserID());
        }
        //用户邮箱
        if (newUserInfo.getUserMailAddress() != null) {
            user.setMailAddress(newUserInfo.getUserMailAddress());
        }
        try {
            userMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_UPDATE_FAIL, DBTableEnum.USER.getTableName());
        }
    }

    /**
     * 获取当前用户身份可以管理的人员权限信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    @Override
    public List<RoleAuthorityVO> listUserRoleAuthorityInfo(String userTel) throws Exception {
        Set<String> roleSet = sysUserMapper.listUserRole(userTel);
        if (roleSet.size() <= 0) {
            return null;
        }
        String userRoleStr = roleSet.iterator().next();
        return userInfoMapper.listUserAuthorityByUserTelAndRole(userTel, userRoleStr);
    }

    /**
     * /**
     * 操作用户权限信息
     *
     * @param userAccounts   要操作的用户账户
     * @param userActionEnum 操作的动作枚举
     * @return 操作失败的账户信息
     * @throws Exception
     */
    @Override
    @Transactional
    public List<String> operateUserAccount(List<String> userAccounts, UserActionEnum userActionEnum) throws Exception {
        UserExample userExample = new UserExample();
        User user = new User();
        //操作失败的用户账户名
        List<String> failOperateAccount = new ArrayList<>();
        switch (userActionEnum) {
            case ENABLE_USER_ACCOUNT://启用账户
                userExample.createCriteria().andUserTelIn(userAccounts);
                user.setIsValid(true);
                userMapper.updateByExampleSelective(user, userExample);
                break;
            case DISABLE_USER_ACCOUNT://禁用账户
                userExample.createCriteria().andUserTelIn(userAccounts);
                user.setIsValid(false);
                userMapper.updateByExampleSelective(user, userExample);
                break;
            case DELETE_USER_ACCOUNT://删除用户账户
                for (String curUserAccount : userAccounts) {
                    try {
                        userMapper.deleteByPrimaryKey(curUserAccount);
                    } catch (DataIntegrityViolationException e) {//数据完整性约束异常
                        failOperateAccount.add(curUserAccount);
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                        failOperateAccount.add(curUserAccount);
                    }
                }
                break;
            default:
                break;
        }
        return failOperateAccount;
    }

    @Override
    public List<AllRoleInfoVO> listAllRole() throws Exception {
        return userInfoMapper.listAllRole();
    }

    /**
     * 更新用户权限信息
     *
     * @param newUserAuthorityInfo
     * @throws Exception
     */
    @Override
    @Transactional
    public void updateUserAuthorityInfo(NewUserAuthorityInfo newUserAuthorityInfo) throws Exception {
        User userForUpdate = new User();
        //设置更新的用户名
        if (newUserAuthorityInfo.getUserAccount() == null || newUserAuthorityInfo.getUserAccount().isEmpty()) {
            throw new OperateDBException(CodeForVOEnum.NEW_USER_AUTHORITY_VO_DATA_ERROR, "更新用户表格时");
        }
        userForUpdate.setUserTel(newUserAuthorityInfo.getUserAccount());
        //设置用户新的角色
        if (newUserAuthorityInfo.getUserRoleId() != 0L) {
            userForUpdate.setRoleId(newUserAuthorityInfo.getUserRoleId());
        }
        //设置用户新的姓名
        if (newUserAuthorityInfo.getUserRealName() == null || newUserAuthorityInfo.getUserRealName().isEmpty()) {
            throw new OperateDBException(CodeForVOEnum.NEW_USER_AUTHORITY_VO_DATA_ERROR, "更新用户表格时");
        }
        userForUpdate.setUserRealName(newUserAuthorityInfo.getUserRealName());
        try {
            userMapper.updateByPrimaryKeySelective(userForUpdate);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_UPDATE_FAIL, DBTableEnum.USER.getTableName());
        }
    }

    private SysUserMapper sysUserMapper;
    private UserInfoMapper userInfoMapper;
    private UserMapper userMapper;
    private final static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    public UserInfoServiceImpl(UserInfoMapper userInfoMapper,
                               UserMapper userMapper,
                               SysUserMapper sysUserMapper) {
        this.userInfoMapper = userInfoMapper;
        this.userMapper = userMapper;
        this.sysUserMapper = sysUserMapper;
    }
}
