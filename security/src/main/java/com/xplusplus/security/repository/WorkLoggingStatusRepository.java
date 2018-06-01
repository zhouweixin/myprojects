package com.xplusplus.security.repository;

import com.xplusplus.security.domain.WorkLoggingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 21:34 2018/6/1
 * @Modified By:
 */
@Repository
public interface WorkLoggingStatusRepository extends JpaRepository<WorkLoggingStatus, Integer> {
    /**
     * 通过名称模糊查询-分页
     *
     * @param name
     * @param pageable
     * @return
     */
    public Page<WorkLoggingStatus> findByNameLike(String name, Pageable pageable);
}
