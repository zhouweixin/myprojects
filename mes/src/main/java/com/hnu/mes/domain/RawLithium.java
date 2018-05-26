package com.hnu.mes.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

/**
 * 原料:碳酸锂
 * Created by zhouweixin on 2018/3/18.
 */
@Entity
@Table(name = "quality_raw_lithium")
public class RawLithium {
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
     * 厂家编号
     */
    @NotBlank(message = "厂家批号不能为空")
    @Column(unique = true, nullable = false)
    private String batchNumber;
    /**
     * 生产日期
     */
    private Date productDate;
    /**
     * 数量
     */
    private Integer number;
    /**
     * 判定
     */
    @OneToOne(targetEntity = Judge.class)
    @JoinColumn(name = "judge_code", referencedColumnName = "code")
    private Judge judge;
    /**
     * 数据c长度
     */
    @Transient
    public static final Integer DATALEN = 40;
    /**
     * 其它数据
     */
    private Double c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25, c26, c27, c28, c29, c30, c31, c32, c33, c34, c35, c36, c37, c38, c39, c40;

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

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Judge getJudge() {
        return judge;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }

    public static Integer getDATALEN() {
        return DATALEN;
    }

    public Double getC1() {
        return c1;
    }

    public void setC1(Double c1) {
        this.c1 = c1;
    }

    public Double getC2() {
        return c2;
    }

    public void setC2(Double c2) {
        this.c2 = c2;
    }

    public Double getC3() {
        return c3;
    }

    public void setC3(Double c3) {
        this.c3 = c3;
    }

    public Double getC4() {
        return c4;
    }

    public void setC4(Double c4) {
        this.c4 = c4;
    }

    public Double getC5() {
        return c5;
    }

    public void setC5(Double c5) {
        this.c5 = c5;
    }

    public Double getC6() {
        return c6;
    }

    public void setC6(Double c6) {
        this.c6 = c6;
    }

    public Double getC7() {
        return c7;
    }

    public void setC7(Double c7) {
        this.c7 = c7;
    }

    public Double getC8() {
        return c8;
    }

    public void setC8(Double c8) {
        this.c8 = c8;
    }

    public Double getC9() {
        return c9;
    }

    public void setC9(Double c9) {
        this.c9 = c9;
    }

    public Double getC10() {
        return c10;
    }

    public void setC10(Double c10) {
        this.c10 = c10;
    }

    public Double getC11() {
        return c11;
    }

    public void setC11(Double c11) {
        this.c11 = c11;
    }

    public Double getC12() {
        return c12;
    }

    public void setC12(Double c12) {
        this.c12 = c12;
    }

    public Double getC13() {
        return c13;
    }

    public void setC13(Double c13) {
        this.c13 = c13;
    }

    public Double getC14() {
        return c14;
    }

    public void setC14(Double c14) {
        this.c14 = c14;
    }

    public Double getC15() {
        return c15;
    }

    public void setC15(Double c15) {
        this.c15 = c15;
    }

    public Double getC16() {
        return c16;
    }

    public void setC16(Double c16) {
        this.c16 = c16;
    }

    public Double getC17() {
        return c17;
    }

    public void setC17(Double c17) {
        this.c17 = c17;
    }

    public Double getC18() {
        return c18;
    }

    public void setC18(Double c18) {
        this.c18 = c18;
    }

    public Double getC19() {
        return c19;
    }

    public void setC19(Double c19) {
        this.c19 = c19;
    }

    public Double getC20() {
        return c20;
    }

    public void setC20(Double c20) {
        this.c20 = c20;
    }

    public Double getC21() {
        return c21;
    }

    public void setC21(Double c21) {
        this.c21 = c21;
    }

    public Double getC22() {
        return c22;
    }

    public void setC22(Double c22) {
        this.c22 = c22;
    }

    public Double getC23() {
        return c23;
    }

    public void setC23(Double c23) {
        this.c23 = c23;
    }

    public Double getC24() {
        return c24;
    }

    public void setC24(Double c24) {
        this.c24 = c24;
    }

    public Double getC25() {
        return c25;
    }

    public void setC25(Double c25) {
        this.c25 = c25;
    }

    public Double getC26() {
        return c26;
    }

    public void setC26(Double c26) {
        this.c26 = c26;
    }

    public Double getC27() {
        return c27;
    }

    public void setC27(Double c27) {
        this.c27 = c27;
    }

    public Double getC28() {
        return c28;
    }

    public void setC28(Double c28) {
        this.c28 = c28;
    }

    public Double getC29() {
        return c29;
    }

    public void setC29(Double c29) {
        this.c29 = c29;
    }

    public Double getC30() {
        return c30;
    }

    public void setC30(Double c30) {
        this.c30 = c30;
    }

    public Double getC31() {
        return c31;
    }

    public void setC31(Double c31) {
        this.c31 = c31;
    }

    public Double getC32() {
        return c32;
    }

    public void setC32(Double c32) {
        this.c32 = c32;
    }

    public Double getC33() {
        return c33;
    }

    public void setC33(Double c33) {
        this.c33 = c33;
    }

    public Double getC34() {
        return c34;
    }

    public void setC34(Double c34) {
        this.c34 = c34;
    }

    public Double getC35() {
        return c35;
    }

    public void setC35(Double c35) {
        this.c35 = c35;
    }

    public Double getC36() {
        return c36;
    }

    public void setC36(Double c36) {
        this.c36 = c36;
    }

    public Double getC37() {
        return c37;
    }

    public void setC37(Double c37) {
        this.c37 = c37;
    }

    public Double getC38() {
        return c38;
    }

    public void setC38(Double c38) {
        this.c38 = c38;
    }

    public Double getC39() {
        return c39;
    }

    public void setC39(Double c39) {
        this.c39 = c39;
    }

    public Double getC40() {
        return c40;
    }

    public void setC40(Double c40) {
        this.c40 = c40;
    }

    @Override
    public String toString() {
        return "RawLithium{" +
                "code=" + code +
                ", operation=" + operation +
                ", auditor=" + auditor +
                ", publisher=" + publisher +
                ", auditDate=" + auditDate +
                ", publishDate=" + publishDate +
                ", status=" + status +
                ", testDate=" + testDate +
                ", batchNumber='" + batchNumber + '\'' +
                ", productDate=" + productDate +
                ", number=" + number +
                ", judge=" + judge +
                ", c1=" + c1 +
                ", c2=" + c2 +
                ", c3=" + c3 +
                ", c4=" + c4 +
                ", c5=" + c5 +
                ", c6=" + c6 +
                ", c7=" + c7 +
                ", c8=" + c8 +
                ", c9=" + c9 +
                ", c10=" + c10 +
                ", c11=" + c11 +
                ", c12=" + c12 +
                ", c13=" + c13 +
                ", c14=" + c14 +
                ", c15=" + c15 +
                ", c16=" + c16 +
                ", c17=" + c17 +
                ", c18=" + c18 +
                ", c19=" + c19 +
                ", c20=" + c20 +
                ", c21=" + c21 +
                ", c22=" + c22 +
                ", c23=" + c23 +
                ", c24=" + c24 +
                ", c25=" + c25 +
                ", c26=" + c26 +
                ", c27=" + c27 +
                ", c28=" + c28 +
                ", c29=" + c29 +
                ", c30=" + c30 +
                ", c31=" + c31 +
                ", c32=" + c32 +
                ", c33=" + c33 +
                ", c34=" + c34 +
                ", c35=" + c35 +
                ", c36=" + c36 +
                ", c37=" + c37 +
                ", c38=" + c38 +
                ", c39=" + c39 +
                ", c40=" + c40 +
                '}';
    }
}
