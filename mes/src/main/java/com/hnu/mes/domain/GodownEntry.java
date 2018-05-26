package com.hnu.mes.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;

/**
 * @Author: zhouweixin
 * @Description: 入库单
 * @Date: Created in 19:22 2018/5/1
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_godown_entry")
public class GodownEntry {
    // 编码
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    // 表头
    @ManyToOne(targetEntity = GodownEntryHeader.class)
    @JoinColumn(name = "godown_entry_header_code", referencedColumnName = "code")
    private GodownEntryHeader godownEntryHeader;

    // 收货物料批号
    private String batchNumber;

    // 单位(KG)
    private String unit;

    // 重量
    @Column(precision = 2)
    private Double weight;

    // 化验结果:0未化验; 1合格; 2不合格
    private Integer testResult;

    public GodownEntry(){}

    public GodownEntry(String batchNumber, String unit, Double weight) {
        this.batchNumber = batchNumber;
        this.unit = unit;
        this.weight = weight;
        this.testResult = 0;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @JsonIgnore
    public GodownEntryHeader getGodownEntryHeader() {
        return godownEntryHeader;
    }

    public void setGodownEntryHeader(GodownEntryHeader godownEntryHeader) {
        this.godownEntryHeader = godownEntryHeader;
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

    public Integer getTestResult() {
        return testResult;
    }

    public void setTestResult(Integer testResult) {
        this.testResult = testResult;
    }
}
