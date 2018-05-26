package com.hnu.mes.domain;

import java.io.Serializable;

/**
 * Created by lanyage on 2018/3/17.
 */
public class CustomerRolePrimaryKey implements Serializable{
    private String customerCode;
    private int roleCode;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public int getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(int roleCode) {
        this.roleCode = roleCode;
    }
}
