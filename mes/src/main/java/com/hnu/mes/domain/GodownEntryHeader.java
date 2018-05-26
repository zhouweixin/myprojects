package com.hnu.mes.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 19:03 2018/5/1
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_godown_entry_header")
public class GodownEntryHeader {
    // 编码
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    // 入库单编号
    private String batchNumber;

    // 发货单编号
    @OneToOne(targetEntity = SendEntryHeader.class)
    @JoinColumn(name = "send_entry_header_code", referencedColumnName = "code")
    private SendEntryHeader sendEntryHeader;

    // 发货厂家
    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name = "supplier_code", referencedColumnName = "code")
    private Supplier supplier;

    // 原料类型
    @ManyToOne(targetEntity = RawType.class)
    @JoinColumn(name = "raw_type_code", referencedColumnName = "code")
    private RawType rawType;

    // 数据库存kg值
    @Column(precision = 2)
    private Double weight;

    // 到货日期
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    // 到货制单时间
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    // 入库单制单人工号
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "create_user_code", referencedColumnName = "code")
    private User createUser;

    // 状态：0样品未入库；1样品已入库
    private Integer status;

    // 入库单
    @OneToMany(targetEntity = GodownEntry.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "godown_entry_header_code")
    private List<GodownEntry> godownEntries = new ArrayList<GodownEntry>();

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

    public SendEntryHeader getSendEntryHeader() {
        return sendEntryHeader;
    }

    public void setSendEntryHeader(SendEntryHeader sendEntryHeader) {
        this.sendEntryHeader = sendEntryHeader;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<GodownEntry> getGodownEntries() {
        return godownEntries;
    }

    public void setGodownEntries(List<GodownEntry> godownEntries) {
        this.godownEntries = godownEntries;
    }

    @Override
    public String toString() {
        return "GodownEntryHeader{" +
                "code=" + code +
                ", batchNumber='" + batchNumber + '\'' +
                ", sendEntryHeader=" + sendEntryHeader +
                ", supplier=" + supplier +
                ", rawType=" + rawType +
                ", weight=" + weight +
                ", date=" + date +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", status=" + status +
                ", godownEntries=" + godownEntries +
                '}';
    }
}
