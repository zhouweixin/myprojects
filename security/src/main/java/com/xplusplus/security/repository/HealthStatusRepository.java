package com.xplusplus.security.repository;

import com.xplusplus.security.domain.HealthStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:27 2018/5/22
 * @Modified By:
 */
@Repository
public interface HealthStatusRepository extends JpaRepository<HealthStatus, Integer> {

    /**
     * 通过名称模糊查询
     * @param name
     * @param pageable
     * @return
     */
    public Page<HealthStatus> findByNameLike(String name, Pageable pageable);
}
