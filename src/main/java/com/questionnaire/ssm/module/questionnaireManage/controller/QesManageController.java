package com.questionnaire.ssm.module.questionnaireManage.controller;

import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.questionnaireManage.pojo.CreateQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManage.service.QesManageService;
import com.questionnaire.ssm.module.questionnaireManage.util.OperateQuestionnaireUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:问卷管理控制层
 * 获取创建问卷视图、创建问卷、查询我的问卷信息、查看问卷、暂时删除问卷、永久删除问卷、共享问卷
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
    public ResponsePkt create(@Valid @RequestBody CreateQuestionnaireVO createQuestionnaireVO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(CodeForVOEnum.VALID_FAIL_CREATE_QUESTIONNAIRE.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
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

    /**
     * 批量暂时删除问卷
     *
     * @param questionnaireIds
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/delTemporaryMultiQuestionnaire")
    @ResponseBody
    public ResponsePkt delTemporaryMultiQuestionnaire(@RequestParam("questionnaireIds") Long[] questionnaireIds) throws Exception {
        if (questionnaireIds.length <= 0) {
            return ResultUtil.error(CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getCode(),
                    CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getMessage());
        }
        Questionnaire questionnaire = OperateQuestionnaireUtil.deleteQesPaperTemporaryAction();
        qesManageService.delOrTemplateQesByIds(Arrays.asList(questionnaireIds), questionnaire);
        return ResultUtil.success();
    }

    /**
     * 批量永久删除问卷
     *
     * @param questionnaireIds
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/delForeverMultiQuestionnaire")
    @ResponseBody
    public ResponsePkt delForeverMultiQuestionnaire(@RequestParam("questionnaireIds") Long[] questionnaireIds) throws Exception {
        if (questionnaireIds.length <= 0) {
            return ResultUtil.error(CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getCode(),
                    CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getMessage());
        }
        Questionnaire questionnaire = OperateQuestionnaireUtil.deleteQesPaperForeverAction();
        qesManageService.delOrTemplateQesByIds(Arrays.asList(questionnaireIds)
                , questionnaire);
        return ResultUtil.success();
    }

    /**
     * 批量模板化问卷
     *
     * @param questionnaireIds
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/templateMultiQuestionnaire")
    @ResponseBody
    public ResponsePkt templateMultiQuestionnaire(@RequestParam("questionnaireIds") Long[] questionnaireIds) throws Exception {
        if (questionnaireIds.length <= 0) {
            return ResultUtil.error(CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getCode(),
                    CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getMessage());
        }
        Questionnaire questionnaire = OperateQuestionnaireUtil.templateQesPaperAction();
        qesManageService.delOrTemplateQesByIds(Arrays.asList(questionnaireIds)
                , questionnaire);
        return ResultUtil.success();
    }


    /**
     * 批量共享问卷
     *
     * @param questionnaireIds 要分享的问卷ID
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/shareMultiQuestionnaire")
    @ResponseBody
    public ResponsePkt shareMultiQuestionnaire(@RequestParam("questionnaireIds") Long[] questionnaireIds) throws Exception {
        if (questionnaireIds.length <= 0) {
            return ResultUtil.error(CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getCode(),
                    CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getMessage());
        }
        qesManageService.shareQesPaperByIds(Arrays.asList(questionnaireIds));
        return ResultUtil.success();
    }

    private static final Logger logger = LoggerFactory.getLogger(QesManageController.class);
    private QesManageService qesManageService;

    @Autowired
    public QesManageController(QesManageService qesManageService) {
        this.qesManageService = qesManageService;
    }
}