package com.hnu.mes.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lanyage on 2018/3/14.
 */
@Entity
@Table(name = "basicinfo_supplymanage")
public class SupplyInfo implements Serializable{
    /**
     * 编码
     */
    @Id
    private String code;

    /**
     * 合同号
     */
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},
    fetch = FetchType.EAGER)
    @JoinColumn(name = "header_code")
    private SupplyInfoHeader header;

    /**
     * 批号
     */
    private String batchNumber;

    /**
     * 名称
     */
    private String name;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 数量
     */
    private int number;

    /**
     * 产品
     */
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},
        fetch = FetchType.EAGER)
    @JoinColumn(name = "product_code")
    private BasicInfoProduct product;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SupplyInfoHeader getHeader() {
        return header;
    }

    public void setHeader(SupplyInfoHeader header) {
        this.header = header;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BasicInfoProduct getProduct() {
        return product;
    }

    public void setProduct(BasicInfoProduct product) {
        this.product = product;
    }
}
