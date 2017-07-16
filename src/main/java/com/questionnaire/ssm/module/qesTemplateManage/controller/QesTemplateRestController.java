package com.questionnaire.ssm.module.qesTemplateManage.controller;

import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.qesTemplateManage.service.QesTemplateManageService;
import com.questionnaire.ssm.module.questionnaireManage.controller.IsOutOfIndex;
import com.questionnaire.ssm.module.questionnaireManage.util.OperateQuestionnaireUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Created by 郑晓辉 on 2017/3/30.
 * Description: 问卷模板管理控制器
 */
@RestController
@RequestMapping(value = "/qesTemplateManage")
public class QesTemplateRestController {

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

    private QesTemplateManageService qesTemplateManageService;

    @Autowired
    public QesTemplateRestController(QesTemplateManageService qesTemplateManageService) {
        this.qesTemplateManageService = qesTemplateManageService;
    }
}
