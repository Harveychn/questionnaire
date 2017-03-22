package com.questionnaire.ssm.module.questionnaireManager.controller;

import com.questionnaire.ssm.module.questionnaireManager.pojo.CreateQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManager.service.QuestionnaireManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:问卷管理控制器
 */
@Controller
@RequestMapping("/questionnaireManager")
public class QuestionnaireManagerController {

    @RequestMapping("/create")
    public void Create(CreateQuestionnaireVO createQuestionnaireVO) throws Exception {
        questionnaireManagerService.insertQuestionnaire(createQuestionnaireVO);
    }


    private static final Logger logger = LoggerFactory.getLogger(QuestionnaireManagerController.class);
    private QuestionnaireManagerService questionnaireManagerService;

    @Autowired
    public QuestionnaireManagerController(QuestionnaireManagerService questionnaireManagerService) {
        this.questionnaireManagerService = questionnaireManagerService;
    }
}
