package com.xplusplus.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @Author: zhouweixin
 * @Description: 班次迟到类型关系表
 * @Date: Created in 下午2:16:23 2018年5月27日
 */
@Entity
public class ScheduleLateType {
	// 主键: 自增长
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 班次
	@ManyToOne(targetEntity = Schedule.class)
	@JoinColumn(name = "schedule_id", referencedColumnName = "id")
	private Schedule schedule;

	// 迟到类型
	@ManyToOne(targetEntity = LateType.class)
	@JoinColumn(name = "late_type_id", referencedColumnName = "id")
	private LateType lateType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public LateType getLateType() {
		return lateType;
	}

	public void setLateType(LateType lateType) {
		this.lateType = lateType;
	}

	@Override
	public String toString() {
		return "ScheduleLateType [id=" + id + ", schedule=" + schedule + ", lateType=" + lateType + "]";
	}

}
