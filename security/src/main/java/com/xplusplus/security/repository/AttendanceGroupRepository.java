package com.xplusplus.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xplusplus.security.domain.AttendanceGroup;
import com.xplusplus.security.domain.Schedule;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午3:44:29 2018年5月31日
 */
@Repository
public interface AttendanceGroupRepository extends JpaRepository<AttendanceGroup, Integer>{

	public AttendanceGroup findFirstBySchedule(Schedule schedule);

}
