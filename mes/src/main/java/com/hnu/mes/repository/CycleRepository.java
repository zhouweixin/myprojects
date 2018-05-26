package com.hnu.mes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hnu.mes.domain.Cycle;

/**
 *
 * @author zhouweixin
 *
 */
public interface CycleRepository extends JpaRepository<Cycle, String> {
    /**
     * 通过名称模糊查询-分页
     * @param name
     * @param pageable
     * @return
     */
    public Page<Cycle> findByNameLike(String name, Pageable pageable);
}
