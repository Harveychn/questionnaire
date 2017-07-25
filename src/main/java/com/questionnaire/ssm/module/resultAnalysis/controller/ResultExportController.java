package com.questionnaire.ssm.module.resultAnalysis.controller;

import com.questionnaire.ssm.module.resultAnalysis.service.AnswerTxtExportService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 郑晓辉 on 2017/7/23.
 * Description: 导出结果数据
 */
@Controller
@RequestMapping(value = "/export")
public class ResultExportController {

    /**
     * 下载答卷中的文本数据信息
     *
     * @param missionId
     * @param qesId
     * @param response
     * @throws Exception
     */
    @GetMapping("/exportTxtData2Excel")
    public void exportTxtData2Excel(Long missionId, Long qesId, HttpServletResponse response) throws Exception {
        Workbook wb = answerTxtExportService.getExcelFile(missionId, qesId);
        // 清空response
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="
                + new String((new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx").getBytes(), "iso-8859-1"));
        response.setContentType("application/msexcel;charset=UTF-8");
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    private AnswerTxtExportService answerTxtExportService;

    @Autowired
    public ResultExportController(AnswerTxtExportService answerTxtExportService) {
        this.answerTxtExportService = answerTxtExportService;
    }
}
