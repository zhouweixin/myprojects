package com.hnu.mes.service;

import com.hnu.mes.domain.ProductSendAudit;
import com.hnu.mes.domain.ProductSendHeader;
import com.hnu.mes.repository.ProductSendAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 17:25 2018/5/25
 * @Modified By:
 */
@Service
public class ProductSendAuditService {
    @Autowired
    private ProductSendAuditRepository productSendAuditRepository;

    /**
     * 新增
     *
     * @param productSendAudit
     * @return
     */
    public ProductSendAudit save(ProductSendAudit productSendAudit){
        return productSendAuditRepository.save(productSendAudit);
    }

    /**
     * 通过产品出库单查询
     *
     * @param productSendHeader
     * @return
     */
    public List<ProductSendAudit> findByProductSendHeader(ProductSendHeader productSendHeader){
        return productSendAuditRepository.findByProductSendHeader(productSendHeader);
    }
}
