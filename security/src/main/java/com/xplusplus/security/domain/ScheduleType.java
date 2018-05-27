package com.xplusplus.security.domain;

import java.time.Period;
import java.util.Date;

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
 * @Description: 班次类型
 * @Date: Created in 下午2:02:17 2018年5月27日
 */
@Entity
public class ScheduleType {
	// 主键: 自增长
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 名称
	private String name;

	// 开始时间
	@Temporal(value = TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date startTime;

	// 休息开始时间
	@Temporal(value = TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date startBreakTime;

	// 休息结束时间
	@Temporal(value = TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date endBreakTime;

	// 结束时间
	@Temporal(value = TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date endTime;

	// 多少分钟前开始打卡
	private Integer beforeMinute;

	// 多少分钟后结束打卡
	private Integer afterMinute;

	// 班次
	@ManyToOne(targetEntity = Schedule.class)
	@JoinColumn(name = "schedule_id", referencedColumnName = "id")
	private Schedule schedule;

	// 休息时间
	private Period breakPeriod;

	// 工作时间
	private Period workPeriod;

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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getBeforeMinute() {
		return beforeMinute;
	}

	public void setBeforeMinute(Integer beforeMinute) {
		this.beforeMinute = beforeMinute;
	}

	public Integer getAfterMinute() {
		return afterMinute;
	}

	public void setAfterMinute(Integer afterMinute) {
		this.afterMinute = afterMinute;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Date getStartBreakTime() {
		return startBreakTime;
	}

	public void setStartBreakTime(Date startBreakTime) {
		this.startBreakTime = startBreakTime;
	}

	public Date getEndBreakTime() {
		return endBreakTime;
	}

	public void setEndBreakTime(Date endBreakTime) {
		this.endBreakTime = endBreakTime;
	}

	public Period getBreakPeriod() {
		return breakPeriod;
	}

	public void setBreakPeriod(Period breakPeriod) {
		this.breakPeriod = breakPeriod;
	}

	public Period getWorkPeriod() {
		return workPeriod;
	}

	public void setWorkPeriod(Period workPeriod) {
		this.workPeriod = workPeriod;
	}

	@Override
	public String toString() {
		return "ScheduleType [id=" + id + ", name=" + name + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", beforeMinute=" + beforeMinute + ", afterMinute=" + afterMinute + ", schedule=" + schedule
				+ ", startBreakTime=" + startBreakTime + ", endBreakTime=" + endBreakTime + ", breakPeriod="
				+ breakPeriod + ", workPeriod=" + workPeriod + "]";
	}

}
