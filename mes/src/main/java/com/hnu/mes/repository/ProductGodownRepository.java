package com.hnu.mes.repository;

import com.hnu.mes.domain.ProductGodown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 16:26 2018/5/10
 * @Modified By:
 */
@Repository
public interface ProductGodownRepository extends JpaRepository<ProductGodown, Long> {
}
