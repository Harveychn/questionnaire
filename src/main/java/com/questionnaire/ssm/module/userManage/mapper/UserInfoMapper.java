package com.questionnaire.ssm.module.userManage.mapper;

import com.questionnaire.ssm.module.userManage.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/5/1.
 * Description:
 */
public interface UserInfoMapper {
    /**
     * 根据用户账户名查询用户信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    MyInfoVO selectUserInfoByUserTel(@Param("userTel") String userTel) throws Exception;

    /**
     * 查询用户首页信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    UserInfoHomeVO selectHomePageUserInfo(@Param("userTel") String userTel) throws Exception;

    /**
     * 根据用户名查询调查员信息
     *
     * @param userTel
     * @return
     * @throws Exception
     */
    SurveyorInfoVO selectSurveyorInfoByUserTel(@Param("userTel") String userTel) throws Exception;

    /**
     * 根据用户名以及角色查询用户权限信息
     *
     * @param userTel
     * @param userRole
     * @return
     * @throws Exception
     */
    List<RoleAuthorityVO> listUserAuthorityByUserTelAndRole(@Param("userTel") String userTel,
                                                            @Param("userRole") String userRole) throws Exception;

    List<AllRoleInfoVO> listAllRole() throws Exception;


}
