package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by lanyage on 2018/3/15.
 */
@Entity
@Table(name = "basicinfo_suppliertype")
public class SupplierType {
    @Id
    private int code;
    private String type;

    public SupplierType(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public SupplierType() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
