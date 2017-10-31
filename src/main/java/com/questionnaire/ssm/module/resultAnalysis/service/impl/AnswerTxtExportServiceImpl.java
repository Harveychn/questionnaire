package com.questionnaire.ssm.module.resultAnalysis.service.impl;

import com.questionnaire.ssm.module.questionnaireManage.enums.QuestionTypeEnum;
import com.questionnaire.ssm.module.resultAnalysis.mapper.ResultExportMapper;
import com.questionnaire.ssm.module.resultAnalysis.pojo.AnswerTxtExportDO;
import com.questionnaire.ssm.module.resultAnalysis.pojo.ExportTxtDataDTO;
import com.questionnaire.ssm.module.resultAnalysis.service.AnswerTxtExportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 郑晓辉 on 2017/7/22.
 * Description: 答案文本数据导出
 */
@Service
public class AnswerTxtExportServiceImpl implements AnswerTxtExportService {

    @Override
    public Workbook getExcelFile(Long missionID, Long qesID) throws Exception {
        List<AnswerTxtExportDO> resultDOList =
                resultExportMapper.listResultTxtByIdsAndTypes(missionID, qesID, exceptQesType());

        ExportTxtDataDTO exportTxtDataDTO = new ExportTxtDataDTO(resultDOList);
        return getWorkBook(exportTxtDataDTO);
    }

    //设置排除哪些题型
    private List<String> exceptQesType() {
        List<String> exceptTypeCode = new ArrayList<>();
        exceptTypeCode.add(QuestionTypeEnum.SINGLE_CHOICE.getCode());
        exceptTypeCode.add(QuestionTypeEnum.MULTIPLE_CHOICE.getCode());
        return exceptTypeCode;
    }

    private Workbook getWorkBook(ExportTxtDataDTO exportTxtDataDTO) {
        Map<ExportTxtDataDTO.KeySetNode, List<ExportTxtDataDTO.ValueNode>> sheetDataMap = exportTxtDataDTO.getSheetDataMap();
        int sheetNum = exportTxtDataDTO.getSize();

        Workbook wb = new XSSFWorkbook();
        //设置单元格样式
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("微软雅黑");
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(font);

        writeSheetsData(sheetDataMap, wb, cellStyle, sheetNum);

        return wb;
    }


    private void writeSheetsData(Map<ExportTxtDataDTO.KeySetNode, List<ExportTxtDataDTO.ValueNode>> sheetDataMap,
                                 Workbook wb, CellStyle cellStyle, int sheetNum) {
        Iterator<ExportTxtDataDTO.KeySetNode> iterator = sheetDataMap.keySet().iterator();
        ExportTxtDataDTO.KeySetNode curKeyNode;
        List<ExportTxtDataDTO.ValueNode> curValueList;

        Sheet curSheet;
        Row curRow;
        for (int sheetIndex = 0; sheetIndex < sheetNum; sheetIndex++) {
            while (iterator.hasNext()) {
                curKeyNode = iterator.next();
                curSheet = wb.createSheet("问题编号-" + curKeyNode.getQesID().toString());
                curValueList = sheetDataMap.get(curKeyNode);
                Row firstRow = curSheet.createRow(0);
                firstRow.createCell(0).setCellValue("问题：");
                firstRow.getCell(0).setCellStyle(cellStyle);
                firstRow.createCell(1).setCellValue(curKeyNode.getQesContent());
                firstRow.getCell(1).setCellStyle(cellStyle);
                Row secondRow = curSheet.createRow(1);
                secondRow.createCell(0).setCellValue("答卷编号");
                secondRow.getCell(0).setCellStyle(cellStyle);
                secondRow.createCell(1).setCellValue("答案内容");
                secondRow.getCell(1).setCellStyle(cellStyle);
                int rowIndex = 2;
                for (ExportTxtDataDTO.ValueNode curValueNode : curValueList) {
                    curRow = curSheet.createRow(rowIndex);
                    curRow.createCell(0).setCellValue(curValueNode.getAnswerPaperID());
                    curRow.getCell(0).setCellStyle(cellStyle);
                    curRow.createCell(1).setCellValue(curValueNode.getAnswerStr());
                    curRow.getCell(1).setCellStyle(cellStyle);
                    rowIndex++;
                }
            }
        }
    }

    private ResultExportMapper resultExportMapper;

    @Autowired
    public AnswerTxtExportServiceImpl(ResultExportMapper resultExportMapper) {
        this.resultExportMapper = resultExportMapper;
    }
}
