package com.hnu.mes.domain;

import javax.persistence.*;

/**
 * @Author: zhouweixin
 * @Description:
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

    @Override
    public String toString() {
        return "MaterialsTotalService{" +
                "code=" + code +
                ", rawType=" + rawType +
                ", weight=" + weight +
                '}';
    }
}
