package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lanyage on 2018/3/18.
 */
@Entity
@Table(name = "eqmanage_archive")
public class EqArchive {
    @Id
    private String code;
//    private Equipment equipment;
    private Date installTime;
    private String defectPeriod;
    private String supplyFactory;
    private String supplyContact;
    private String repairFactory;
    private String RepairContact;
    private String document;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
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
        return RepairContact;
    }

    public void setRepairContact(String repairContact) {
        RepairContact = repairContact;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
