package com.questionnaire.ssm.module.researchManage.controller;

import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.researchManage.enums.MissionStatusEnum;
import com.questionnaire.ssm.module.researchManage.pojo.*;
import com.questionnaire.ssm.module.researchManage.service.MissionManageService;
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
public class ResearchRestController {

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
     * 获取任务管理视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getMissionManageView")
    public ModelAndView getMissionManage() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("researchManage/missionManage");
        return modelAndView;
    }

    /**
     * 获取修改截止日期视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getMissionManageEditView")
    public ModelAndView getMissionManageEdit(Long missionId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        this.missionId = missionId;
        modelAndView.setViewName("researchManage/missionManageEdit");
        return modelAndView;
    }

    /**
     * 修改任务截止日期
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/changeFinalTime")
    @ResponseBody
    public ResponsePkt changeFinalTime(@RequestBody MissionVO missionVO) throws Exception {
        missionManageService.changeFinalTime(this.missionId, missionVO);
        return ResultUtil.success();
    }

    /**
     * 删除任务
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/deleteMission")
    @ResponseBody
    public void deleteMission(@RequestParam("missionId") Long missionId, @RequestParam("questionnaireId") Long questionnaireId) throws Exception {
        missionManageService.deleteMission(missionId, questionnaireId);
    }


    /**
     * 获取任务列表
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/listMission")
    public List<ListMissionVO> listMission() throws Exception {
        return missionManageService.listMission();
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
        if (createResearchMissionVO.getMissionQuestionnaireInfo().size() <= 0) {
            return ResultUtil.error();
        }
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

    /**
     * 获取展示全部已发布调查任务视图页面
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getListAllMissionView")
    public ModelAndView getListAllMissionView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resultAnalysis/listAllMissions");
        return modelAndView;
    }

    /**
     * 查询已发布调查任务
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getReleasedMissions")
    @ResponseBody
    public List<MissionInfoVO> getReleasedMissions() throws Exception {
        String userTel = UserValidationUtil.getUserTel(logger);
        return researchService.listMissionInfo(userTel, MissionStatusEnum.RELEASED_STATUS);
    }


    private final static Logger logger = LoggerFactory.getLogger(ResearchRestController.class);
    private Long missionId;
    private ResearchService researchService;
    private MissionManageService missionManageService;


    @Autowired
    public ResearchRestController(ResearchService researchService,
                                  MissionManageService missionManageService) {
        this.researchService = researchService;
        this.missionManageService = missionManageService;
    }
}
