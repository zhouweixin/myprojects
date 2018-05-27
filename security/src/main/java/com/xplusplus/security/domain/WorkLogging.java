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
 * @Description: 工作记录表
 * @Date: Created in 下午3:27:23 2018年5月27日
 */
@Entity
public class WorkLogging {
	// 主键: 自增长
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 用户
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	// 日期
	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	// 开始时间
	@Temporal(value = TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date startTime;

	// 结束时间
	@Temporal(value = TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date endTime;

	// 开始休息时间
	@Temporal(value = TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date startBreakTime;

	// 结束休息时间
	@Temporal(value = TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date endBreakTime;

	// 休息时长
	private Period breakPeriod;

	// 工作时长
	private Period workPeriod;

	// 迟到类型
	@ManyToOne(targetEntity = LateType.class)
	@JoinColumn(name = "late_type_id", referencedColumnName = "id")
	private LateType lateType;

	// 项目
	@ManyToOne(targetEntity = Project.class)
	@JoinColumn(name = "project_id", referencedColumnName = "id")
	private Project project;

	// 是否早退
	private Boolean leaveEarly;

	// 签到地址
	@ManyToOne(targetEntity = AttendanceAddress.class)
	@JoinColumn(name = "attendance_address_id", referencedColumnName = "id")
	private AttendanceAddress attendanceAddress;

	// 缺卡
	private Boolean noCard;

	// 工作记录状态
	@ManyToOne(targetEntity = Project.class)
	@JoinColumn(name = "project_id", referencedColumnName = "id")
	private WorkLoggingStatus status;

	// 备注
	private String note;

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

	public LateType getLateType() {
		return lateType;
	}

	public void setLateType(LateType lateType) {
		this.lateType = lateType;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Boolean getLeaveEarly() {
		return leaveEarly;
	}

	public void setLeaveEarly(Boolean leaveEarly) {
		this.leaveEarly = leaveEarly;
	}

	public AttendanceAddress getAttendanceAddress() {
		return attendanceAddress;
	}

	public void setAttendanceAddress(AttendanceAddress attendanceAddress) {
		this.attendanceAddress = attendanceAddress;
	}

	public Boolean getNoCard() {
		return noCard;
	}

	public void setNoCard(Boolean noCard) {
		this.noCard = noCard;
	}

	public WorkLoggingStatus getStatus() {
		return status;
	}

	public void setStatus(WorkLoggingStatus status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "WorkLogging [id=" + id + ", user=" + user + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", startBreakTime=" + startBreakTime + ", endBreakTime=" + endBreakTime + ", breakPeriod="
				+ breakPeriod + ", workPeriod=" + workPeriod + ", lateType=" + lateType + ", project=" + project
				+ ", leaveEarly=" + leaveEarly + ", attendanceAddress=" + attendanceAddress + ", noCard=" + noCard
				+ ", status=" + status + ", note=" + note + "]";
	}

}
