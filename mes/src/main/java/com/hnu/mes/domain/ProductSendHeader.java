package com.hnu.mes.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zhouweixin
 * @Description: 成品入库单表头
 * @Date: Created in 18:52 2018/5/17
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_product_send_header")
public class ProductSendHeader {
    /**
     * 主键, 自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    /**
     * 编号:系统生成7位
     */
    private String number;

    /**
     * 产品型号
     */
    @ManyToOne(targetEntity = RawType.class)
    @JoinColumn(name = "raw_type_code", referencedColumnName = "code")
    private RawType rawType;

    /**
     * 运输方式
     */
    private String transportMode;

    /**
     * 总量
     */
    @Column(precision = 2)
    private Double weight;

    /**
     * 开单日期
     */
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    /**
     * 接收公司
     */
    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_code", referencedColumnName = "code")
    private Company company;

    /**
     * 流程
     */
    @ManyToOne(targetEntity = ProcessManage.class)
    @JoinColumn(name = "process_manage_code", referencedColumnName = "code")
    private ProcessManage processManage;

    /**
     * 审核状态:0未提交;1提交;2在审;3通过;4不通过
     */
    private Integer auditStatus;

    /**
     * 出库状态:0待出库;1已出库
     */
    private Integer outStatus = 0;

    /**
     * 出库人
     */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "sender_code", referencedColumnName = "code")
    private User sender;

    /**
     * 出库时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /**
     * 申请人
     */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "applicant_code", referencedColumnName = "code")
    private User applicant;

    /**
     * 申请时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;


    /**
     * 产品发货单
     */
    @OneToMany(targetEntity = ProductSend.class)
    @JoinColumn(name = "product_send_header_code")
    private Set<ProductSend> productSends = new HashSet<>();

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public RawType getRawType() {
        return rawType;
    }

    public void setRawType(RawType rawType) {
        this.rawType = rawType;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ProcessManage getProcessManage() {
        return processManage;
    }

    public void setProcessManage(ProcessManage processManage) {
        this.processManage = processManage;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getOutStatus() {
        return outStatus;
    }

    public void setOutStatus(Integer outStatus) {
        this.outStatus = outStatus;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Set<ProductSend> getProductSends() {
        return productSends;
    }

    public void setProductSends(Set<ProductSend> productSends) {
        this.productSends = productSends;
    }

    @Override
    public String toString() {
        return "ProductSendHeader{" +
                "code=" + code +
                ", number='" + number + '\'' +
                ", rawType=" + rawType +
                ", transportMode='" + transportMode + '\'' +
                ", weight=" + weight +
                ", createDate=" + createDate +
                ", company=" + company +
                ", processManage=" + processManage +
                ", auditStatus=" + auditStatus +
                ", outStatus=" + outStatus +
                ", sender=" + sender +
                ", sendTime=" + sendTime +
                ", applicant=" + applicant +
                ", applyTime=" + applyTime +
                ", productSends=" + productSends +
                '}';
    }
}
