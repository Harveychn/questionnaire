package com.questionnaire.ssm.module.userManage.pojo;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/16.
 * Description:
 */
public class UploadResultVO<T> {
    /*当前操作的文件名*/
    private String fileName;
    /*成功操作的记录数*/
    private int successRecordCount;
    //当前错误信息所属模板名
    private String templateName;
    /*错误记录信息*/
    private List<T> errorRecord;
    /*是否文件类型错误*/
    private boolean isFileTypeError;
    /*是否上传文件的数据格式组织错误*/
    private boolean isFileFormatError;
    /*是否文件本身错误*/
    private boolean isFileError;
    /*错误信息*/
    private String errorMsg;

    @Override
    public String toString() {
        return "UploadResultVO{" +
                "fileName='" + fileName + '\'' +
                ", successRecordCount=" + successRecordCount +
                ", templateName='" + templateName + '\'' +
                ", errorRecord=" + errorRecord +
                ", isFileTypeError=" + isFileTypeError +
                ", isFileFormatError=" + isFileFormatError +
                ", isFileError=" + isFileError +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getSuccessRecordCount() {
        return successRecordCount;
    }

    public void setSuccessRecordCount(int successRecordCount) {
        this.successRecordCount = successRecordCount;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public List<T> getErrorRecord() {
        return errorRecord;
    }

    public void setErrorRecord(List<T> errorRecord) {
        this.errorRecord = errorRecord;
    }

    public boolean isFileTypeError() {
        return isFileTypeError;
    }

    public void setFileTypeError(boolean fileTypeError) {
        isFileTypeError = fileTypeError;
    }

    public boolean isFileFormatError() {
        return isFileFormatError;
    }

    public void setFileFormatError(boolean fileFormatError) {
        isFileFormatError = fileFormatError;
    }

    public boolean isFileError() {
        return isFileError;
    }

    public void setFileError(boolean fileError) {
        isFileError = fileError;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
