package com.questionnaire.ssm.module.global.constant;

/**
 * Created by 郑晓辉 on 2017/4/16.
 * Description: 系统常量
 */
public class CONSTANT {

    /*上传图片路径*/
        public final static String UPLOAD_PICTURE_QUESTION = "F:\\创新实践\\uploadFiles\\question-picture";
    //    public final static String UPLOAD_PICTURE_QUESTION = "C:\\Program Files\\Apache Software Foundation\\questionnaire_files\\question-picture";
    /*上传文件路径*/
    private final static String UPLOAD_FILE_PATH = "F:\\创新实践\\uploadFiles\\excels";
    //        private final static String UPLOAD_FILE_PATH = "C:\\Program Files\\Apache Software Foundation\\questionnaire_files\\temp_excels";
    /*服务器模板文件路径*/
    private final static String DOWNLOAD_FOLDER_PATH = "F:\\创新实践\\uploadFiles\\uploadFile-template";
    //        private final static String DOWNLOAD_FOLDER_PATH = "C:\\Program Files\\Apache Software Foundation\\questionnaire_files\\upload_templateFiles";
    /*服务器用户头像路径*/
    private final static String USER_PICTURE_PATH = "F:\\创新实践\\uploadFiles\\user-pictures";
    //        private final static String USER_PICTURE_PATH = "C:\\Program Files\\Apache Software Foundation\\questionnaire_files\\user-pictures";

    //数据库中单位id文本切割符
    private final static String DIVIDE_SYMBOL = "||";
    /*默认头像*/
    private final static String USER_DEFAULT_PICTURE = "default.jpg";
    /*单位变动文本提示信息*/
    private final static String NO_SUCH_UNIT_TIP = "单位信息不存在！";
    //上传模板名
    private final static String UNIT_TEMPLATE_NAME = "UNIT_INFO_TEMPLATE";
    private final static String USER_TEMPLATE_NAME = "USER_INFO_TEMPLATE";
    /*答案为空时，数据库中answer_detail默认为 ‘.’ */
    private final static String NULL_ANSWER_STRING = ".";
    //答案数据为空时，视图中展示的字符串
    private final static String VO_NULL_ANSWER_STRING = " ";

    /*系统角色*/
    private final static String ROLE_SYSTEM_ADMIN = "系统管理员";
    private final static String ROLE_CENTER_ADMIN = "疾控中心管理员";
    private final static String ROLE_RESEARCHER = "调查员";

    public static String getUnitTemplateName() {
        return UNIT_TEMPLATE_NAME;
    }

    public static String getUserTemplateName() {
        return USER_TEMPLATE_NAME;
    }

    public static String getUploadFilePath() {
        return UPLOAD_FILE_PATH;
    }

    public static String getDownloadFolderPath() {
        return DOWNLOAD_FOLDER_PATH;
    }

    public static String getDivideSymbol() {
        return DIVIDE_SYMBOL;
    }

    public static String getUserPicturePath() {
        return USER_PICTURE_PATH;
    }

    public static String getUserDefaultPicture() {
        return USER_DEFAULT_PICTURE;
    }

    public static String getNoSuchUnitTip() {
        return NO_SUCH_UNIT_TIP;
    }

    public static String getNullAnswerString() {
        return NULL_ANSWER_STRING;
    }

    public static String getRoleSystemAdmin() {
        return ROLE_SYSTEM_ADMIN;
    }

    public static String getRoleCenterAdmin() {
        return ROLE_CENTER_ADMIN;
    }

    public static String getRoleResearcher() {
        return ROLE_RESEARCHER;
    }

    public static String getVoNullAnswerString() {
        return VO_NULL_ANSWER_STRING;
    }
}
