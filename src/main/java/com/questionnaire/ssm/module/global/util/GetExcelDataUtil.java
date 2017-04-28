package com.questionnaire.ssm.module.global.util;

import com.questionnaire.ssm.module.userManage.pojo.ExcelDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.ss.usermodel.*;

import java.util.*;

import static org.apache.poi.ss.usermodel.Cell.*;


/**
 * Created by 郑晓辉 on 2017/4/16.
 * Description: 获取excel单元格数据工具类
 */
public class GetExcelDataUtil {

    /**
     * 获取excel表格数据
     *
     * @param workbook HSSFWorkbook 或者 XSSFWorkbook
     * @return
     * @throws Exception
     */
    public static ExcelDataDTO getValue(Workbook workbook) throws Exception {
        Sheet currentSheet = null;
        Map<Integer, String> fieldNameMap = new HashMap<>();
        Map<String, String> valuesMap = null;
        List<Map<String, String>> valueMapList = new ArrayList<>();

        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            currentSheet = workbook.getSheetAt(sheetIndex);
            if (currentSheet == null) {
                continue;
            }
            for (Row row : currentSheet) {
                //行数据为空
                if (row == null) {
                    continue;
                }
                valuesMap = new HashMap<>();
                for (Cell cell : row) {
                    //第一行数据 字段名 处理
                    if (row.getRowNum() == 0) {
                        fieldNameMap.put(cell.getColumnIndex(), cell.getRichStringCellValue().getString());
                        continue;
                    }
                    //单元格数据提取
                    valuesMap.put(fieldNameMap.get(cell.getColumnIndex()), getCellValue(cell));
                }
                //一行单元格中的数据
                if (row.getRowNum() != 0) {
                    valueMapList.add(valuesMap);
                }
            }
        }

        ExcelDataDTO excelDataDTO = new ExcelDataDTO();
        excelDataDTO.setFieldNameMap(fieldNameMap);
        excelDataDTO.setValueMapList(valueMapList);
        return excelDataDTO;
    }

    /**
     * 获取单元格数值
     *
     * @param cell
     * @return
     */
    private static String getCellValue(Cell cell) {
        String cellValue = null;
        switch (cell.getCellType()) {
            case CELL_TYPE_STRING:
                cellValue = cell.getRichStringCellValue().getString();
                break;
            case CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    cellValue = date.toString();
                } else {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
            case CELL_TYPE_BLANK:
                cellValue = ".";
                break;
            default:
                cellValue = ".";
        }
        return cellValue;
    }

    private final static Logger logger = LoggerFactory.getLogger(GetExcelDataUtil.class);
}
