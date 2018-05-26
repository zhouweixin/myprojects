package com.hnu.mes.domain;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 角色
 * 
 * @author zhouweixin
 *
 */
@Entity
@Table(name = "permission_role")
public class Role {
	/** 编码 */
	@Id
	@NotNull(message = "角色编码不能为空")
    private Integer code;

	/** 名称 */
	@NotBlank(message = "角色名称不能为空")
	private String name;

	/**
	 * 标志:0表示系统创建,不允许删除
	 */
	private Integer flag = 1;

	/**
	 * 模块
	 */
	@ManyToMany(targetEntity = Model.class, fetch = FetchType.EAGER)
	@JoinTable(name = "PermissionRoleModelOperation", joinColumns = {
			@JoinColumn(name = "RoleCode", referencedColumnName = "code") }, inverseJoinColumns = {
					@JoinColumn(name = "ModelCode", referencedColumnName = "code") })
	private Set<Model> models;

	/** 描述 */
	private String description;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Model> getModels() {
		return models;
	}

	public void setModels(Set<Model> models) {
		this.models = models;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Role{" +
				"code=" + code +
				", name='" + name + '\'' +
				", flag=" + flag +
				", models=" + models +
				", description='" + description + '\'' +
				'}';
	}
}
