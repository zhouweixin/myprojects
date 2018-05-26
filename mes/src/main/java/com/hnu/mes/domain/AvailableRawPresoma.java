package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 10:44 2018/5/9
 * @Modified By:
 */
@Entity
@Table(name = "available_raw_presoma")
public class AvailableRawPresoma {
    /**
     * 批号
     */
    @Id
    private String batchNumber;
    /**
     * 当前总量
     */
    private Double currentActualMaterials;
    /**
     * 可用量
     */
    private Double currentAvailableMaterial;
    /**
     * 单位
     */
    private String meterialsUnit;
    /**
     * 状态
     */
    private Integer judgeCode;

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Double getCurrentActualMaterials() {
        return currentActualMaterials;
    }

    public void setCurrentActualMaterials(Double currentActualMaterials) {
        this.currentActualMaterials = currentActualMaterials;
    }

    public Double getCurrentAvailableMaterial() {
        return currentAvailableMaterial;
    }

    public void setCurrentAvailableMaterial(Double currentAvailableMaterial) {
        this.currentAvailableMaterial = currentAvailableMaterial;
    }

    public String getMeterialsUnit() {
        return meterialsUnit;
    }

    public void setMeterialsUnit(String meterialsUnit) {
        this.meterialsUnit = meterialsUnit;
    }

    public Integer getJudgeCode() {
        return judgeCode;
    }

    public void setJudgeCode(Integer judgeCode) {
        this.judgeCode = judgeCode;
    }

    @Override
    public String toString() {
        return "AvailableRawPresoma{" +
                "batchNumber='" + batchNumber + '\'' +
                ", currentActualMaterials=" + currentActualMaterials +
                ", currentAvailableMaterial=" + currentAvailableMaterial +
                ", meterialsUnit='" + meterialsUnit + '\'' +
                ", judgeCode=" + judgeCode +
                '}';
    }
}
