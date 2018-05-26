package com.hnu.mes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hnu.mes.domain.Process;

/**
 * @author: zhengyibin
 */
public interface ProcessRepository extends JpaRepository<Process, Integer> {
    /**
     * 通过名称模糊查询-分页
     * @param name
     * @param pageable
     * @return
     */
    public Page<Process> findByNameLike(String name, Pageable pageable);
}
