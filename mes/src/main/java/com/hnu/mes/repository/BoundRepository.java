package com.hnu.mes.repository;

import com.hnu.mes.domain.Bound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhouweixin on 2018/3/22.
 */
public interface BoundRepository extends JpaRepository<Bound, String> {
    /**
     * 通过编码模糊查询-分页
     * @param code
     * @param pageable
     * @return
     */
    public Page<Bound> findByCodeLike(String code, Pageable pageable);
}
