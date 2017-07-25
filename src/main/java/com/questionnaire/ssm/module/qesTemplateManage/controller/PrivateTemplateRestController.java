package com.questionnaire.ssm.module.qesTemplateManage.controller;

import com.questionnaire.ssm.module.global.service.PaginationDisplayService;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.PrivateTemplateInfoVO;
import com.questionnaire.ssm.module.qesTemplateManage.service.QesTemplateManageService;
import com.questionnaire.ssm.module.questionnaireManage.controller.IsOutOfIndex;
import com.questionnaire.ssm.module.questionnaireManage.pojo.PreOrNextQes;
import com.questionnaire.ssm.module.questionnaireManage.service.QesManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/privateTemplateManage")
public class PrivateTemplateRestController extends IsOutOfIndex {

    /**
     * 获取个人模板信息视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getQesTemplateView")
    public ModelAndView getQesTemplateView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("qesTemplateManage/listPrivateTemplate");
        return modelAndView;
    }

    /**
     * 获取个人模板信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getMyTemplateInfo")
    public List<PrivateTemplateInfoVO> getMyTemplateInfo() throws Exception {
        String userTel = UserValidationUtil.getUserTel(logger);
        List<PrivateTemplateInfoVO> privateTemplateInfoVOS = qesTemplateManageService.listPrivateTemplate(userTel);
        Long[] ids = new Long[privateTemplateInfoVOS.size()];
        for (int i = 0; i < privateTemplateInfoVOS.size(); i++) {
            ids[i] = privateTemplateInfoVOS.get(i).getQuestionnaireId();
        }
        preOrNextQes = null;
        preOrNextQes = new PreOrNextQes(ids);
        return privateTemplateInfoVOS;
    }

    @GetMapping(value = "/displayPriTemplate/{curQesId}")
    public ModelAndView displayPriTemplate(@PathVariable("curQesId") long curQesId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("qesTemplateManage/displayPriTemplate");
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
        ModelAndView modelAndView = new ModelAndView("qesTemplateManage/displayPriTemplate");
        paginationDisplayService.displayNext(preOrNextQes, modelAndView);
        return modelAndView;
    }

    @GetMapping(value = "/displayPrevQesPaper")
    public ModelAndView displayPrevQesPaper() throws Exception {
        ModelAndView modelAndView = new ModelAndView("qesTemplateManage/displayPriTemplate");
        paginationDisplayService.displayPrevious(preOrNextQes, modelAndView);
        return modelAndView;
    }

    private final static Logger logger = LoggerFactory.getLogger(PrivateTemplateRestController.class);
    private PreOrNextQes preOrNextQes;
    private QesManageService qesManageService;
    private QesTemplateManageService qesTemplateManageService;
    private PaginationDisplayService paginationDisplayService;

    @Autowired
    public PrivateTemplateRestController(QesTemplateManageService qesTemplateManageService,
                                         QesManageService qesManageService,
                                         PaginationDisplayService paginationDisplayService) {
        this.preOrNextQes = null;
        this.qesTemplateManageService = qesTemplateManageService;
        this.qesManageService = qesManageService;
        this.paginationDisplayService = paginationDisplayService;
    }
}
