package com.questionnaire.ssm.module.userManage.service.impl;

import com.questionnaire.ssm.module.generated.mapper.UserMapper;
import com.questionnaire.ssm.module.generated.pojo.User;
import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.userManage.mapper.UserInfoMapper;
import com.questionnaire.ssm.module.userManage.pojo.MyInfoVO;
import com.questionnaire.ssm.module.userManage.pojo.NewUserInfo;
import com.questionnaire.ssm.module.userManage.pojo.SurveyorInfoVO;
import com.questionnaire.ssm.module.userManage.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;

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

    private UserInfoMapper userInfoMapper;
    private UserMapper userMapper;
    private final static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    public UserInfoServiceImpl(UserInfoMapper userInfoMapper,
                               UserMapper userMapper) {
        this.userInfoMapper = userInfoMapper;
        this.userMapper = userMapper;
    }
}
