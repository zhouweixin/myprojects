package com.xplusplus.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @Author: zhouweixin
 * @Description: 考勤组与负责人关系表
 * @Date: Created in 下午3:17:49 2018年5月27日
 */
@Entity
public class AttendanceGroupLeader {
	// 主键: 自增长
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 考勤组
	@ManyToOne(targetEntity = AttendanceGroup.class)
	@JoinColumn(name = "attendance_group_id", referencedColumnName = "id")
	private AttendanceGroup attendanceGroup;

	// 负责人
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "leader_id", referencedColumnName = "id")
	private User leader;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AttendanceGroup getAttendanceGroup() {
		return attendanceGroup;
	}

	public void setAttendanceGroup(AttendanceGroup attendanceGroup) {
		this.attendanceGroup = attendanceGroup;
	}

	public User getLeader() {
		return leader;
	}

	public void setLeader(User leader) {
		this.leader = leader;
	}

	@Override
	public String toString() {
		return "AttendanceGroupLeader [id=" + id + ", attendanceGroup=" + attendanceGroup + ", leader=" + leader + "]";
	}

}
