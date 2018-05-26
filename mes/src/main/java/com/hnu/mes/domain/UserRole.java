package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 用户角色
 * 
 * @author zhouweixin
 *
 */
@Entity
@Table(name = "permission_user_role")
@IdClass(UserRolePrimaryKey.class)
public class UserRole {
	/** 用户编码 */
	@Id
	private String userCode;

	/** 角色编码 */
	@Id
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
		return "UserRole [userCode=" + userCode + ", roleCode=" + roleCode + "]";
	}

}
