package com.questionnaire.ssm.module.notice.controller;


import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.notice.pojo.CreateNoticeVO;
import com.questionnaire.ssm.module.notice.pojo.ListMyNoticeInfoVO;
import com.questionnaire.ssm.module.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * Created by 95884 on 2017/4/1.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
    /**
     * 获取创建公告的视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getCreateNotice")
    public ModelAndView getCreateNotice() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("notice/createNotice");
        return modelAndView;
    }
    /**
     * 获取提醒公告的视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getCreateNoticeForMission")
    public ModelAndView getCreateNoticeForMission() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("notice/createNoticeForMission");
        return modelAndView;
    }

    /**
     * 创建公告
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/createNotice")
    @ResponseBody
    public ResponsePkt createNotice(@RequestBody CreateNoticeVO createNoticeVO) throws Exception {
        String userTel = UserValidationUtil.getUserTel(logger);
        noticeService.insertNotice(userTel, createNoticeVO);
        return ResultUtil.success();
    }

    /**
     * 获取查看公告视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getMyNoticeView")
    public ModelAndView getMyNoticeView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("notice/listNotice");
        return modelAndView;
    }

    /**
     * 获取公告信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/listMyNotice")
    @ResponseBody
    public List<ListMyNoticeInfoVO> listNotice() throws Exception {
        String userTel = UserValidationUtil.getUserTel(logger);
        return noticeService.listNoticeByUserTel(userTel);
    }

    /**
     * 删除公告
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/deleteNotice")
    @ResponseBody
    public ResponsePkt deleteNotice(long[] noticeIdArray) throws Exception {
        UserValidationUtil.checkUserValid(logger);
        //目前array肯定是单个元素
        for (Long noticeId : noticeIdArray) {
            noticeService.deleteNotice(noticeId);
        }
        return ResultUtil.success();
    }

    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    private NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

}
