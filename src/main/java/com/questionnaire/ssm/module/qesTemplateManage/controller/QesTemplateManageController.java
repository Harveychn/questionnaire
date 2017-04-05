package com.questionnaire.ssm.module.qesTemplateManage.controller;

import com.questionnaire.ssm.module.global.enums.UserActionEnum;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.qesTemplateManage.service.QesTemplateManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 郑晓辉 on 2017/3/30.
 * Description: 问卷模板管理控制器
 */
@RestController
@RequestMapping(value = "/qesTemplateManage")
public class QesTemplateManageController {

    @GetMapping(value = "/getQesTemplate")
    public ModelAndView getQesTemplateView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("qesTemplateManage/listQesTemplate");
        return modelAndView;
    }

    /**
     * 获取公共模板信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getPublicTemplate")
    public ModelAndView getPublicTemplateView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("qesTemplateManage/listPublicTemplate");
        modelAndView.addObject("publicQesTemplateInfoVO",
                qesTemplateManageService.listPublicTemplate());
        return modelAndView;
    }

    /**
     * 添加公共模板到我的模板库
     *
     * @param templateIds 要添加的模板id
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/add2MyTemplateLib")
    public ResponsePkt add2MyTemplateLib(@RequestParam("templateIds") Long[] templateIds) throws Exception {
        qesTemplateManageService.addToMyTemplateLibrary(templateIds,
                UserActionEnum.ADD_PUBLIC_TEMPLATE_2_MY_TEMPLATE_LIBRARY);
        return ResultUtil.success();
    }

    private final static Logger logger = LoggerFactory.getLogger(QesTemplateManageController.class);
    private QesTemplateManageService qesTemplateManageService;

    @Autowired
    public QesTemplateManageController(QesTemplateManageService qesTemplateManageService) {
        this.qesTemplateManageService = qesTemplateManageService;
    }
}
