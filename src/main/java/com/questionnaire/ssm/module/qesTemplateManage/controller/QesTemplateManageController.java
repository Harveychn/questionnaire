package com.questionnaire.ssm.module.qesTemplateManage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 郑晓辉 on 2017/3/30.
 * Description: 问卷模板管理控制器
 */
@RestController
@RequestMapping(value="/qesTemplateManage")
public class QesTemplateManageController {

    @GetMapping(value = "/getQesTemplateView")
    public ModelAndView getQesTemplateView()throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("qesTemplateManage/listQesTemplate");
        return modelAndView;
    }



}
