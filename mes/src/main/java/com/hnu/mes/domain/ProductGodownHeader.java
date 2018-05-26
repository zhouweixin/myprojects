package com.hnu.mes.domain;

import com.hnu.mes.utils.GlobalUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zhouweixin
 * @Description: 产品入库单表头
 * @Date: Created in 16:12 2018/5/10
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_product_godown_header")
public class ProductGodownHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    /**
     * 入库单编号:7位序号
     */
    private String batchNumber;

    // 产品型号
    private String model;

    /**
     * 制造部门
     */
    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "department_code", referencedColumnName = "code")
    private Department department;

    /**
     * 重量
     */
    @Column(precision = 2)
    private Double weight;

    // 状态:0待入库;1已入库
    private Integer status = GlobalUtil.ProductGodownStatus.PRE_GODOWN;

    // 缴库时间
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    // 缴库人
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "payer_code", referencedColumnName = "code")
    private User payer;

    // 入库时间
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date godownTime;

    // 入库人
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "godowner_code", referencedColumnName = "code")
    private User godowner;

    // 产品入库表
    @OneToMany(targetEntity = ProductGodown.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_godown_header_code")
    private Set<ProductGodown> productGodowns = new HashSet<>();

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public Date getGodownTime() {
        return godownTime;
    }

    public void setGodownTime(Date godownTime) {
        this.godownTime = godownTime;
    }

    public User getGodowner() {
        return godowner;
    }

    public void setGodowner(User godowner) {
        this.godowner = godowner;
    }

    public Set<ProductGodown> getProductGodowns() {
        return productGodowns;
    }

    public void setProductGodowns(Set<ProductGodown> productGodowns) {
        this.productGodowns = productGodowns;
    }

    @Override
    public String toString() {
        return "ProductGodownHeader{" +
                "code=" + code +
                ", batchNumber='" + batchNumber + '\'' +
                ", model='" + model + '\'' +
                ", department=" + department +
                ", weight=" + weight +
                ", status=" + status +
                ", payTime=" + payTime +
                ", payer=" + payer +
                ", godownTime=" + godownTime +
                ", godowner=" + godowner +
                ", productGodowns=" + productGodowns +
                '}';
    }
}
