package com.xplusplus.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.time.Period;
import java.util.Date;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 19:50 2018/5/7
 * @Modified By:
 */
@Entity
public class User {
	/**
	 * 编号:部门首字母+队号+5位数字
	 */
	@Id
	private String id;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 性别:0男; 1女
	 */
	private Integer sex;

	/**
	 * ic号
	 */
	private String ic;

	/**
	 * 微信号
	 */
	private String wechat;

	/**
	 * 联系方式
	 */
	private String contact;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 部门
	 */
	@ManyToOne(targetEntity = Department.class)
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department department;

	/**
	 * 角色, 职位
	 */
	@ManyToOne(targetEntity = Role.class)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;

	/**
	 * 工作性质
	 */
	private String jobNature;

	/**
	 * 实习周期
	 */
	private Period period;

	/**
	 * 入职日期
	 */
	private Date employDate;

	/**
	 * 档案编号
	 */
	@OneToOne(targetEntity = Archive.class)
	@JoinColumn(name = "archive_id", referencedColumnName = "id")
	private Archive archive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getIc() {
		return ic;
	}

	public void setIc(String ic) {
		this.ic = ic;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getJobNature() {
		return jobNature;
	}

	public void setJobNature(String jobNature) {
		this.jobNature = jobNature;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public Date getEmployDate() {
		return employDate;
	}

	public void setEmployDate(Date employDate) {
		this.employDate = employDate;
	}

	public Archive getArchive() {
		return archive;
	}

	public void setArchive(Archive archive) {
		this.archive = archive;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sex=" + sex + ", ic=" + ic + ", wechat=" + wechat + ", contact="
				+ contact + ", password=" + password + ", department=" + department + ", role=" + role + ", jobNature="
				+ jobNature + ", period=" + period + ", employDate=" + employDate + ", archive=" + archive + "]";
	}

}
