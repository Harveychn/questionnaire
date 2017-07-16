package com.questionnaire.ssm.module.global.service;

import com.questionnaire.ssm.module.questionnaireManage.pojo.PreOrNextQes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 郑晓辉 on 2017/7/16.
 * Description: 问卷分页展示服务
 */
public interface PaginationDisplayService {


    void displayPrevious(PreOrNextQes preOrNextQes, ModelAndView modelAndView) throws Exception;

    void displayNext(PreOrNextQes preOrNextQes, ModelAndView modelAndView) throws Exception;
}
