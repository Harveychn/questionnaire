package com.questionnaire.ssm.module.notice.controller;


import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.notice.pojo.Notice;
import com.questionnaire.ssm.module.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by 95884 on 2017/4/1.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
    private static final Logger logger= LoggerFactory.getLogger(NoticeController.class);
    private NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
    /***
     * 获取创建公告的视图
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getCreateNotice")
    public ModelAndView getCreateNotice() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("notice", new Notice());
        modelAndView.setViewName("notice/newNotice");
        return modelAndView;
    }
    /***
     * 创建公告
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/createNotice")
    public ModelAndView createNotice(Notice notice) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        noticeService.insertNotice(notice);
        modelAndView.setViewName("notice/createSuccess");
        return modelAndView;
    }

    /***
     * 获取公告信息
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/listMyNotice")
    public ModelAndView listNotice() throws Exception {
        String userTel= UserValidationUtil.getUserTel(logger);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticeListVO", noticeService.listNoticeByUserTel(userTel));
        modelAndView.setViewName("notice/viewNotice");
        return modelAndView;
    }
    /***
     * 删除公告
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/deleteNotice/{noticeId}")
    @ResponseBody
    public ModelAndView deleteNotice(@PathVariable("noticeId") long noticeId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        noticeService.deleteNotice(noticeId);
        modelAndView.setViewName("notice/listNotice");
        return modelAndView;
    }

    @GetMapping(value = "/test/{noticeId}")
    @ResponseBody
    public String test(@PathVariable("noticeId") long noticeId) throws Exception{
        System.out.println(noticeId);
        return "noticeId="+noticeId;
    }

}
