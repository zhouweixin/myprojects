package com.xplusplus.security.repository;

import com.xplusplus.security.domain.AttendanceGroup;
import com.xplusplus.security.domain.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author：XudongHu
 * @description: 班次Repository
 * @date:20:28 2018/5/28
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
    /**
     * 通过名称模糊查询-分页
     */
    public Page<Schedule> findByNameLike(String name, Pageable pageable);

}
