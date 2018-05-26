package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Created by lanyage on 2018/3/22.
 */
@Entity
@Table(name = "supplier_customer_header_info")
@IdClass(SupplierToInfoPrimaryKey.class)
public class SupplierToInfo {

    @Id
    private String supplierCode;
    @Id
    private String customerCode;
    @Id
    private String headerCode;

    private String infoCode;

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

    public String getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(String infoCode) {
        this.infoCode = infoCode;
    }
}
