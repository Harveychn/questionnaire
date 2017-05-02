package com.questionnaire.ssm.module.researchManage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 郑晓辉 on 2017/5/1.
 * Description: 调查结果控制器
 */
@Controller
@RequestMapping(value = "/researchResult")
public class ResearchResultController {

    @PostMapping(value = "/submitAnswerPaper")
    public void submitAnswerPaper() throws Exception {

    }
}
