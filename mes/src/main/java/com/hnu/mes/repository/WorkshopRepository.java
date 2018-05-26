package com.hnu.mes.repository;

import com.hnu.mes.domain.Workshop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zhengyibin
 * @Date: 2018/3/17
 * @Description: 车间dao
 */
public interface WorkshopRepository extends JpaRepository<Workshop, String> {
    /**
     * 通过名称模糊查询-分页
     *
     * @param name
     * @param pageable
     * @return
     */
    public Page<Workshop> findAllByNameLike(String name, Pageable pageable);
}
