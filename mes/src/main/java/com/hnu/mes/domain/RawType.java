package com.hnu.mes.domain;

import javax.persistence.*;

/**
 * @Author: zhouweixin
 * @Description: 原料名称表
 * @Date: Created in 8:54 2018/5/2
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_raw_type")
public class RawType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    //  原料类型名称
    private String name;

    /**
     * 物料类型表
     */
    @ManyToOne(targetEntity = Material.class)
    @JoinColumn(name = "material_code", referencedColumnName = "code")
    private Material material;

    // 数据表名
    private String dataTableName;

    // 库存上限
    @Column(precision = 2)
    private Double stockUpper;

    //  库存下限
    @Column(precision = 2)
    private Double stockBottom;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataTableName() {
        return dataTableName;
    }

    public void setDataTableName(String dataTableName) {
        this.dataTableName = dataTableName;
    }

    public Double getStockUpper() {
        return stockUpper;
    }

    public void setStockUpper(Double stockUpper) {
        this.stockUpper = stockUpper;
    }

    public Double getStockBottom() {
        return stockBottom;
    }

    public void setStockBottom(Double stockBottom) {
        this.stockBottom = stockBottom;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "RawType{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", material=" + material +
                ", dataTableName='" + dataTableName + '\'' +
                ", stockUpper=" + stockUpper +
                ", stockBottom=" + stockBottom +
                '}';
    }
}
