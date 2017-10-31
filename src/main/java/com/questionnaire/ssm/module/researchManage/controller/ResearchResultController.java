package com.questionnaire.ssm.module.researchManage.controller;

import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.researchManage.pojo.AnswerPaperVO;
import com.questionnaire.ssm.module.researchManage.service.ResearchResultService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/5/1.
 * Description: 调查结果控制器
 */
@Controller
@RequestMapping(value = "/researchResult")
public class ResearchResultController {

    /**
     * 上传答卷
     *
     * @param answerPaperVOList Json格式的AnswerPaper  List
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/submitAnswerPaper")
    @ResponseBody
    public ResponsePkt submitAnswerPaper(@RequestBody List<AnswerPaperVO> answerPaperVOList) throws Exception {
        String userTel = UserValidationUtil.getUserTel(logger);
        if (answerPaperVOList.size() <= 0) {
            return ResultUtil.success(0);
        }
        return ResultUtil.success(researchResultService.multiSubmitAnswerPaper(userTel, answerPaperVOList));
    }

    private final static Logger logger = LoggerFactory.getLogger(ResearchResultController.class);
    private ResearchResultService researchResultService;

    @Autowired
    public ResearchResultController(ResearchResultService researchResultService) {
        this.researchResultService = researchResultService;
    }
}
