package com.hnu.mes.domain;

import javax.persistence.*;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:00 2018/5/9
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_materials_entry")
public class MaterialsEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    /**
     * 原料名称
     */
    @ManyToOne(targetEntity = RawType.class)
    @JoinColumn(name = "raw_type_code", referencedColumnName = "code")
    private RawType rawType;

    /**
     * 批号
     */
    private String batchNumber;

    /**
     * 重量
     */
    private Double weight;

    /**
     * 状态
     */
    private Integer status;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "MaterialsEntry{" +
                "code=" + code +
                ", rawType=" + rawType +
                ", batchNumber='" + batchNumber + '\'' +
                ", weight=" + weight +
                ", status=" + status +
                '}';
    }
}
