package com.questionnaire.ssm.module.researchManage.controller;

import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.researchManage.pojo.CreateResearchMissionVO;
import com.questionnaire.ssm.module.researchManage.pojo.QuestionnaireInfoVO;
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

    /**
     * 获取创建调查视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/createResearchView")
    public ModelAndView createResearchView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("researchManage/createResearch");
        return modelAndView;
    }

    /**
     * 获取可发布的问卷信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/listQuestionnaireInfo")
    @ResponseBody
    public List<QuestionnaireInfoVO> listQuestionnaireInfo() throws Exception {
        String userTel = UserValidationUtil.getUserTel(logger);
        return researchService.listQesInfoByUserTel(userTel);
    }

    /**
     * 创建调查任务
     *
     * @param createResearchMissionVO
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/createResearchMission")
    @ResponseBody
    public ResponsePkt createResearchMission(@RequestBody CreateResearchMissionVO createResearchMissionVO) throws Exception {
        //判断用户是否登录
        String userTel = UserValidationUtil.getUserTel(logger);
        //保存创建的调查任务信息
        researchService.createMissionByUserTel(userTel, createResearchMissionVO);
        return ResultUtil.success();
    }

    /**
     * 查询当前用户可以查看的任务信息
     *
     * @return
     * @throws Exception
     */
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
