package com.xplusplus.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @Author: zhouweixin
 * @Description: 项目员工关系表
 * @Date: Created in 上午12:30:48 2018年5月27日
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "project_id"})})
public class ProjectUser {
	// 主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 用户
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	// 项目
	@ManyToOne(targetEntity = Project.class)
	@JoinColumn(name = "project_id", referencedColumnName = "id")
	private Project project;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "ProjectUser [id=" + id + ", user=" + user + ", project=" + project + "]";
	}

}
