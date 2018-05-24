package com.xplusplus.security.repository;

import com.xplusplus.security.domain.MaritalStatus;
import com.xplusplus.security.domain.Nation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Huxudong
 * @Description: 婚姻状况Repository
 * @Date: Created in 12:28 2018/5/22
 * @Modified By:
 */
@Repository
public interface MaritalStatusRepository extends JpaRepository<MaritalStatus, Integer> {
    /**
     * 通过名称模糊查询
     */
    public List<MaritalStatus> findByNameLike(String name);

    /**
     * 通过名称模糊查询-分页
     */
    public Page<MaritalStatus> findByNameLike(String name, Pageable pageable);
}
