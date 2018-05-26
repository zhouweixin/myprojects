package com.hnu.mes.controller;

import com.hnu.mes.domain.ProductSendAudit;
import com.hnu.mes.domain.ProductSendHeader;
import com.hnu.mes.domain.Result;
import com.hnu.mes.service.ProductSendAuditService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 17:26 2018/5/25
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/productSendAudit")
public class ProductSendAuditController {
    @Autowired
    private ProductSendAuditService productSendAuditService;

    /**
     * 通过产品出库单查询
     *
     * @param productSendHeaderCode
     * @return
     */
    @RequestMapping(value = "/getByProductSendHeader")
    public Result<List<ProductSendAudit>> getByProductSendHeader(Long productSendHeaderCode){
        ProductSendHeader productSendHeader = new ProductSendHeader();
        productSendHeader.setCode(productSendHeaderCode);
        return ResultUtil.success(productSendAuditService.findByProductSendHeader(productSendHeader));
    }
}
