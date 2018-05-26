package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 角色,模块,操作
 * 
 * @author zhouweixin
 *
 */
@Entity
@Table(name = "permission_role_model_operation")
@IdClass(RoleModelOperationPrimaryKey.class)
public class RoleModelOperation {
	/** 角色编码 */
	@Id
	private Integer roleCode;

	/** 模块编码 */
	@Id
	private Integer modelCode;

	/** 操作编码 */
	@Id
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

	@Override
	public String toString() {
		return "RoleModelOperation [roleCode=" + roleCode + ", modelCode=" + modelCode + ", operationCode="
				+ operationCode + "]";
	}

}
