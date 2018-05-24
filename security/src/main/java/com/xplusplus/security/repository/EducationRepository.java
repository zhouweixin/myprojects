package com.xplusplus.security.repository;

import com.xplusplus.security.domain.Education;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liweifeng
 * @Description:
 * @Date: Created in 12:26 2018/5/22
 * @Modified By:
 */
@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {

    /**
     * 通过名称模糊查询
     * @param name
     * @param pageable
     * @return
     */
    public Page<Education> findByNameLike(String name, Pageable pageable);
}
