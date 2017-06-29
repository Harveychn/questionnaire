package com.questionnaire.ssm.module.global.controller;

import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.notice.enums.NoticeForCurUserActionEnum;
import com.questionnaire.ssm.module.notice.pojo.NoticeForCurUserVO;
import com.questionnaire.ssm.module.notice.service.Notice2MeService;
import com.questionnaire.ssm.module.userManage.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/6/1.
 * Description: 首页数据控制器
 */
@Controller
@RequestMapping(value = "/homeData")
public class HomePageController {

    /**
     * 获取首页数据
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getHomeVO")
    public ModelAndView getHomeVOData() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        String userTel = UserValidationUtil.getUserTel(logger);
        modelAndView.addObject("myInfo", userInfoService.getUserInfoHomeVO(userTel));
        return modelAndView;
    }

    /**
     * 获取最新的5个公告信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getTop5NoticeForMe")
    @ResponseBody
    public List<NoticeForCurUserVO> getTop5NoticeForMe() throws Exception {
        String userTel = UserValidationUtil.getUserTel(logger);
        return notice2MeService.getReleasedNoticeForCurrentUser(userTel, NoticeForCurUserActionEnum.GET_TOP_5_RELEASED_NOTICE);
    }

    private static final Logger logger = LoggerFactory.getLogger(HomePageController.class);
    private UserInfoService userInfoService;
    private Notice2MeService notice2MeService;

    @Autowired
    public HomePageController(UserInfoService userInfoService,
                              Notice2MeService notice2MeService) {
        this.userInfoService = userInfoService;
        this.notice2MeService = notice2MeService;
    }
}
