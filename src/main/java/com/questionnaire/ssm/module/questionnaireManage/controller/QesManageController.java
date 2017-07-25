package com.questionnaire.ssm.module.questionnaireManage.controller;

import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.service.PaginationDisplayService;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.questionnaireManage.pojo.CreateQesVO;
import com.questionnaire.ssm.module.questionnaireManage.pojo.MyQesVO;
import com.questionnaire.ssm.module.questionnaireManage.pojo.PreOrNextQes;
import com.questionnaire.ssm.module.questionnaireManage.pojo.TempDelQesPaperVO;
import com.questionnaire.ssm.module.questionnaireManage.service.QesManageService;
import com.questionnaire.ssm.module.questionnaireManage.util.OperateQuestionnaireUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:问卷管理控制层
 * 获取创建问卷视图、创建问卷、查询我的问卷信息、查看问卷、暂时删除问卷、永久删除问卷、共享问卷
 */
@Controller
@RequestMapping("/questionnaireManage")
public class QesManageController extends IsOutOfIndex {

    /**
     * 获取创建问卷的视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getCreateView")
    public ModelAndView getCreateView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("createQuestionnaireVO", new CreateQesVO());
        modelAndView.setViewName("qesManage/createQuestionnaire");
        return modelAndView;
    }

    /**
     * 校验前台参数，失败直接返回失败原因
     * 否则创建问卷 创建正常则返回正常代码，错误会抛出InsertException
     *
     * @param createQesVO
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/create")
    @ResponseBody
    public ResponsePkt create(@Valid @RequestBody CreateQesVO createQesVO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(CodeForVOEnum.VALID_FAIL_CREATE_QUESTIONNAIRE.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        qesManageService.insertQuestionnaire(createQesVO);
        return ResultUtil.success();
    }

    /**
     * 获取用户个人问卷视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getListMyQesPaperView")
    public String getListMyQesPaperView() throws Exception {
        return "qesManage/listQuestionnaire";
    }

    /**
     * 获取用户的问卷信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/listMyQuestionnaire")
    @ResponseBody
    public List<MyQesVO> listMyQuestionnaire() throws Exception {
        String userTel = UserValidationUtil.getUserTel(logger);
        List<MyQesVO> resultVO = qesManageService.listQuestionnaireInfoByUserTel(userTel);
        Long[] qesIds = new Long[resultVO.size()];
        for (int i = 0; i < resultVO.size(); i++) {
            qesIds[i] = resultVO.get(i).getQuestionnaireId();
        }
        preOrNextQes = null;
        preOrNextQes = new PreOrNextQes(qesIds);
        return resultVO;
    }

    /**
     * 预览，展示问卷
     *
     * @param curQesId 问卷id
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/displayQuestionnaire/{questionnaireId}")
    public ModelAndView displayQuestionnaire(@PathVariable("questionnaireId") long curQesId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("qesManage/displayQuestionnaire");
        modelAndView.addObject("displayQuestionnaireVO",
                qesManageService.getQuestionnaireById(curQesId));
        preOrNextQes.setCurrentQesPaperId(curQesId);
        isOutOfMinIndex(preOrNextQes, modelAndView);
        isOutOfMaxIndex(preOrNextQes, modelAndView);
        //设置当前查看的问卷id
        preOrNextQes.setCurrentQesPaperId(curQesId);
        return modelAndView;
    }

    /**
     * 展示下一份问卷信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/displayNextQesPaper")
    public ModelAndView displayNextQesPaper() throws Exception {
        ModelAndView modelAndView = new ModelAndView("qesManage/displayQuestionnaire");
        paginationDisplayService.displayNext(preOrNextQes, modelAndView);
        return modelAndView;
    }

    /**
     * 展示上一份问卷信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/displayPrevQesPaper")
    public ModelAndView displayPrevQesPaper() throws Exception {
        ModelAndView modelAndView = new ModelAndView("qesManage/displayQuestionnaire");
        paginationDisplayService.displayPrevious(preOrNextQes, modelAndView);
        return modelAndView;
    }

    /**
     * 批量暂时删除问卷
     *
     * @param questionnaireIds
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/delTemporaryMultiQuestionnaire")
    @ResponseBody
    public ResponsePkt delTemporaryMultiQuestionnaire(@RequestParam("questionnaireIds") Long[] questionnaireIds) throws Exception {
        if (questionnaireIds.length <= 0) {
            return ResultUtil.error(CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getCode(),
                    CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getMessage());
        }
        Questionnaire questionnaire = OperateQuestionnaireUtil.deleteQesPaperTemporaryAction();
        qesManageService.delQesByIds(Arrays.asList(questionnaireIds), questionnaire);
        return ResultUtil.success();
    }

    /**
     * 恢复回收站问卷到原来位置
     *
     * @param questionnaireIds
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/restoreMultiQuestionnaire")
    @ResponseBody
    public ResponsePkt restoreMultiQuestionnaire(@RequestParam("questionnaireIds") Long[] questionnaireIds) throws Exception {
        if (questionnaireIds.length <= 0) {
            return ResultUtil.error(CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getCode(),
                    CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getMessage());
        }
        Questionnaire questionnaire = OperateQuestionnaireUtil.restoreQesPaperAction();
        qesManageService.delQesByIds(Arrays.asList(questionnaireIds), questionnaire);
        return ResultUtil.success();
    }

    /**
     * 批量永久删除问卷
     *
     * @param questionnaireIds
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/delForeverMultiQuestionnaire")
    @ResponseBody
    public ResponsePkt delForeverMultiQuestionnaire(@RequestParam("questionnaireIds") Long[] questionnaireIds) throws Exception {
        if (questionnaireIds.length <= 0) {
            return ResultUtil.error(CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getCode(),
                    CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getMessage());
        }
        qesManageService.delDataForeverQesByIds(Arrays.asList(questionnaireIds));
        return ResultUtil.success();
    }

    /**
     * 获取暂时删除的问卷视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/temporaryDeleteQesPaperView")
    public ModelAndView temporaryDeleteQesPaperView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("qesManage/recycleBinQuestionnaire");
        return modelAndView;
    }

    /**
     * 获取暂时删除的问卷信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/listTemporaryDeleteQesPaper")
    @ResponseBody
    public List<TempDelQesPaperVO> listTemporaryDeleteQesPaper() throws Exception {
        String userTel = UserValidationUtil.getUserTel(logger);
        return qesManageService.listTempDelQesPaperByUserTel(userTel);
    }

    /**
     * 批量模板化问卷
     *
     * @param questionnaireIds
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/templateMultiQuestionnaire")
    @ResponseBody
    public ResponsePkt templateMultiQuestionnaire(@RequestParam("questionnaireIds") Long[] questionnaireIds) throws Exception {
        if (questionnaireIds.length <= 0) {
            return ResultUtil.error(CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getCode(),
                    CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getMessage());
        }
        qesManageService.templateQesPaperByIds(Arrays.asList(questionnaireIds));
        return ResultUtil.success();
    }

    /**
     * 批量共享问卷
     *
     * @param questionnaireIds 要分享的问卷ID
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/shareMultiQuestionnaire")
    @ResponseBody
    public ResponsePkt shareMultiQuestionnaire(@RequestParam("questionnaireIds") Long[] questionnaireIds) throws Exception {
        if (questionnaireIds.length <= 0) {
            return ResultUtil.error(CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getCode(),
                    CodeForVOEnum.QUESTIONNAIRE_IDS_NULL.getMessage());
        }
        qesManageService.shareQesPaperByIds(Arrays.asList(questionnaireIds));
        return ResultUtil.success();
    }

    private static final Logger logger = LoggerFactory.getLogger(QesManageController.class);
    private PreOrNextQes preOrNextQes;
    private QesManageService qesManageService;
    private PaginationDisplayService paginationDisplayService;

    @Autowired
    public QesManageController(QesManageService qesManageService,
                               PaginationDisplayService paginationDisplayService) {
        this.preOrNextQes = null;
        this.qesManageService = qesManageService;
        this.paginationDisplayService = paginationDisplayService;
    }
}