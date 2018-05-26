package com.hnu.mes.repository;

import com.hnu.mes.domain.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by lanyage on 2018/3/15.
 */
public interface SupplierDao extends JpaRepository<Supplier, String>, JpaSpecificationExecutor {
    Supplier findByName(String name);

    public Page<Supplier> findByNameLike(String s, Pageable pageable);
}
