package com.hnu.mes.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.Valid;

/**
 * @Author: zhouweixin
 * @Description: 第三方发货单表
 * @Date: Created in 14:00 2018/5/1
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_send_entry")
public class SendEntry {

    // 编码
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    // 发货单表头
    @ManyToOne
    @JoinColumn(name = "send_header_code", referencedColumnName = "code")
    private SendEntryHeader sendEntryHeader;

    // 批号
    private String batchNumber;

    // 单位:kg
    private String unit;

    // 重量
    @Column(precision = 2)
    private Double weight;

    // 状态:0未收;1已收
    private Integer status;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @JsonIgnore
    public SendEntryHeader getSendEntryHeader() {
        return sendEntryHeader;
    }

    public void setSendEntryHeader(SendEntryHeader sendEntryHeader) {
        this.sendEntryHeader = sendEntryHeader;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
