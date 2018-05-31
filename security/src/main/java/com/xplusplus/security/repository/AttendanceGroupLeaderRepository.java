package com.xplusplus.security.repository;

import com.xplusplus.security.domain.AttendanceGroup;
import com.xplusplus.security.domain.AttendanceGroupLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author：XudongHu
 * @description: 不解释
 * @date:21:43 2018/5/30
 */
@Repository
public interface AttendanceGroupLeaderRepository extends JpaRepository<AttendanceGroupLeader,Integer > {
    /**
     * 通过外键考勤组删除
     */
    AttendanceGroupLeader deleteByAttendanceGroup(AttendanceGroup attendanceGroup);
}
