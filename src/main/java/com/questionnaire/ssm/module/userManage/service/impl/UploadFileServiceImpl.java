package com.questionnaire.ssm.module.userManage.service.impl;

import com.questionnaire.ssm.module.generated.mapper.RoleMapper;
import com.questionnaire.ssm.module.generated.mapper.UnitMapper;
import com.questionnaire.ssm.module.generated.mapper.UserMapper;
import com.questionnaire.ssm.module.generated.pojo.*;
import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.global.enums.UploadTemplateCaseEnum;
import com.questionnaire.ssm.module.global.util.CheckUploadFileUtil;
import com.questionnaire.ssm.module.global.util.GetExcelDataUtil;
import com.questionnaire.ssm.module.login.utils.UserUtil;
import com.questionnaire.ssm.module.userManage.enums.Save2DOResultEnum;
import com.questionnaire.ssm.module.userManage.pojo.CheckUploadFileDTO;
import com.questionnaire.ssm.module.userManage.pojo.ExcelDataDTO;
import com.questionnaire.ssm.module.userManage.pojo.UploadResultVO;
import com.questionnaire.ssm.module.userManage.pojo.UserExcelDTO;
import com.questionnaire.ssm.module.userManage.service.UploadFileService;
import com.questionnaire.ssm.module.userManage.util.UploadResultUtil;
import com.questionnaire.ssm.module.userManage.util.ValidateExcelDataUtil;
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
        //保存上传文件的操作结果
        List<UploadResultVO> uploadResultVOList = new ArrayList<>();

        String filePath = null;
        String currentOriginFileName = null;
        for (CheckUploadFileDTO checkUploadFileDTO : checkUploadFileDTOList) {
            //当前操作的文件名
            currentOriginFileName = checkUploadFileDTO.getOriginFileName();

            /*文件类型错误*/
            if (checkUploadFileDTO.isFileTypeError()) {
                UploadResultVO uploadResultVO = UploadResultUtil.fileTypeErrorVO(currentOriginFileName);
                uploadResultVOList.add(uploadResultVO);
                continue;
            }
            /*文件转换错误*/
            if (checkUploadFileDTO.isFileTransError()) {
                UploadResultVO uploadResultVO = UploadResultUtil.fileTransErrorVO(currentOriginFileName);
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
                //获取excel文件数据
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

            /*保存数据到数据库，并返回操作结果*/
            uploadResultVOList.add(saveData(currentOriginFileName, fieldNameMap, valueMapList));

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
    private UploadTemplateCaseEnum judgeTemplateCase(Map<Integer, String> fieldNameMap) throws Exception {
        if (fieldNameMap.size() == 6) {
            if (fieldNameMap.containsValue("单位名")
                    && fieldNameMap.containsValue("单位编号")
                    && fieldNameMap.containsValue("单位级别")
                    && fieldNameMap.containsValue("单位所在省")
                    && fieldNameMap.containsValue("单位所在市")
                    && fieldNameMap.containsValue("单位详细地址")) {
                return UploadTemplateCaseEnum.UNIT_TEMPLATE;
            }
        } else if (fieldNameMap.size() == 7) {
            if (fieldNameMap.containsValue("用户手机号码(账户)")
                    && fieldNameMap.containsValue("用户角色")
                    && fieldNameMap.containsValue("用户初始密码")
                    && fieldNameMap.containsValue("用户姓名")
                    && fieldNameMap.containsValue("是否激活账户")
                    && fieldNameMap.containsValue("用户所在单位名")
                    && fieldNameMap.containsValue("用户所在单位编号")) {
                return UploadTemplateCaseEnum.USER_INFO_TEMPLATE;
            }
        }
        return UploadTemplateCaseEnum.UNKNOWN_TEMPLATE;
    }

    private UploadResultVO saveData(String currentOriginFileName,
                                    Map<Integer, String> fieldNameMap,
                                    List<Map<String, String>> valueMapList) throws Exception {

        switch (judgeTemplateCase(fieldNameMap)) {
            case UNIT_TEMPLATE:
                return caseUnitTemplate(currentOriginFileName, fieldNameMap, valueMapList);
            case USER_INFO_TEMPLATE:
                return caseUserTemplate(currentOriginFileName, fieldNameMap, valueMapList);
            case UNKNOWN_TEMPLATE:
                return UploadResultUtil.templateErrorVO(currentOriginFileName);
        }
        return null;
    }

    /**
     * 上传文件为单位信息模板
     *
     * @param currentOriginFileName 当前操作文件上传时文件名
     * @param fieldNameMap          首行字段
     * @param valueMapList          数值行
     * @return
     * @throws Exception
     */
    private UploadResultVO<Unit> caseUnitTemplate(String currentOriginFileName,
                                                  Map<Integer, String> fieldNameMap,
                                                  List<Map<String, String>> valueMapList) throws Exception {
        Unit unit = null;
        String errorMsg = null;
        int successRecords = valueMapList.size();
        List<Unit> errorUnitExcelInfoList = new ArrayList<>();

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
            //当前记录重复
            successRecords--;
            switch (saveData2UnitDO(unit)) {
                case SUCCESS:
                    successRecords++;
                    errorMsg = Save2DOResultEnum.SUCCESS.getMessage();
                    break;
                case DUPLICATED_UNIT_RECORD:
                    errorUnitExcelInfoList.add(unit);
                    errorMsg = Save2DOResultEnum.DUPLICATED_UNIT_RECORD.getMessage();
                    break;
                case UNIT_FORMAT_ERROR:
                    errorUnitExcelInfoList.add(unit);
                    errorMsg = Save2DOResultEnum.UNIT_FORMAT_ERROR.getMessage();
                    break;
                case UNKNOWN_ERROR:
                    errorUnitExcelInfoList.add(unit);
                    errorMsg = Save2DOResultEnum.UNKNOWN_ERROR.getMessage();
                    break;
            }
        }
        UploadResultVO<Unit> uploadResultVO = new UploadResultVO<>();

        uploadResultVO.setFileError(false);
        uploadResultVO.setFileTypeError(false);
        uploadResultVO.setFileFormatError(false);
        uploadResultVO.setFileName(currentOriginFileName);
        uploadResultVO.setErrorRecord(errorUnitExcelInfoList);
        uploadResultVO.setTemplateName(CONSTANT.getUnitTemplateName());
        uploadResultVO.setErrorMsg(errorMsg);
        uploadResultVO.setSuccessRecordCount(successRecords);

        return uploadResultVO;
    }

    /**
     * 保存数据到数据库unit表格，
     *
     * @param savingUnitRecord unit信息
     * @return 插入数据时候是否重复
     * @throws Exception
     */
    @Transactional
    private Save2DOResultEnum saveData2UnitDO(Unit savingUnitRecord) throws Exception {
        if (Save2DOResultEnum.FORMAT_SUCCESS != ValidateExcelDataUtil.checkUnitExcelDTO(savingUnitRecord)) {
            return ValidateExcelDataUtil.checkUnitExcelDTO(savingUnitRecord);
        }
        try {
            unitMapper.insertSelective(savingUnitRecord);
        } catch (DuplicateKeyException e) {
            return Save2DOResultEnum.DUPLICATED_UNIT_RECORD;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Save2DOResultEnum.UNKNOWN_ERROR;
        }
        return Save2DOResultEnum.SUCCESS;
    }

    /**
     * 上传文件为用户信息模板文件
     *
     * @param currentOriginFileName
     * @param fieldNameMap
     * @param valueMapList
     * @return
     * @throws Exception
     */
    private UploadResultVO<UserExcelDTO> caseUserTemplate(String currentOriginFileName,
                                                          Map<Integer, String> fieldNameMap,
                                                          List<Map<String, String>> valueMapList) throws Exception {
        UserExcelDTO userExcelDTO = null;
        List<UserExcelDTO> errorUserExcelInfoList = new ArrayList<>();
        String resultMessage = null;
        //假定所有记录都是可以成功插入的记录
        int successRecords = valueMapList.size();
        //取数据并保存
        for (Map<String, String> currentValue : valueMapList) {
            userExcelDTO = new UserExcelDTO();
            for (Integer index : fieldNameMap.keySet()) {
                String fieldName = fieldNameMap.get(index);
                switch (fieldName) {
                    case "用户手机号码(账户)":
                        userExcelDTO.setUserTel(currentValue.get(fieldName));
                        break;
                    case "用户角色":
                        userExcelDTO.setUserRole(currentValue.get(fieldName));
                        break;
                    case "用户初始密码":
                        userExcelDTO.setUserPassword(currentValue.get(fieldName));
                        break;
                    case "用户姓名":
                        userExcelDTO.setUserRealName(currentValue.get(fieldName));
                        break;
                    case "是否激活账户":
                        userExcelDTO.setIsValid(currentValue.get(fieldName));
                        break;
                    case "用户所在单位名":
                        userExcelDTO.setUnitName(currentValue.get(fieldName));
                        break;
                    case "用户所在单位编号":
                        userExcelDTO.setUnitCode(currentValue.get(fieldName));
                        break;
                    default:
                        break;
                }
            }
            //转换前台数据到后台数据实体、保存数据到数据库
            successRecords--;
            switch (saveData2UserInfoDO(userExcelDTO)) {
                case SUCCESS:
                    resultMessage = Save2DOResultEnum.SUCCESS.getMessage();
                    successRecords++;
                    break;
                case NO_SUCH_ROLE:
                    resultMessage = Save2DOResultEnum.NO_SUCH_ROLE.getMessage();
                    errorUserExcelInfoList.add(userExcelDTO);
                    break;
                case NO_SUCH_UNIT:
                    resultMessage = Save2DOResultEnum.NO_SUCH_UNIT.getMessage();
                    errorUserExcelInfoList.add(userExcelDTO);
                    break;
                case DUPLICATED_USER_RECORD:
                    resultMessage = Save2DOResultEnum.DUPLICATED_USER_RECORD.getMessage();
                    errorUserExcelInfoList.add(userExcelDTO);
                    break;
                case UNKNOWN_ERROR:
                    resultMessage = Save2DOResultEnum.UNKNOWN_ERROR.getMessage();
                    errorUserExcelInfoList.add(userExcelDTO);
                    break;
                case USER_FORMAT_ERROR:
                    resultMessage = Save2DOResultEnum.USER_FORMAT_ERROR.getMessage();
                    errorUserExcelInfoList.add(userExcelDTO);
                    break;
            }
        }
        UploadResultVO<UserExcelDTO> uploadResultVO = new UploadResultVO<>();

        uploadResultVO.setFileError(false);
        uploadResultVO.setFileTypeError(false);
        uploadResultVO.setFileFormatError(false);
        uploadResultVO.setFileName(currentOriginFileName);
        uploadResultVO.setErrorMsg(resultMessage);
        uploadResultVO.setTemplateName(CONSTANT.getUserTemplateName());
        uploadResultVO.setErrorRecord(errorUserExcelInfoList);
        uploadResultVO.setSuccessRecordCount(successRecords);

        return uploadResultVO;
    }

    /**
     * 保存数据到user表
     *
     * @param savingUserInfo userExcelInfo
     * @return
     * @throws Exception
     */
    @Transactional
    private Save2DOResultEnum saveData2UserInfoDO(UserExcelDTO savingUserInfo) throws Exception {
        /*校验数据格式*/
        if (Save2DOResultEnum.FORMAT_SUCCESS != ValidateExcelDataUtil.checkUserExcelDTO(savingUserInfo)) {
            return ValidateExcelDataUtil.checkUserExcelDTO(savingUserInfo);
        }

        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andRoleLike(savingUserInfo.getUserRole());

        long roleId = 0;
        try {
            roleId = roleMapper.selectByExample(roleExample).get(0).getRoleId();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        if (0 == roleId) {
            return Save2DOResultEnum.NO_SUCH_ROLE;
        }

        UnitExample unitExample = new UnitExample();
        unitExample.createCriteria()
                .andUnitNameEqualTo(savingUserInfo.getUnitName())
                .andUnitCodeEqualTo(savingUserInfo.getUnitCode());

        long unitId = 0;
        try {
            unitId = unitMapper.selectByExample(unitExample).get(0).getUnitId();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        if (unitId == 0) {
            return Save2DOResultEnum.NO_SUCH_UNIT;
        }
        User user = new User();
        user.setRoleId(roleId);
        user.setUnitId(unitId);
        user.setIsValid(savingUserInfo.getIsValid().trim().equals("是") ? Boolean.TRUE : Boolean.FALSE);
        user.setUserTel(savingUserInfo.getUserTel());
        user.setUserRealName(savingUserInfo.getUserRealName());
        user.setPassword(UserUtil.encodePassword(savingUserInfo.getUserPassword(),
                user.getUserTel(),
                user.getUserRealName())
        );

        try {
            userMapper.insertSelective(user);
        } catch (DuplicateKeyException e) {
            return Save2DOResultEnum.DUPLICATED_USER_RECORD;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Save2DOResultEnum.UNKNOWN_ERROR;
        }

        return Save2DOResultEnum.SUCCESS;
    }

    private UserMapper userMapper;
    private UnitMapper unitMapper;
    private RoleMapper roleMapper;
    private final static Logger logger = LoggerFactory.getLogger(UploadFileServiceImpl.class);

    @Autowired
    public UploadFileServiceImpl(UnitMapper unitMapper,
                                 RoleMapper roleMapper,
                                 UserMapper userMapper) {
        this.unitMapper = unitMapper;
        this.roleMapper = roleMapper;
        this.userMapper = userMapper;
    }
}