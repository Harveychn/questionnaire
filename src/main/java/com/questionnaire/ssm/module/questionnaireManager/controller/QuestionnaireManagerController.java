package com.questionnaire.ssm.module.questionnaireManager.controller;

import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.questionnaireManager.pojo.CreateQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManager.service.QuestionnaireManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:问卷管理控制器
 */
@Controller
@RequestMapping("/questionnaireManager")
public class QuestionnaireManagerController {

    /**
     * 创建问卷 正常返回正常代码，错误则抛出InsertException
     *
     * @param createQuestionnaireVO
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/create")
    @ResponseBody
    public ResponsePkt Create(CreateQuestionnaireVO createQuestionnaireVO) throws Exception {
        questionnaireManagerService.insertQuestionnaire(createQuestionnaireVO);
        return ResultUtil.success();
    }

    /***
     * 获取已有的问卷
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/listPaper")
    @ResponseBody
    public ResponsePkt listPaper() throws Exception {
        return new ResponsePkt();
    }

    private static final Logger logger = LoggerFactory.getLogger(QuestionnaireManagerController.class);
    private QuestionnaireManagerService questionnaireManagerService;

    @Autowired
    public QuestionnaireManagerController(QuestionnaireManagerService questionnaireManagerService) {
        this.questionnaireManagerService = questionnaireManagerService;
    }
}
