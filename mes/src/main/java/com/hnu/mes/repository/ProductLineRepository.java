package com.hnu.mes.repository;

import com.hnu.mes.domain.ProductLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by lanyage on 2018/3/20.
 */
public interface ProductLineRepository extends JpaRepository<ProductLine, String>, JpaSpecificationExecutor {
    ProductLine findByName(String name);

    Page<ProductLine> findByNameLike(String name, Pageable pageable);
}
