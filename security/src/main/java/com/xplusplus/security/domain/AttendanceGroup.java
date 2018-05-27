package com.xplusplus.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @Author: zhouweixin
 * @Description: 考勤组管理
 * @Date: Created in 下午3:10:15 2018年5月27日
 */
@Entity
public class AttendanceGroup {
	// 主键: 自增长
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 名称
	private String name;

	// 班次
	@ManyToOne(targetEntity = Schedule.class)
	@JoinColumn(name = "schedule_id", referencedColumnName = "id")
	private Schedule schedule;

	// 允许外勤打卡
	private Boolean enableOut;

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

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Boolean getEnableOut() {
		return enableOut;
	}

	public void setEnableOut(Boolean enableOut) {
		this.enableOut = enableOut;
	}

	@Override
	public String toString() {
		return "AttendanceGroup [id=" + id + ", name=" + name + ", schedule=" + schedule + ", enableOut=" + enableOut
				+ "]";
	}

}
