package com.questionnaire.ssm.module.global.util;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.questionnaire.ssm.module.userManage.pojo.ExcelDataDTO;
import org.apache.poi.ss.util.CellReference;
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
        DataFormatter formatter = new DataFormatter();
        Sheet currentSheet = null;

        Map<Integer, String> fieldNameMap = new HashMap<>();

        List<Map<String, String>> valueMapList = new ArrayList<>();
        Map<String, String> valuesMap = null;

        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            currentSheet = workbook.getSheetAt(sheetIndex);
            for (Row row : currentSheet) {

                valuesMap = new HashMap<>();
                for (Cell cell : row) {
                    CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());

                    /*第一行数据 字段名 处理*/
                    if (cellRef.getRow() == 0) {
                        fieldNameMap.put(cell.getColumnIndex(), cell.getRichStringCellValue().getString());
                        continue;
                    }

                    switch (cell.getCellType()) {
                        case CELL_TYPE_STRING:
                            System.out.println(cell.getRichStringCellValue().getString());
                            valuesMap.put(fieldNameMap.get(cell.getColumnIndex()), cell.getRichStringCellValue().getString());
                            break;
                        case CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                System.out.println(date);
                                valuesMap.put(fieldNameMap.get(cell.getColumnIndex()), date.toString());
                            } else {
                                System.out.println(cell.getNumericCellValue());
                                valuesMap.put(fieldNameMap.get(cell.getColumnIndex()), String.valueOf(cell.getNumericCellValue()));
                            }
                            break;
                        case CELL_TYPE_BOOLEAN:
                            System.out.println(cell.getBooleanCellValue());
                            valuesMap.put(fieldNameMap.get(cell.getColumnIndex()), String.valueOf(cell.getBooleanCellValue()));
                            break;
                        case CELL_TYPE_FORMULA:
                            System.out.println(cell.getCellFormula());
                            valuesMap.put(fieldNameMap.get(cell.getColumnIndex()), cell.getCellFormula());
                            break;
                        case CELL_TYPE_BLANK:
                            System.out.println();
                            valuesMap.put(fieldNameMap.get(cell.getColumnIndex()), ".");
                            break;
                        default:
                            System.out.println();
                            valuesMap.put(fieldNameMap.get(cell.getColumnIndex()), ".");
                    }

                    if (valuesMap.keySet().size() == fieldNameMap.size()) {
                        valueMapList.add(valuesMap);
                    }
                }
            }
        }
        ExcelDataDTO excelDataDTO = new ExcelDataDTO();
        excelDataDTO.setFieldNameMap(fieldNameMap);
        excelDataDTO.setValueMapList(valueMapList);
        return excelDataDTO;
    }

    private final static Logger logger = LoggerFactory.getLogger(GetExcelDataUtil.class);
}
