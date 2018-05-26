package com.hnu.mes.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

/**
 * 制程：扣电
 *
 * @Author: zhengyibin
 * @Date: 2018/3/19
 * @Description: 粉碎扣电
 */
@Entity
@Table(name = "quality_process_buckle")
public class ProcessBuckle {
    /**
     * 数据pc的长度
     */
    @Transient
    public static final Integer DATALEN = 10;
    /**
     * 编码
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    /**
     * 操作
     */
    @ManyToOne(targetEntity = Operation.class)
    @JoinColumn(name = "operation_code", referencedColumnName = "code")
    private Operation operation;
    /**
     * 审核人:对用户管理信息表的员工编码
     */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "auditor_code", referencedColumnName = "code")
    private User auditor;
    /**
     * 发布人
     * 外键--用户.编码
     */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "publisher_code", referencedColumnName = "code")
    private User publisher;
    /**
     * 审核时间
     */
    private Date auditDate;
    /**
     * 发布时间
     */
    private Date publishDate;
    /**
     * 状态码（0未审核，1已审核未发布，2已审核已发布）
     */
    @OneToOne(targetEntity = Status.class)
    @JoinColumn(name = "status_code", referencedColumnName = "code")
    private Status status;
    /**
     * 检测日期
     */
    private Date testDate;
    /**
     * 编号
     */
    @NotBlank(message = "编号不能为空")
    @Column(unique = true, nullable = false)
    private String batchNumber;
    /**
     * 炉号
     */
    private Integer furnaceNum;
    /**
     * 标准编码:对应基础信息库连绵的上下限标准表
     */
    @OneToOne(targetEntity = Bound.class)
    @JoinColumn(name = "bound_code", referencedColumnName = "code")
    private Bound bound;
    /**
     * 各参数
     */
    private Double pc1, pc2, pc3, pc4, pc5, pc6, pc7, pc8, pc9, pc10;

    public static Integer getDATALEN() {
        return DATALEN;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public User getAuditor() {
        return auditor;
    }

    public void setAuditor(User auditor) {
        this.auditor = auditor;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Integer getFurnaceNum() {
        return furnaceNum;
    }

    public void setFurnaceNum(Integer furnaceNum) {
        this.furnaceNum = furnaceNum;
    }

    public Bound getBound() {
        return bound;
    }

    public void setBound(Bound bound) {
        this.bound = bound;
    }

    public Double getPc1() {
        return pc1;
    }

    public void setPc1(Double pc1) {
        this.pc1 = pc1;
    }

    public Double getPc2() {
        return pc2;
    }

    public void setPc2(Double pc2) {
        this.pc2 = pc2;
    }

    public Double getPc3() {
        return pc3;
    }

    public void setPc3(Double pc3) {
        this.pc3 = pc3;
    }

    public Double getPc4() {
        return pc4;
    }

    public void setPc4(Double pc4) {
        this.pc4 = pc4;
    }

    public Double getPc5() {
        return pc5;
    }

    public void setPc5(Double pc5) {
        this.pc5 = pc5;
    }

    public Double getPc6() {
        return pc6;
    }

    public void setPc6(Double pc6) {
        this.pc6 = pc6;
    }

    public Double getPc7() {
        return pc7;
    }

    public void setPc7(Double pc7) {
        this.pc7 = pc7;
    }

    public Double getPc8() {
        return pc8;
    }

    public void setPc8(Double pc8) {
        this.pc8 = pc8;
    }

    public Double getPc9() {
        return pc9;
    }

    public void setPc9(Double pc9) {
        this.pc9 = pc9;
    }

    public Double getPc10() {
        return pc10;
    }

    public void setPc10(Double pc10) {
        this.pc10 = pc10;
    }

    @Override
    public String toString() {
        return "ProcessBuckle{" +
                "code=" + code +
                ", operation=" + operation +
                ", auditor=" + auditor +
                ", publisher=" + publisher +
                ", auditDate=" + auditDate +
                ", publishDate=" + publishDate +
                ", status=" + status +
                ", testDate=" + testDate +
                ", batchNumber='" + batchNumber + '\'' +
                ", furnaceNum=" + furnaceNum +
                ", bound=" + bound +
                ", pc1=" + pc1 +
                ", pc2=" + pc2 +
                ", pc3=" + pc3 +
                ", pc4=" + pc4 +
                ", pc5=" + pc5 +
                ", pc6=" + pc6 +
                ", pc7=" + pc7 +
                ", pc8=" + pc8 +
                ", pc9=" + pc9 +
                ", pc10=" + pc10 +
                '}';
    }
}
