package com.questionnaire.ssm.module.notice.controller;

import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.notice.pojo.NoticeForSurveyorVO;
import com.questionnaire.ssm.module.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/5/2.
 * Description:
 */
@Controller
@RequestMapping(value = "/noticeRestful")
public class NoticeRestController {

    /**
     * 获取当前用户(调查员)可查看的公告信息
     *
     * @throws Exception
     */
    @GetMapping(value = "/listNoticeInfo")
    @ResponseBody
    public ResponsePkt listNoticeForSurveyor() throws Exception {
        String userTel = UserValidationUtil.getUserTel(logger);
        List<NoticeForSurveyorVO> noticeForSurveyorVOList = noticeService.listNoticeInfoForSurveyor(userTel);
        if (null == noticeForSurveyorVOList) {
            return ResultUtil.success();
        }
        return ResultUtil.success(noticeForSurveyorVOList);
    }

    private NoticeService noticeService;
    private final static Logger logger = LoggerFactory.getLogger(NoticeRestController.class);

    @Autowired
    public NoticeRestController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
}
