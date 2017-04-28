package com.questionnaire.ssm.module.global.pojo;

/**
 * Created by 郑晓辉 on 2017/4/27.
 * Description: 单位信息视图实体类
 */
public class UnitInfoVO {
    //单位ID
    private Long unitId;
    //单位名
    private String unitName;
    //单位所在省份
    private String unitProvince;
    //单位所在城市
    private String unitCity;

    @Override
    public String toString() {
        return "UnitInfoVO{" +
                "unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                ", unitProvince='" + unitProvince + '\'' +
                ", unitCity='" + unitCity + '\'' +
                '}';
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitProvince() {
        return unitProvince;
    }

    public void setUnitProvince(String unitProvince) {
        this.unitProvince = unitProvince;
    }

    public String getUnitCity() {
        return unitCity;
    }

    public void setUnitCity(String unitCity) {
        this.unitCity = unitCity;
    }
}
