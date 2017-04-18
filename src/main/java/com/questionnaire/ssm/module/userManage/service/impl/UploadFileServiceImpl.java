package com.questionnaire.ssm.module.userManage.service.impl;

import com.questionnaire.ssm.module.global.enums.UploadTemplateCaseEnum;
import com.questionnaire.ssm.module.global.util.CheckUploadFileUtil;
import com.questionnaire.ssm.module.global.util.GetExcelDataUtil;
import com.questionnaire.ssm.module.userManage.pojo.CheckUploadFileDTO;
import com.questionnaire.ssm.module.userManage.pojo.ExcelDataDTO;
import com.questionnaire.ssm.module.userManage.pojo.UploadResultVO;
import com.questionnaire.ssm.module.userManage.service.UploadFileService;
import com.questionnaire.ssm.module.userManage.util.UploadResultUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 郑晓辉 on 2017/4/16.
 * Description:
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {

    public List<UploadResultVO> uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*检查文件类型，返回初步处理的服务器文件地址*/
        List<CheckUploadFileDTO> checkUploadFileDTOList = CheckUploadFileUtil.checkUploadFiles(request, response);

        List<UploadResultVO> uploadResultVOList = new ArrayList<>();

        String filePath = null;
        String currentOriginFileName = null;
        for (CheckUploadFileDTO checkUploadFileDTO : checkUploadFileDTOList) {
            //当前操作的文件名
            currentOriginFileName = checkUploadFileDTO.getOriginFileName();

            /*文件转换错误*/
            if (checkUploadFileDTO.isFileTransError()) {
                UploadResultVO uploadResultVO = UploadResultUtil.fileTransErrorVO(currentOriginFileName);
                uploadResultVOList.add(uploadResultVO);
                continue;
            }
            /*文件类型错误*/
            if (checkUploadFileDTO.isFileTypeError()) {
                UploadResultVO uploadResultVO = UploadResultUtil.fileTypeErrorVO(currentOriginFileName);
                uploadResultVOList.add(uploadResultVO);
                continue;
            }

            //通过上传到服务器的文件路径，读取文件信息，操作数据
            filePath = checkUploadFileDTO.getTempFilePath();

            //文件流数据
            InputStream fileStream = new FileInputStream(filePath);
            ExcelDataDTO excelDataDTO = null;
            if (filePath.endsWith(".xls")) {
                //创建工作簿
                HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileStream);
                excelDataDTO = GetExcelDataUtil.getValue(hssfWorkbook);
            } else if (filePath.endsWith(".xlsx")) {
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileStream);
                excelDataDTO = GetExcelDataUtil.getValue(xssfWorkbook);
            }

            if (excelDataDTO == null) {
                return null;
            }

            //将提取出的数据进行处理，保存
            Map<Integer, String> fieldNameMap = excelDataDTO.getFieldNameMap();
            List<Map<String, String>> valueMapList = excelDataDTO.getValueMapList();

            if (UploadTemplateCaseEnum.UNIT_TEMPLATE == templateCase(fieldNameMap)) {   //单位信息表
                List<Unit> unitList = new ArrayList<>();
                Unit unit = null;

                for (Map<String, String> currentValue : valueMapList) {
                    unit = new Unit();
                    for (Integer index : fieldNameMap.keySet()) {
                        String fieldName = fieldNameMap.get(index);
                        switch (fieldName) {
                            case "单位名":
                                unit.setUnitName(currentValue.get(fieldName));
                                break;
                            case "单位编号":
                                unit.setUnitCode(currentValue.get(fieldName));
                                break;
                            case "单位级别":
                                unit.setUnitLevel(currentValue.get(fieldName));
                                break;
                            case "单位所在省":
                                unit.setUnitProvince(currentValue.get(fieldName));
                                break;
                            case "单位所在市":
                                unit.setUnitCity(currentValue.get(fieldName));
                                break;
                            case "单位详细地址":
                                unit.setAddress(currentValue.get(fieldName));
                                break;
                            default:
                        }
                    }
                    unitList.add(unit);
                }
                //开始保存数据到数据库中
                UploadResultVO<Unit> uploadResultVO = new UploadResultVO<>();
                List<Unit> duplicatedUnitInfo = saveData2UnitDB(unitList);
                try {
                    uploadResultVO.setFileError(false);
                    uploadResultVO.setFileTypeError(false);
                    uploadResultVO.setFileFormatError(false);
                    //插入数据库失败数据
                    if (duplicatedUnitInfo.size() > 0) {
                        uploadResultVO.setErrorRecord(duplicatedUnitInfo);
                    }
                    uploadResultVO.setSuccessRecordCount(unitList.size() - duplicatedUnitInfo.size());
                    uploadResultVO.setFileName(currentOriginFileName);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                uploadResultVOList.add(uploadResultVO);
            } else if (UploadTemplateCaseEnum.UNKNOWN_TEMPLATE == templateCase(fieldNameMap)) {     //文件模板格式错误
                UploadResultVO uploadResultVO = UploadResultUtil.templateErrorVO(currentOriginFileName);
                uploadResultVOList.add(uploadResultVO);
                continue;
            }
            CheckUploadFileUtil.deleteTempFile(checkUploadFileDTO.getTempFilePath());
        }

        return uploadResultVOList;
    }

    /**
     * 根据首行字段名判断上传模板属于哪一张模板
     *
     * @param fieldNameMap
     * @return
     * @throws Exception
     */
    private UploadTemplateCaseEnum templateCase(Map<Integer, String> fieldNameMap) throws Exception {
        if (fieldNameMap.size() == 6) {
            if (fieldNameMap.containsValue("单位名")
                    && fieldNameMap.containsValue("单位编号")
                    && fieldNameMap.containsValue("单位级别")
                    && fieldNameMap.containsValue("单位所在省")
                    && fieldNameMap.containsValue("单位所在市")
                    && fieldNameMap.containsValue("单位详细地址")) {
                return UploadTemplateCaseEnum.UNIT_TEMPLATE;
            }
        }
        return UploadTemplateCaseEnum.UNKNOWN_TEMPLATE;
    }

    /**
     * 保存数据到数据库unit表格
     *
     * @param unitList
     * @return
     * @throws Exception
     */
    @Transactional
    private List<Unit> saveData2UnitDB(List<Unit> unitList) throws Exception {
        List<Unit> duplicateUnit = new ArrayList<>();
        for (Unit savingUnitRecord : unitList) {
            try {
                unitMapper.insertSelective(savingUnitRecord);
            } catch (DuplicateKeyException e) {
                duplicateUnit.add(savingUnitRecord);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return duplicateUnit;
    }

    private UnitMapper unitMapper;
    private final static Logger logger = LoggerFactory.getLogger(UploadFileServiceImpl.class);

    @Autowired
    public UploadFileServiceImpl(UnitMapper unitMapper) {
        this.unitMapper = unitMapper;
    }
}