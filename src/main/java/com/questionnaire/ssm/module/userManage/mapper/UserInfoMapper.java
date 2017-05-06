package com.questionnaire.ssm.module.userManage.mapper;

import com.questionnaire.ssm.module.userManage.pojo.MyInfoVO;
import com.questionnaire.ssm.module.userManage.pojo.SurveyorInfoVO;
import org.apache.ibatis.annotations.Param;

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

    SurveyorInfoVO selectSurveyorInfoByUserTel(@Param("userTel") String userTel)throws Exception;
}
