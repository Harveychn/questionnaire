package com.questionnaire.ssm.module.userManage.pojo;

import java.util.List;
import java.util.Map;

/**
 * Created by 郑晓辉 on 2017/4/17.
 * Description: 获取到的excel数据
 */
public class ExcelDataDTO {
    /*字段名Map<index,字段中文名>*/
    private Map<Integer, String> fieldNameMap;
    /*row数据List<Map<字段名，值>>*/
    private List<Map<String, String>> valueMapList;

    @Override
    public String toString() {
        return "ExcelDataDTO{" +
                "fieldNameMap=" + fieldNameMap +
                ", valueMapList=" + valueMapList +
                '}';
    }

    public Map<Integer, String> getFieldNameMap() {
        return fieldNameMap;
    }

    public void setFieldNameMap(Map<Integer, String> fieldNameMap) {
        this.fieldNameMap = fieldNameMap;
    }

    public List<Map<String, String>> getValueMapList() {
        return valueMapList;
    }

    public void setValueMapList(List<Map<String, String>> valueMapList) {
        this.valueMapList = valueMapList;
    }
}
