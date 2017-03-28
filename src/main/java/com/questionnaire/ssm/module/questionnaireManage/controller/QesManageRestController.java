package com.questionnaire.ssm.module.questionnaireManage.controller;

import com.questionnaire.ssm.module.questionnaireManage.pojo.DisplayQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManage.service.QesManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 郑晓辉 on 2017/3/27.
 * Description: Restful格式的controller
 */
@RequestMapping(value = "/QesManageRest")
@RestController
public class QesManageRestController {

    @GetMapping(value = "/displayQuestionnaire/{questionnaireId}")
    public DisplayQuestionnaireVO displayQuestionnaire(@PathVariable("questionnaireId") long questionnaireId) throws Exception {
        return qesManageService.getQuestionnaireById(questionnaireId);
    }

    private QesManageService qesManageService;

    @Autowired
    public QesManageRestController(QesManageService qesManageService) {
        this.qesManageService = qesManageService;
    }
}
