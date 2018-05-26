package com.hnu.mes.domain;

import java.io.Serializable;

/**
 * User和Role的复合主键
 * 
 * @author zhouweixin
 *
 */
public class UserRolePrimaryKey implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 用户工号 */
	private String userCode;
	/** 角色工号 */
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserRolePrimaryKey [userCode=" + userCode + ", roleCode=" + roleCode + "]";
	}

}
