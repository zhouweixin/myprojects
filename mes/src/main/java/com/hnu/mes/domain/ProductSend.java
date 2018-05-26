package com.hnu.mes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @Author: zhouweixin
 * @Description: 成品入库单
 * @Date: Created in 19:00 2018/5/17
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_product_send")
public class ProductSend {
    /**
     * 主键, 自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    /**
     * 批号
     */
    private String batchNumber;

    /**
     * 单位:kg
     */
    private String unit;

    /**
     * 重量
     */
    @Column(precision = 2)
    private Double weight;

    /**
     * 表头
     */
    @ManyToOne(targetEntity = ProductSendHeader.class)
    @JoinColumn(name = "product_send_header_code", referencedColumnName = "code")
    private ProductSendHeader productSendHeader;

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @JsonIgnore
    public ProductSendHeader getProductSendHeader() {
        return productSendHeader;
    }

    public void setProductSendHeader(ProductSendHeader productSendHeader) {
        this.productSendHeader = productSendHeader;
    }
}
