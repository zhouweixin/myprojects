package com.xplusplus.security.repository;

import com.xplusplus.security.domain.AttendanceGroup;
import com.xplusplus.security.domain.AttendanceGroupLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author：XudongHu
 * @description: 不解释
 * @date:21:43 2018/5/30
 */
@Repository
public interface AttendanceGroupLeaderRepository extends JpaRepository<AttendanceGroupLeader,Integer > {
    /**
     * 通过考勤组删除
     */
     void deleteByAttendanceGroup(AttendanceGroup attendanceGroup);
    /**
     * 通过考勤组查找
     */
    List<AttendanceGroupLeader> findByAttendanceGroup(AttendanceGroup attendanceGroup);
}
