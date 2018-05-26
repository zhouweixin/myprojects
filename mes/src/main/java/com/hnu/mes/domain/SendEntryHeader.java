package com.hnu.mes.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description: 第三方发货单表头
 * @Date: Created in 14:01 2018/5/1
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_send_entry_header")
public class SendEntryHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    // 合同号
    private String contractNumber;

    // 第三方发货单
    @OneToMany(targetEntity = SendEntry.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "send_header_code")
    private List<SendEntry> sendEntries = new ArrayList<SendEntry>();

    // 发货人厂家
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_code", referencedColumnName = "code")
    private Supplier supplier;

    // 发货人
    @ManyToOne
    @JoinColumn(name = "sender_code", referencedColumnName = "code")
    private Customer sender;

    // 发货日期
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sendDate;

    // 联系电话
    private String contact;

    // 物料名称
    private String name;

    // 总重量
    @Column(precision = 2)
    private Double weight;

    // 状态:0待收货;1已发货;2已收货
    private Integer status;

    // 原料类型
    @ManyToOne(targetEntity = RawType.class)
    @JoinColumn(name = "raw_type_code", referencedColumnName = "code")
    private RawType rawType;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public List<SendEntry> getSendEntries() {
        return sendEntries;
    }

    public void setSendEntries(List<SendEntry> sendEntries) {
        this.sendEntries = sendEntries;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public RawType getRawType() {
        return rawType;
    }

    public void setRawType(RawType rawType) {
        this.rawType = rawType;
    }

    @Override
    public String toString() {
        return "SendEntryHeader{" +
                "code=" + code +
                ", contractNumber='" + contractNumber + '\'' +
                ", sendEntries=" + sendEntries +
                ", supplier=" + supplier +
                ", sender=" + sender +
                ", sendDate=" + sendDate +
                ", contact='" + contact + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", status=" + status +
                ", rawType=" + rawType +
                '}';
    }
}
