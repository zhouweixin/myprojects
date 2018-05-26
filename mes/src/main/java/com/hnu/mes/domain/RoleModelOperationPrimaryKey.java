package com.hnu.mes.domain;

import java.io.Serializable;

/**
 * 角色,模块,操作的复合主键
 * 
 * @author zhouweixin
 *
 */
public class RoleModelOperationPrimaryKey implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 角色编码 */
	private Integer roleCode;
	/** 模块编码 */
	private Integer modelCode;
	/** 操作编码 */
	private Integer operationCode;

	public Integer getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(Integer roleCode) {
		this.roleCode = roleCode;
	}

	public Integer getModelCode() {
		return modelCode;
	}

	public void setModelCode(Integer modelCode) {
		this.modelCode = modelCode;
	}

	public Integer getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(Integer operationCode) {
		this.operationCode = operationCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "RoleModelOperationPrimaryKey [roleCode=" + roleCode + ", modelCode=" + modelCode + ", operationCode="
				+ operationCode + "]";
	}

}
