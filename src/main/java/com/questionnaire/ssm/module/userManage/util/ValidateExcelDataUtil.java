package com.questionnaire.ssm.module.userManage.util;

import com.questionnaire.ssm.module.generated.pojo.Unit;
import com.questionnaire.ssm.module.userManage.enums.Save2DOResultEnum;
import com.questionnaire.ssm.module.userManage.pojo.UserExcelDTO;

/**
 * Created by 郑晓辉 on 2017/4/22.
 * Description: 检查上传excel文件中的数据是否符合规定
 */
public class ValidateExcelDataUtil {


    /**
     * 校验单位上传格式信息
     *
     * @param unit
     * @return
     */
    public static Save2DOResultEnum checkUnitExcelDTO(Unit unit) {
        //单位名校验
        if (unit.getUnitName() == null || unit.getUnitName().trim().equals("")) {
            return Save2DOResultEnum.UNIT_FORMAT_ERROR;
        }
        //单位编号校验
        if (unit.getUnitCode() == null || unit.getUnitCode().trim().equals("")) {
            return Save2DOResultEnum.UNIT_FORMAT_ERROR;
        }
        return Save2DOResultEnum.FORMAT_SUCCESS;
    }

    /**
     * 校验用户上传模板数据格式
     *
     * @param userExcelDTO
     * @return
     * @throws Exception
     */
    public static Save2DOResultEnum checkUserExcelDTO(UserExcelDTO userExcelDTO) throws Exception {
        //用户信息校验
        if (userExcelDTO.getUserTel() == null || userExcelDTO.getUserTel().trim().equals("")) {
            return Save2DOResultEnum.USER_FORMAT_ERROR;
        }
        if (userExcelDTO.getUserRealName() == null || userExcelDTO.getUserRealName().trim().equals("")) {
            return Save2DOResultEnum.USER_FORMAT_ERROR;
        }
        if (userExcelDTO.getUserPassword() == null || userExcelDTO.getUserPassword().trim().equals("")
                || userExcelDTO.getUserPassword().length() < 6) {
            return Save2DOResultEnum.USER_FORMAT_ERROR;
        }
        //用户角色信息
        if (userExcelDTO.getUserRole() == null || userExcelDTO.getUserRole().trim().equals("")) {
            return Save2DOResultEnum.USER_FORMAT_ERROR;
        }
        //用户账户信息
        if (userExcelDTO.getIsValid() == null || userExcelDTO.getIsValid().trim().equals("")) {
            return Save2DOResultEnum.USER_FORMAT_ERROR;
        }
        //用户单位信息校验
        if (userExcelDTO.getUnitName() == null || userExcelDTO.getUnitName().trim().equals("")) {
            return Save2DOResultEnum.USER_FORMAT_ERROR;
        }
        if (userExcelDTO.getUnitCode() == null || userExcelDTO.getUnitCode().trim().equals("")) {
            return Save2DOResultEnum.USER_FORMAT_ERROR;
        }
        return Save2DOResultEnum.FORMAT_SUCCESS;
    }
}
