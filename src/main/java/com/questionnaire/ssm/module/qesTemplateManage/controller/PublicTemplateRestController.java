package com.questionnaire.ssm.module.qesTemplateManage.controller;

import com.questionnaire.ssm.module.global.service.PaginationDisplayService;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.PublicTemplateInfoVO;
import com.questionnaire.ssm.module.qesTemplateManage.service.QesTemplateManageService;
import com.questionnaire.ssm.module.questionnaireManage.controller.IsOutOfIndex;
import com.questionnaire.ssm.module.questionnaireManage.pojo.PreOrNextQes;
import com.questionnaire.ssm.module.questionnaireManage.service.QesManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/7/16.
 * Description:
 */
@RestController
@RequestMapping(value = "/publicTemplateManage")
public class PublicTemplateRestController extends IsOutOfIndex {

    /**
     * 获取公共模板信息视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getPublicTemplateView")
    public ModelAndView getPublicTemplateView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("qesTemplateManage/listPublicTemplate");
        return modelAndView;
    }

    /**
     * 获取公共模板信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getPublicTemplateInfo")
    public List<PublicTemplateInfoVO> getPublicTemplateInfo() throws Exception {
        List<PublicTemplateInfoVO> resultList = qesTemplateManageService.listPublicTemplate();
        Long[] ids = new Long[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            ids[i] = resultList.get(i).getQuestionnaireId();
        }
        preOrNextQes = null;
        preOrNextQes = new PreOrNextQes(ids);
        return resultList;
    }

    @GetMapping(value = "/displayPubTemplate/{curQesId}")
    public ModelAndView displayPubTemplate(@PathVariable("curQesId") long curQesId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("qesTemplateManage/displayPubTemplate");
        modelAndView.addObject("displayQuestionnaireVO",
                qesManageService.getQuestionnaireById(curQesId));
        preOrNextQes.setCurrentQesPaperId(curQesId);
        isOutOfMinIndex(preOrNextQes, modelAndView);
        isOutOfMaxIndex(preOrNextQes, modelAndView);
        //设置当前查看的问卷id
        preOrNextQes.setCurrentQesPaperId(curQesId);
        return modelAndView;
    }

    @GetMapping(value = "/displayNextQesPaper")
    public ModelAndView displayNextQesPaper() throws Exception {
        ModelAndView modelAndView = new ModelAndView("qesTemplateManage/displayPubTemplate");
        paginationDisplayService.displayNext(preOrNextQes, modelAndView);
        return modelAndView;
    }

    @GetMapping(value = "/displayPrevQesPaper")
    public ModelAndView displayPrevQesPaper() throws Exception {
        ModelAndView modelAndView = new ModelAndView("qesTemplateManage/displayPubTemplate");
        paginationDisplayService.displayPrevious(preOrNextQes, modelAndView);
        return modelAndView;
    }

    private PreOrNextQes preOrNextQes;
    private QesManageService qesManageService;
    private QesTemplateManageService qesTemplateManageService;
    private PaginationDisplayService paginationDisplayService;

    @Autowired
    public PublicTemplateRestController(QesTemplateManageService qesTemplateManageService,
                                        QesManageService qesManageService,
                                        PaginationDisplayService paginationDisplayService) {
        this.preOrNextQes = null;
        this.qesTemplateManageService = qesTemplateManageService;
        this.qesManageService = qesManageService;
        this.paginationDisplayService = paginationDisplayService;
    }
}
