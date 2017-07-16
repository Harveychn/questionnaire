package com.questionnaire.ssm.module.questionnaireManage.controller;

import com.questionnaire.ssm.module.questionnaireManage.pojo.PreOrNextQes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 郑晓辉 on 2017/6/29.
 * Description: 检查是否超出数组的索引范围
 */
public class IsOutOfIndex {

    //判断是否超出最小边界
    public void isOutOfMinIndex(PreOrNextQes preOrNextQes, ModelAndView modelAndView) throws Exception {
        if (preOrNextQes.getPreviousQesPaperId() == PreOrNextQes.OUT_OF_INDEX) {
            modelAndView.addObject("isOutOfMinIndex", true);
        } else {
            modelAndView.addObject("isOutOfMinIndex", false);
        }
    }

    //判断是否超出最大边界
    public void isOutOfMaxIndex(PreOrNextQes preOrNextQes, ModelAndView modelAndView) throws Exception {
        if (preOrNextQes.getNextQesPaperId() == PreOrNextQes.OUT_OF_INDEX) {
            modelAndView.addObject("isOutOfMaxIndex", true);
        } else {
            modelAndView.addObject("isOutOfMaxIndex", false);
        }
    }
}
