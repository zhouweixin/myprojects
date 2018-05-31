package com.hnu.mes.domain;

import javax.persistence.*;

/**
 * @Author: zhouweixin
 * @Description: 库存表
 * @Date: Created in 20:04 2018/5/9
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_materials_total")
public class MaterialsTotal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    /**
     * 原料类型
     */
    @ManyToOne(targetEntity = RawType.class)
    @JoinColumn(name = "raw_type_code", referencedColumnName = "code")
    private RawType rawType;

    /**
     * 重量
     */
    private Double weight;

    /**
     * 状态：0正常；1开始盘库；2待审核；3已审核
     */
    private Integer status;

    /**
     * 警告状态:0正常;1高于阈值;2低于阈值
     */
    private Integer warnStatus;

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

    public Integer getWarnStatus() {
        return warnStatus;
    }

    public void setWarnStatus(Integer warnStatus) {
        this.warnStatus = warnStatus;
    }

    @Override
    public String toString() {
        return "MaterialsTotal{" +
                "code=" + code +
                ", rawType=" + rawType +
                ", weight=" + weight +
                ", status=" + status +
                ", warnStatus=" + warnStatus +
                '}';
    }
}
