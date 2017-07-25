package com.questionnaire.ssm.module.resultAnalysis.controller;

import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.resultAnalysis.pojo.StatisticalAnalysisResultVO;
import com.questionnaire.ssm.module.resultAnalysis.service.StatisticalAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/5/30.
 * Description: 统计分析
 */
@Controller
@RequestMapping(value = "/statisticalAnalysis")
public class StatisticalAnalysisController {

    /**
     * 获取结果分析可视化页面
     * *
     *
     * @param missionId
     * @param missionPaperId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getAnalyzeResultView")
    public ModelAndView getAnalyzeResultView(@RequestParam("missionId") String missionId,
                                             @RequestParam("missionPaperId") String missionPaperId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        this.missionId = missionId;
        this.missionPaperId = missionPaperId;
        modelAndView.setViewName("statisticalAnalysis/analyzeResult");
        return modelAndView;
    }

    /**
     * 根据调查id以及调查问卷ID查询结果分析数据
     *
     * @throws Exception
     */
    @GetMapping(value = "/getQesPaperAnalyzeResult")
    @ResponseBody
    public ResponsePkt getQesPaperAnalyzeResult() throws Exception {
        List<StatisticalAnalysisResultVO> resultVOList = statisticalAnalysisService.getQesPaperAnalyzeResult(this.missionId, this.missionPaperId);
        if (resultVOList == null || resultVOList.size() <= 0) {
            return ResultUtil.error(CodeForVOEnum.NO_RESULT_FOR_ANALYZE.getCode(),
                    CodeForVOEnum.NO_RESULT_FOR_ANALYZE.getMessage());
        }
        return ResultUtil.success(resultVOList);
    }

    private String missionId;
    private String missionPaperId;
    private StatisticalAnalysisService statisticalAnalysisService;

    @Autowired
    public StatisticalAnalysisController(StatisticalAnalysisService statisticalAnalysisService) {
        this.statisticalAnalysisService = statisticalAnalysisService;
    }
}
