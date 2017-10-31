package com.questionnaire.ssm.module.global.service.impl;

import com.questionnaire.ssm.module.global.service.PaginationDisplayService;
import com.questionnaire.ssm.module.questionnaireManage.controller.IsOutOfIndex;
import com.questionnaire.ssm.module.questionnaireManage.pojo.PreOrNextQes;
import com.questionnaire.ssm.module.questionnaireManage.service.QesManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 郑晓辉 on 2017/7/16.
 * Description:
 */
@Service
public class PaginationDisplayServiceImpl extends IsOutOfIndex implements PaginationDisplayService {


    /**
     * 展示上一份问卷信息
     *
     * @param preOrNextQes 全部问卷信息管理
     * @param modelAndView 要返回给用户的modelAndView引用
     * @throws Exception
     */
    @Override
    public void displayPrevious(PreOrNextQes preOrNextQes, ModelAndView modelAndView) throws Exception {
        Long displayingQesId = preOrNextQes.getPreviousQesPaperId();
        //超出左边界
        if (displayingQesId == PreOrNextQes.OUT_OF_INDEX) {
            modelAndView.addObject("displayQuestionnaireVO",
                    qesManageService.getQuestionnaireById(preOrNextQes.getCurrentQesPaperId()));
            modelAndView.addObject("isOutOfMinIndex", true);
        } else {
            modelAndView.addObject("displayQuestionnaireVO",
                    qesManageService.getQuestionnaireById(displayingQesId));
            //设置当前问卷为下一份问卷id
            preOrNextQes.setCurrentQesPaperId(displayingQesId);
            isOutOfMinIndex(preOrNextQes, modelAndView);
        }
        //判断右边界是否超出
        isOutOfMaxIndex(preOrNextQes, modelAndView);
    }

    /**
     * 展示下一份问卷信息
     *
     * @param preOrNextQes 全部问卷信息管理
     * @param modelAndView 要返回给用户的modelAndView引用
     * @throws Exception
     */
    @Override
    public void displayNext(PreOrNextQes preOrNextQes, ModelAndView modelAndView) throws Exception {
        //获取下一份问卷id
        Long displayingQesId = preOrNextQes.getNextQesPaperId();
        if (displayingQesId == PreOrNextQes.OUT_OF_INDEX) {        //没有下一份问卷
            modelAndView.addObject("displayQuestionnaireVO",
                    qesManageService.getQuestionnaireById(preOrNextQes.getCurrentQesPaperId()));
            modelAndView.addObject("isOutOfMaxIndex", true);
        } else { //有下一份问卷
            modelAndView.addObject("displayQuestionnaireVO",
                    qesManageService.getQuestionnaireById(displayingQesId));
            //设置当前问卷为下一份问卷id
            preOrNextQes.setCurrentQesPaperId(displayingQesId);
            isOutOfMaxIndex(preOrNextQes, modelAndView);
        }
        //左边界判断是否超出
        isOutOfMinIndex(preOrNextQes, modelAndView);
    }

    private QesManageService qesManageService;

    @Autowired
    public PaginationDisplayServiceImpl(QesManageService qesManageService) {
        this.qesManageService = qesManageService;
    }
}
