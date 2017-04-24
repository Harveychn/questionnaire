package com.questionnaire.ssm.module.researchManage.controller;

import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.researchManage.pojo.CreateResearchMissionVO;
import com.questionnaire.ssm.module.researchManage.pojo.ResearchListVO;
import com.questionnaire.ssm.module.researchManage.service.ResearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/23.
 * Description: 调查管理控制器
 */
@RestController
@RequestMapping(value = "/researchManage")
public class ResearchController {

    @GetMapping(value = "/createResearchView")
    public ModelAndView createResearchView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("researchManage/createResearch");
        return modelAndView;
    }

    @PostMapping(value = "/createResearchMission")
    @ResponseBody
    public ResponsePkt createResearchMission(CreateResearchMissionVO createResearchMissionVO) throws Exception {
        //判断用户是否登录
        String userTel = UserValidationUtil.getUserTel(logger);
        //保存创建的调查任务信息
        researchService.createMissionByUserTel(userTel, createResearchMissionVO);
        return ResultUtil.success();
    }

    @GetMapping(value = "/listResearchMission")
    @ResponseBody
    public List<ResearchListVO> listResearchMission() throws Exception {
        String userTel = UserValidationUtil.getUserTel(logger);
        return researchService.listResearchMission(userTel);
    }

    private ResearchService researchService;
    private final static Logger logger = LoggerFactory.getLogger(ResearchController.class);

    @Autowired
    public ResearchController(ResearchService researchService) {
        this.researchService = researchService;
    }
}
