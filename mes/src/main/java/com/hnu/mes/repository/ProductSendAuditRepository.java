package com.hnu.mes.repository;

import com.hnu.mes.domain.ProductSendAudit;
import com.hnu.mes.domain.ProductSendHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 17:25 2018/5/25
 * @Modified By:
 */
@Repository
public interface ProductSendAuditRepository extends JpaRepository<ProductSendAudit, Long> {
    /**
     * 通过产品出库单查询
     *
     * @param productSendHeader
     * @return
     */
    public List<ProductSendAudit> findByProductSendHeader(ProductSendHeader productSendHeader);
}
