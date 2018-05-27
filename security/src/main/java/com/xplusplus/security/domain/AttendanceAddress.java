package com.xplusplus.security.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @Author: zhouweixin
 * @Description: 考勤地址表
 * @Date: Created in 下午3:21:34 2018年5月27日
 */
@Entity
public class AttendanceAddress {
	// 主键: 自增长
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 名称
	private String name;

	// 详细地址
	private String detailName;

	// 有效范围(米)
	@Column(precision = 2)
	private Double domainRadius;

	// 考勤组
	@ManyToOne(targetEntity = AttendanceGroup.class)
	@JoinColumn(name = "attendance_group_id", referencedColumnName = "id")
	private AttendanceGroup attendanceGroup;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getDomainRadius() {
		return domainRadius;
	}

	public void setDomainRadius(Double domainRadius) {
		this.domainRadius = domainRadius;
	}

	public AttendanceGroup getAttendanceGroup() {
		return attendanceGroup;
	}

	public void setAttendanceGroup(AttendanceGroup attendanceGroup) {
		this.attendanceGroup = attendanceGroup;
	}

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	@Override
	public String toString() {
		return "AttendanceAddress [id=" + id + ", name=" + name + ", detailName=" + detailName + ", domainRadius="
				+ domainRadius + ", attendanceGroup=" + attendanceGroup + "]";
	}

}
