package com.questionnaire.ssm.module.questionnaireManage.controller;

import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.questionnaireManage.pojo.CreateQesVO;
import com.questionnaire.ssm.module.questionnaireManage.pojo.DisplayQesVO;
import com.questionnaire.ssm.module.questionnaireManage.service.ContinueEditService;
import com.questionnaire.ssm.module.questionnaireManage.service.QesManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by 郑晓辉 on 2017/3/27.
 * Description: Restful格式的controller
 */
@RequestMapping(value = "/QesManageRest")
@RestController
public class QesManageRestController {

    /**
     * 查询json格式数据的问卷信息
     *
     * @param questionnaireId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/displayQuestionnaire/{questionnaireId}")
    public DisplayQesVO displayQuestionnaire(@PathVariable("questionnaireId") long questionnaireId) throws Exception {
        return qesManageService.getQuestionnaireById(questionnaireId);
    }

    /**
     * 获取编辑问卷视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getEditQesPaperView/{questionnaireId}")
    public ModelAndView getEditQesPaperView(@PathVariable("questionnaireId") long questionnaireId) throws Exception {
        this.editQesPaperId = questionnaireId;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("qesManage/editQesPaper");
        return modelAndView;
    }

    /**
     * 获取引用问卷视图
     *
     * @param questionnaireId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getQuoteQesPaperView/{questionnaireId}")
    public ModelAndView getQuoteQesPaperView(@PathVariable("questionnaireId") long questionnaireId) throws Exception {
        this.editQesPaperId = questionnaireId;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("qesManage/quoteQesPaper");
        return modelAndView;
    }

    /**
     * 获取要编辑的问卷数据
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getEditQesPaperData")
    public DisplayQesVO getEditQesPaperData() throws Exception {
        return qesManageService.getQuestionnaireById(this.editQesPaperId);
    }

    /**
     * 保存用户继续编辑完成的问卷数据
     *
     * @param createQesVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postEditFinishQesData")
    public ResponsePkt postEditFinishQesData(@Valid @RequestBody CreateQesVO createQesVO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(CodeForVOEnum.VALID_FAIL_CREATE_QUESTIONNAIRE.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        if (!continueEditService.continueEdit(createQesVO)) {
            return ResultUtil.error(CodeForVOEnum.REQUEST_ERROR.getCode(),
                    CodeForVOEnum.REQUEST_ERROR.getMessage());
        }
        return ResultUtil.success();
    }

    /**
     * 引用模板
     *
     * @param createQesVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postQuoteQesPaperData")
    public ResponsePkt postQuoteQesPaperData(@Valid @RequestBody CreateQesVO createQesVO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(CodeForVOEnum.VALID_FAIL_CREATE_QUESTIONNAIRE.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        qesManageService.insertQuestionnaire(createQesVO);
        return ResultUtil.success();
    }

    private Long editQesPaperId;
    private QesManageService qesManageService;
    private ContinueEditService continueEditService;

    @Autowired
    public QesManageRestController(QesManageService qesManageService,
                                   ContinueEditService continueEditService) {
        this.qesManageService = qesManageService;
        this.continueEditService = continueEditService;
    }
}
