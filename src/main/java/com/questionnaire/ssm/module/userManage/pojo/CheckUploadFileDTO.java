package com.questionnaire.ssm.module.userManage.pojo;

/**
 * Created by 郑晓辉 on 2017/4/16.
 * Description: 检查上传文件的数据传输对象
 */
public class CheckUploadFileDTO {
    /*当前操作文件名*/
    private String originFileName;
    /*当前文件服务器路径*/
    private String currentServerFilePath;
    /*上传的文件类型是否有错*/
    private boolean isFileTypeError;
    /*文件流转换存在错误*/
    private boolean fileTransError;
    /*暂存的文件路径,处理完成数据需要删除缓存的文件*/
    private String tempFilePath;

    @Override
    public String toString() {
        return "CheckUploadFileDTO{" +
                "originFileName='" + originFileName + '\'' +
                ", currentServerFilePath='" + currentServerFilePath + '\'' +
                ", isFileTypeError=" + isFileTypeError +
                ", fileTransError=" + fileTransError +
                ", tempFilePath='" + tempFilePath + '\'' +
                '}';
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public String getCurrentServerFilePath() {
        return currentServerFilePath;
    }

    public void setCurrentServerFilePath(String currentServerFilePath) {
        this.currentServerFilePath = currentServerFilePath;
    }

    public boolean isFileTypeError() {
        return isFileTypeError;
    }

    public void setFileTypeError(boolean fileTypeError) {
        isFileTypeError = fileTypeError;
    }

    public boolean isFileTransError() {
        return fileTransError;
    }

    public void setFileTransError(boolean fileTransError) {
        this.fileTransError = fileTransError;
    }

    public String getTempFilePath() {
        return tempFilePath;
    }

    public void setTempFilePath(String tempFilePath) {
        this.tempFilePath = tempFilePath;
    }
}
