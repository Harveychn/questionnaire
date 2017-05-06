package com.questionnaire.ssm.module.userManage.controller;

import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.userManage.pojo.SurveyorInfoVO;
import com.questionnaire.ssm.module.userManage.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 郑晓辉 on 2017/5/2.
 * Description:
 */
@Controller
@RequestMapping(value = "/userInfoRestful")
public class UserInfoRestManageController {

    @GetMapping(value = "/getSurveyorInfo")
    @ResponseBody
    public ResponsePkt getSurveyorInfo() throws Exception {
        String userTel = UserValidationUtil.getUserTel(logger);
        return ResultUtil.success(userInfoService.getSurveyorInfo(userTel));
    }

    private final static Logger logger = LoggerFactory.getLogger(UserInfoRestManageController.class);
    private UserInfoService userInfoService;

    @Autowired
    public UserInfoRestManageController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }
}
