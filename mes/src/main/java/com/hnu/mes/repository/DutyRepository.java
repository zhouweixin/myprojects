package com.hnu.mes.repository;

import com.hnu.mes.domain.Duty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhouweixin on 2018/3/22.
 */
public interface DutyRepository extends JpaRepository<Duty, Integer> {
    /**
     * 通过名称模糊查询-分页
     * @param name
     * @param pageable
     * @return
     */
    public Page<Duty> findByNameLike(String name, Pageable pageable);
}
