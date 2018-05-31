package com.hnu.mes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hnu.mes.utils.GlobalUtil;

import javax.persistence.*;

/**
 * @Author: zhouweixin
 * @Description:  产品入库单
 * @Date: Created in 16:13 2018/5/10
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_product_godown")
public class ProductGodown {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    /**
     * 批号
     */
    private String batchNumber;

    /**
     * 重量
     */
    @Column(precision = 2)
    private Double weight;

    /**
     * 状态：0待入库；1已入库
     */
    private Integer status = GlobalUtil.ProductGodownStatus.PRE_GODOWN;

    /**
     * 表头
     */
    @ManyToOne(targetEntity = ProductGodownHeader.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_godown_header_code", referencedColumnName = "code")
    private ProductGodownHeader productGodownHeader;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonIgnore
    public ProductGodownHeader getProductGodownHeader() {
        return productGodownHeader;
    }

    public void setProductGodownHeader(ProductGodownHeader productGodownHeader) {
        this.productGodownHeader = productGodownHeader;
    }
}
