package com.hnu.mes.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 17:19 2018/5/25
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_product_send_audit")
public class ProductSendAudit {
    // 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    // 产品出库单表头
    @ManyToOne(targetEntity = ProductSendHeader.class)
    @JoinColumn(name = "product_send_header_code", referencedColumnName = "code")
    private ProductSendHeader productSendHeader;

    // 流程类型
    @ManyToOne(targetEntity = ProcessManage.class)
    @JoinColumn(name = "process_manage_code", referencedColumnName = "code")
    private ProcessManage processManage;

    // 审核结果:0未通过;1已通过
    private Integer auditResult;

    // 审核人
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "auditor_code", referencedColumnName = "code")
    private User auditor;

    // 审核时间
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;

    // 备注
    private String note;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public ProductSendHeader getProductSendHeader() {
        return productSendHeader;
    }

    public void setProductSendHeader(ProductSendHeader productSendHeader) {
        this.productSendHeader = productSendHeader;
    }

    public ProcessManage getProcessManage() {
        return processManage;
    }

    public void setProcessManage(ProcessManage processManage) {
        this.processManage = processManage;
    }

    public Integer getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(Integer auditResult) {
        this.auditResult = auditResult;
    }

    public User getAuditor() {
        return auditor;
    }

    public void setAuditor(User auditor) {
        this.auditor = auditor;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ProductSendAudit{" +
                "code=" + code +
                ", productSendHeader=" + productSendHeader +
                ", processManage=" + processManage +
                ", auditResult=" + auditResult +
                ", auditor=" + auditor +
                ", auditTime=" + auditTime +
                ", note='" + note + '\'' +
                '}';
    }
}
