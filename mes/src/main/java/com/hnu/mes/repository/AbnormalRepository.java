package com.hnu.mes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hnu.mes.domain.Abnormal;

/**
 *
 * @author zhouweixin
 *
 */
public interface AbnormalRepository extends JpaRepository<Abnormal, String> {
    /**
     * 通过名称模糊查询-分页
     * @param name
     * @param pageable
     * @return
     */
    public Page<Abnormal> findByNameLike(String name, Pageable pageable);
}
