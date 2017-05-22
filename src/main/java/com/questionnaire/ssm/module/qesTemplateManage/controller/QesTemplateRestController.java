package com.questionnaire.ssm.module.qesTemplateManage.controller;

import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.PrivateTemplateInfoVO;
import com.questionnaire.ssm.module.qesTemplateManage.pojo.PublicTemplateInfoVO;
import com.questionnaire.ssm.module.qesTemplateManage.service.QesTemplateManageService;
import com.questionnaire.ssm.module.questionnaireManage.util.OperateQuestionnaireUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.POST;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/30.
 * Description: 问卷模板管理控制器
 */
@RestController
@RequestMapping(value = "/qesTemplateManage")
public class QesTemplateRestController {

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
        return qesTemplateManageService.listPrivateTemplate(userTel);
    }

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
        return qesTemplateManageService.listPublicTemplate();
    }

    /**
     * 批量删除模板信息
     *
     * @param templateIds
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/multiDeleteTemplate")
    public ResponsePkt deleteTemplate(@RequestParam("templateIds") Long[] templateIds) throws Exception {
        //检查操作的id是否为空
        if (templateIds.length <= 0) {
            return ResultUtil.error(CodeForVOEnum.TEMPLATE_IDS_NULL.getCode(),
                    CodeForVOEnum.TEMPLATE_IDS_NULL.getMessage());
        }
        qesTemplateManageService.delTemplateByIds(Arrays.asList(templateIds),
                OperateQuestionnaireUtil.deleteQesPaperTemporaryAction());
        return ResultUtil.success();
    }

    /**
     * 添加个人模板到公共模板
     *
     * @param templateIds 要添加的模板ID
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/add2PublicTemplateLib")
    public ResponsePkt add2PublicTemplateLib(@RequestParam("templateIds") Long[] templateIds) throws Exception {
        //检查操作的id是否为空
        if (templateIds.length <= 0) {
            return ResultUtil.error(CodeForVOEnum.TEMPLATE_IDS_NULL.getCode(),
                    CodeForVOEnum.TEMPLATE_IDS_NULL.getMessage());
        }
        qesTemplateManageService.add2PublicTemplateLib(Arrays.asList(templateIds));
        return ResultUtil.success();
    }

    /**
     * 添加公共模板到个人模板库
     *
     * @param templateIds 要添加的模板ID
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/add2MyTemplateLib")
    public ResponsePkt add2MyTemplateLib(@RequestParam("templateIds") Long[] templateIds) throws Exception {
        if (templateIds.length <= 0) {
            return ResultUtil.error(CodeForVOEnum.TEMPLATE_IDS_NULL.getCode(),
                    CodeForVOEnum.TEMPLATE_IDS_NULL.getMessage());
        }
        qesTemplateManageService.add2MyTemplateLibrary(Arrays.asList(templateIds));
        return ResultUtil.success();
    }

    private final static Logger logger = LoggerFactory.getLogger(QesTemplateRestController.class);
    private QesTemplateManageService qesTemplateManageService;

    @Autowired
    public QesTemplateRestController(QesTemplateManageService qesTemplateManageService) {
        this.qesTemplateManageService = qesTemplateManageService;
    }
}
