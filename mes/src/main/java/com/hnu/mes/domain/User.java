package com.hnu.mes.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户
 * 
 * @author zhouweixin
 *
 */
@Entity
@Table(name = "permission_user")
public class User {
	/**
	 * 工号
	 */
	@Id
	@NotBlank(message = "用户工号不能为空")
	private String code = "";
	/**
	 * 姓名
	 */
	@NotBlank(message = "用户名不能为空")
	private String name = "";
	/**
	 * 密码
	 */
	private String password = "";
	/**
	 * IC卡
	 */
	private String inteCircCard = "";
	/**
	 * 描述
	 */
	private String description = "";
	/**
	 * 联系方式
	 */
	private String contact = "";
	/**
	 * 是否启用
	 */
	@Column(columnDefinition = "default 1")
	private Integer enable = 1;
	/**
	 * 是否启用IC卡登录
	 */
	@Column(columnDefinition = "default 0")
	private Integer enableIc = 0;

	/**
	 * 部门
	 */
	@ManyToOne(targetEntity = Department.class)
	@JoinColumn(name = "department_code")
	private Department department;

	/**
	 * 角色
	 */
	@ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
	@JoinTable(name = "PermissionUserRole", joinColumns = {@JoinColumn(name = "UserCode", referencedColumnName = "code") }, 
							inverseJoinColumns = {@JoinColumn(name = "RoleCode", referencedColumnName = "code") })
	private Set<Role> roles;	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInteCircCard() {
		return inteCircCard;
	}

	public void setInteCircCard(String inteCircCard) {
		this.inteCircCard = inteCircCard;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getEnableIc() {
		return enableIc;
	}

	public void setEnableIc(Integer enableIc) {
		this.enableIc = enableIc;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [code=" + code + ", name=" + name + ", password=" + password + ", inteCircCard=" + inteCircCard
				+ ", description=" + description + ", contact=" + contact + ", enable=" + enable + ", enableIc="
				+ enableIc + ", department=" + department + ", roles=" + roles + "]";
	}

}
