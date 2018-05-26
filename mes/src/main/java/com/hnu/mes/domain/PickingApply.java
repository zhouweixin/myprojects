package com.hnu.mes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @Author: zhouweixin
 * @Description: 领料申请表
 * @Date: Created in 20:33 2018/5/2
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_picking_apply")
public class PickingApply {
    // 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    // 领料申请表头
    @ManyToOne(targetEntity = PickingApplyHeader.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "picking_apply_header_code", referencedColumnName = "code")
    private PickingApplyHeader pickingApplyHeader;

    // 原料类型
    @ManyToOne(targetEntity = RawType.class)
    @JoinColumn(name = "raw_type_code", referencedColumnName = "code")
    private RawType rawType;

    // 批号
    private String batchNumber;

    // 单位(KG)
    private String unit;

    // 重量
    @Column(precision = 2)
    private Double weight;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @JsonIgnore
    public PickingApplyHeader getPickingApplyHeader() {
        return pickingApplyHeader;
    }

    public void setPickingApplyHeader(PickingApplyHeader pickingApplyHeader) {
        this.pickingApplyHeader = pickingApplyHeader;
    }

    public RawType getRawType() {
        return rawType;
    }

    public void setRawType(RawType rawType) {
        this.rawType = rawType;
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
}
