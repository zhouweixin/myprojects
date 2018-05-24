package com.xplusplus.security.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author: zhouweixin
 * @Description: 项目收款记录表
 * @Date: Created in 上午10:11:00 2018年5月23日
 */
@Entity
public class ProjectReceipt {
	// 主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 项目编号
	@ManyToOne(targetEntity = Project.class)
	@JoinColumn(name = "project_id", referencedColumnName = "id")
	private Project project;

	// 收款金额
	@Column(precision = 2)
	private Double price;

	// 收款时间
	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date time;

	// 经办人
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "operator_id", referencedColumnName = "id")
	private User operator;

	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProjectReceipt [id=" + id + ", project=" + project + ", price=" + price + ", time=" + time
				+ ", operator=" + operator + ", description=" + description + "]";
	}

}
