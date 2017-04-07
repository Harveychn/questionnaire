package com.questionnaire.ssm.module.notice.controller;


import com.questionnaire.ssm.module.notice.pojo.Notice;
import com.questionnaire.ssm.module.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by 95884 on 2017/4/1.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

    private NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping(value = "/getCreateNotice")
    public ModelAndView getCreateNotice() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("notice", new Notice());
        modelAndView.setViewName("notice/createNotice");
        return modelAndView;
    }

    @PostMapping(value = "/createNotice")
    public ModelAndView createNotice(Notice notice) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        noticeService.insertNotice(notice);
        modelAndView.setViewName("notice/createSuccess");
        return modelAndView;
    }

    @GetMapping(value = "/listNotice")
    public ModelAndView listNotice() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticeListVO", noticeService.listNotice());
        modelAndView.setViewName("notice/listNotice");
        return modelAndView;
    }

    @PostMapping(value = "/deleteNotice/{noticeId}")
    public ModelAndView deleteNotice(@PathVariable("noticeId") long noticeId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
//        noticeService
        modelAndView.setViewName("notice/listNotice");
        return modelAndView;
    }

}
