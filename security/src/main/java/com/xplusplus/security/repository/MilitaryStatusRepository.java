package com.xplusplus.security.repository;

import com.xplusplus.security.domain.MilitaryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Huxudong
 * @Description: 兵役状况Repository
 * @Date: Created in 12:29 2018/5/22
 * @Modified By:
 */
@Repository
public interface MilitaryStatusRepository extends JpaRepository<MilitaryStatus, Integer> {
    /**
     * 通过名称模糊查询-分页
     */
    public Page<MilitaryStatus> findByNameLike(String name, Pageable pageable);
}
