package com.questionnaire.ssm.module.userManage.service;

import com.questionnaire.ssm.module.userManage.pojo.MyInfoVO;
import com.questionnaire.ssm.module.userManage.pojo.NewUserInfo;
import com.questionnaire.ssm.module.userManage.pojo.SurveyorInfoVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 郑晓辉 on 2017/5/1.
 * Description:
 */
public interface UserInfoService {

    MyInfoVO getMyInfo(String userTel) throws Exception;

    SurveyorInfoVO getSurveyorInfo(String userTel) throws Exception;

    @Transactional
    void changeMyInfo(String userTel,NewUserInfo newUserInfo) throws Exception;
}
