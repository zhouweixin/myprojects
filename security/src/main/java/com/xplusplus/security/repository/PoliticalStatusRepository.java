package com.xplusplus.security.repository;

import com.xplusplus.security.domain.Nation;
import com.xplusplus.security.domain.PoliticalStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Huxudong
 * @Description:
 * @Date: Created in 12:31 2018/5/22
 * @Modified By:
 */
@Repository
public interface PoliticalStatusRepository extends JpaRepository<PoliticalStatus, Integer> {
    /**
     * 通过名称模糊查询
     */
    public List<PoliticalStatus> findByNameLike(String name);


    /**
     * 通过名称模糊查询-分页
     */
    public Page<PoliticalStatus> findByNameLike(String name, Pageable pageable);
}
