package com.questionnaire.ssm.module.questionnaireManager.controller;

import com.questionnaire.ssm.module.global.enums.RequestResultEnum;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.questionnaireManager.enums.CheckInValidEnum;
import com.questionnaire.ssm.module.questionnaireManager.pojo.CreateQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManager.service.QuestionnaireManagerService;
import com.questionnaire.ssm.module.questionnaireManager.util.CheckVOValidUtil;
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
 * Description:问卷管理控制层
 */
@Controller
@RequestMapping("/questionnaireManager")
public class QuestionnaireManagerController {

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
    public ResponsePkt Create(CreateQuestionnaireVO createQuestionnaireVO) throws Exception {
        if (!CheckVOValidUtil.createQuestionnaireVOValid(createQuestionnaireVO)) {
            return ResultUtil.error(CheckInValidEnum.QUESTIONNAIRE_TITLE_NULL.getCode(),
                    CheckInValidEnum.QUESTIONNAIRE_TITLE_NULL.getMessage());
        }
        questionnaireManagerService.insertQuestionnaire(createQuestionnaireVO);
        return ResultUtil.success();
    }

    /***
     * 获取全部已有的问卷
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/listAllPaperTitle")
    @ResponseBody
    public ResponsePkt listAllPaper() throws Exception {
        return new ResponsePkt();
    }

    private static final Logger logger = LoggerFactory.getLogger(QuestionnaireManagerController.class);
    private QuestionnaireManagerService questionnaireManagerService;

    @Autowired
    public QuestionnaireManagerController(QuestionnaireManagerService questionnaireManagerService) {
        this.questionnaireManagerService = questionnaireManagerService;
    }
}
