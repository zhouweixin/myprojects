package com.hnu.mes.repository;

import com.hnu.mes.domain.SupplierType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by lanyage on 2018/3/15.
 */
public interface SupplierTypeDao extends JpaRepository<SupplierType, Integer>, JpaSpecificationExecutor {
    SupplierType findByCode(Integer code);

    /**
     * 通过名称模糊查询-分页
     * @param type
     * @param pageable
     * @return
     */
    public Page<SupplierType> findSupplierTypesByTypeLike(String type, Pageable pageable);
}
