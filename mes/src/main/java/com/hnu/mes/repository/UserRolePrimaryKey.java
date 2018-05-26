package com.hnu.mes.repository;

import java.io.Serializable;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 16:32 2018/4/13
 * @Modified By:
 */
public class UserRolePrimaryKey implements Serializable {
    /** 用户编码*/
    private String userCode;
    /** 角色编码 */
    private Integer roleCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    public String toString() {
        return "UserRolePrimaryKey{" +
                "userCode='" + userCode + '\'' +
                ", roleCode=" + roleCode +
                '}';
    }
}
