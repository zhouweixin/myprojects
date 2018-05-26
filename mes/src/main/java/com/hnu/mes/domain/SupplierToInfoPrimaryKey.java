package com.hnu.mes.domain;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by lanyage on 2018/3/22.
 */
public class SupplierToInfoPrimaryKey implements Serializable{
    @Id
    private String supplierCode;
    @Id
    private String customerCode;
    @Id
    private String headerCode;
    @Id
    private String infoCode;

    public String getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(String infoCode) {
        this.infoCode = infoCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getHeaderCode() {
        return headerCode;
    }

    public void setHeaderCode(String headerCode) {
        this.headerCode = headerCode;
    }
}
