package com.questionnaire.ssm.module.userManage.util;

import com.questionnaire.ssm.module.userManage.pojo.UploadResultVO;

/**
 * Created by 郑晓辉 on 2017/4/17.
 * Description: 上传结果数据组织工具包
 */
public class UploadResultUtil {

    /**
     * 文件类型错误（1）
     *
     * @param currentFileName
     * @return
     * @throws Exception
     */
    public static UploadResultVO fileTypeErrorVO(String currentFileName) throws Exception {
        UploadResultVO uploadResultVO = new UploadResultVO();

        uploadResultVO.setFileTypeError(true);
        uploadResultVO.setFileError(false);
        uploadResultVO.setFileFormatError(false);
        /*当前操作的文件名*/
        uploadResultVO.setFileName(currentFileName);

        return uploadResultVO;
    }

    /**
     * 文件转换错误（2）
     *
     * @param currentFileName
     * @return
     * @throws Exception
     */
    public static UploadResultVO fileTransErrorVO(String currentFileName) throws Exception {
        UploadResultVO uploadResultVO = new UploadResultVO();

        uploadResultVO.setFileTypeError(false);
        uploadResultVO.setFileError(true);
        uploadResultVO.setFileFormatError(false);
        /*当前操作的文件名*/
        uploadResultVO.setFileName(currentFileName);

        return uploadResultVO;
    }

    /**
     * 模板存在错误（3）
     *
     * @param currentFileName
     * @return
     * @throws Exception
     */
    public static UploadResultVO templateErrorVO(String currentFileName) throws Exception {
        UploadResultVO uploadResultVO = new UploadResultVO();

        uploadResultVO.setFileTypeError(false);
        uploadResultVO.setFileError(false);
        uploadResultVO.setFileFormatError(true);
        /*当前操作的文件名*/
        uploadResultVO.setFileName(currentFileName);

        return uploadResultVO;
    }

}
