package com.xplusplus.security.repository;

import com.xplusplus.security.domain.AttendanceGroup;
import com.xplusplus.security.domain.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author：XudongHu
 * @description: 考勤组Repository
 * @date:15:28 2018/5/29
 */
@Repository
public interface AttendanceGroupRepository  extends JpaRepository<AttendanceGroup,Integer> {
    /**
     * 通过班次查询
     * @param schedule
     * @return
     */
    AttendanceGroup findFirstBySchedule(Schedule schedule);
    /**
     * 通过名称分页模糊查询
     */
    Page<AttendanceGroup> findByNameLike(String name, Pageable pageable);

}
