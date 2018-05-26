package com.hnu.mes.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.ibatis.annotations.Many;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 菜单信息表
 *
 * @author chenpingxiao
 *
 */
@Entity
@Table(name = "eqmanage_archive")
public class Archive {
    /**
     * 档案编码
     */
    @Id
    @GeneratedValue
    private Long code;


    /**
     * 档案名称
     */
    private String name;

    /**
     * 设备编码
     */
    @ManyToOne(targetEntity = Equipment.class)
    @JoinColumn(name = "equipmentCode", referencedColumnName = "code")
    private Equipment equipmentCode;

    /**
     * 安装时间
     */
    private String installTime;




    /**
     * 安装时间
     */
    private String equipmentName;

    /**
     * 维修期限
     */
    private String defectPeriod;

    /**
     * 供货厂家
     */
    private String supplyFactory;


    /**
     * 供货厂家联系方式
     */
    private String supplyContact;

    /**
     * 维修厂家
     */
    private String repairFactory;

    /**
     * 维修厂家联系方式
     */
    private String repairContact;

    /**
     * 设备描述
     */
    private String document;


    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Equipment getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(Equipment equipmentCode) {
        this.equipmentCode = equipmentCode;
    }



    public String getInstallTime() {
        return installTime;
    }

    public void setInstallTime(String installTime) {
        this.installTime = installTime;
    }

    public String getDefectPeriod() {
        return defectPeriod;
    }

    public void setDefectPeriod(String defectPeriod) {
        this.defectPeriod = defectPeriod;
    }

    public String getSupplyFactory() {
        return supplyFactory;
    }

    public void setSupplyFactory(String supplyFactory) {
        this.supplyFactory = supplyFactory;
    }

    public String getSupplyContact() {
        return supplyContact;
    }

    public void setSupplyContact(String supplyContact) {
        this.supplyContact = supplyContact;
    }

    public String getRepairFactory() {
        return repairFactory;
    }

    public void setRepairFactory(String repairFactory) {
        this.repairFactory = repairFactory;
    }

    public String getRepairContact() {
        return repairContact;
    }

    public void setRepairContact(String repairContact) {
        this.repairContact = repairContact;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "code='" + code +
                ", name='" + name +'\'' +
                ", equipmentCode=" + equipmentCode +
                ", equipmentName=" + equipmentName +
                ", installTime=" + installTime +
                ", defectPeriod=" + defectPeriod +
                ", supplyFactory=" + supplyFactory +
                ", supplyContact=" + supplyContact +
                ", repairFactory=" + repairFactory +
                ", repairContact=" + repairContact +
                ", document=" + document +
                '}';
    }
}

