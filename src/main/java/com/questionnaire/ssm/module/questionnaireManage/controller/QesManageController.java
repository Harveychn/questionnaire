package com.questionnaire.ssm.module.questionnaireManage.controller;

import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.questionnaireManage.enums.CheckInValidEnum;
import com.questionnaire.ssm.module.questionnaireManage.pojo.CreateQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManage.service.QesManageService;
import com.questionnaire.ssm.module.questionnaireManage.util.CheckVOValidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:问卷管理控制层
 */
@Controller
@RequestMapping("/questionnaireManage")
public class QesManageController {

    /**
     * 获取创建问卷的视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getCreateView")
    public ModelAndView getCreateView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("createQuestionnaireVO", new CreateQuestionnaireVO());
        modelAndView.setViewName("qesManage/createQuestionnaire");
        return modelAndView;
    }

    /**
     * 校验前台参数，失败直接返回失败原因
     * 否则创建问卷 创建正常则返回正常代码，错误会抛出InsertException
     *
     * @param createQuestionnaireVO
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/create")
    @ResponseBody
    public ResponsePkt create(@Valid CreateQuestionnaireVO createQuestionnaireVO) throws Exception {
        if (!CheckVOValidUtil.createQuestionnaireVOValid(createQuestionnaireVO)) {
            return ResultUtil.error(CheckInValidEnum.QUESTIONNAIRE_TITLE_NULL.getCode(),
                    CheckInValidEnum.QUESTIONNAIRE_TITLE_NULL.getMessage());
        }
        qesManageService.insertQuestionnaire(createQuestionnaireVO);
        return ResultUtil.success();
    }

    /***
     * 获取用户的问卷
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/listMyQuestionnaire")
    public ModelAndView listMyQuestionnaire() throws Exception {
        String userTel = UserValidationUtil.getUserTel(logger);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("questionnaireInfoListVO",
                qesManageService.listQuestionnaireInfoByUserTel(userTel));
        modelAndView.setViewName("qesManage/listQuestionnaire");
        return modelAndView;
    }

    /**
     * 预览，展示问卷
     *
     * @param questionnaireId 问卷id
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/displayQuestionnaire/{questionnaireId}")
    public ModelAndView displayQuestionnaire(@PathVariable("questionnaireId") long questionnaireId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("displayQuestionnaireVO",
                qesManageService.getQuestionnaireById(questionnaireId));
        modelAndView.setViewName("qesManage/displayQuestionnaire");
        return modelAndView;
    }

    @GetMapping(value = "/test/{id}")
    @ResponseBody
    public String test(@PathVariable("id") long id) throws Exception {
        System.out.println(id);
        return "ok! id=" + id;
    }

    private static final Logger logger = LoggerFactory.getLogger(QesManageController.class);
    private QesManageService qesManageService;

    @Autowired
    public QesManageController(QesManageService qesManageService) {
        this.qesManageService = qesManageService;
    }
}
