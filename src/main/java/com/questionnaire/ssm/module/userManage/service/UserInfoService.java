package com.questionnaire.ssm.module.userManage.service;

import com.questionnaire.ssm.module.userManage.enums.UserActionEnum;
import com.questionnaire.ssm.module.userManage.pojo.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/5/1.
 * Description:
 */
public interface UserInfoService {

    /**
     * 获取个人信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    MyInfoVO getMyInfo(String userTel) throws Exception;

    /**
     * 获取用户首页个人信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    UserInfoHomeVO getUserInfoHomeVO(String userTel) throws Exception;

    /**
     * 获取调查员信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    SurveyorInfoVO getSurveyorInfo(String userTel) throws Exception;

    /**
     * 个人信息修改
     *
     * @param userTel
     * @param newUserInfo
     * @throws Exception
     */
    @Transactional
    void changeMyInfo(String userTel, NewUserInfo newUserInfo) throws Exception;

    /**
     * 获取当前用户身份可以管理的人员权限信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    List<RoleAuthorityVO> listUserRoleAuthorityInfo(String userTel) throws Exception;

    /**
     * 操作用户权限信息
     *
     * @param userAccounts   要操作的用户账户
     * @param userActionEnum 操作的动作枚举
     * @return 操作失败的账户信息
     * @throws Exception
     */
    List<String> operateUserAccount(List<String> userAccounts, UserActionEnum userActionEnum) throws Exception;

    /**
     * 查询所有角色信息
     *
     * @return
     * @throws Exception
     */
    List<AllRoleInfoVO> listAllRole() throws Exception;

    /**
     * 更新用户权限信息
     *
     * @param newUserAuthorityInfo
     * @throws Exception
     */
    void updateUserAuthorityInfo(NewUserAuthorityInfo newUserAuthorityInfo) throws Exception;
}
