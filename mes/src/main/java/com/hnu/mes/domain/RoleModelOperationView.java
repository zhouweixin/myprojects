package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 角色,模块,操作:视图
 * 
 * @author zhouweixin
 *
 */
@Entity
@Table(name = "role_model_operation")
@IdClass(RoleModelOperationPrimaryKey.class)
public class RoleModelOperationView {
	/** 角色编码 */
	@Id
	private Integer roleCode;

	/** 模块编码 */
	@Id
	private Integer modelCode;

	/** 操作编码 */
	@Id
	private Integer operationCode;

	/** 操作类型 */
	private String operationName;

	/** 模块名称 */
	private String modelName;

	/** 角色名称 */
	private String roleName;

	/** 角色描述 */
	private String roleDescription;

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

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Override
	public String toString() {
		return "RoleModelOperation [roleCode=" + roleCode + ", modelCode=" + modelCode + ", operationCode="
				+ operationCode + ", operationName=" + operationName + ", modelName=" + modelName + ", roleName="
				+ roleName + ", roleDescription=" + roleDescription + "]";
	}

}
