package com.hnu.mes.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: zhengyibin
 * @Date: 2018/3/18
 * @Description: 产品，对应表quality_product，用于产品审核、产品发布
 */
@Entity
@Table(name = "quality_product")
public class Product {
    /**
     * 编码
     * 主键，非空
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    /**
     * 操作
     * 外键--操作.编码
     */
    @ManyToOne(targetEntity = Operation.class)
    @JoinColumn(name = "operation_code", referencedColumnName = "code", columnDefinition = "tinyint(8) default 1")
    private Operation operation;
    /**
     * 审核人
     * 用户.编码
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
     * 批号
     * 非空
     */
    @NotBlank(message = "产品批号不能为空")
    @Column(unique = true, nullable = false)
    private String batchNumber;
    /**
     * 判定
     */
    @OneToOne(targetEntity = Judge.class)
    @JoinColumn(name = "judge_code", referencedColumnName = "code")
    private Judge judge;
    /**
     * 数量
     */
    private Integer number;
    /**
     * 各种指标
     */
    private Double p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20,
            p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40;

    /**
     * 扩展字段个数
     */
    @Transient
    public final static Integer DATALEN = 40;

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

    public Judge getJudge() {
        return judge;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getP1() {
        return p1;
    }

    public void setP1(Double p1) {
        this.p1 = p1;
    }

    public Double getP2() {
        return p2;
    }

    public void setP2(Double p2) {
        this.p2 = p2;
    }

    public Double getP3() {
        return p3;
    }

    public void setP3(Double p3) {
        this.p3 = p3;
    }

    public Double getP4() {
        return p4;
    }

    public void setP4(Double p4) {
        this.p4 = p4;
    }

    public Double getP5() {
        return p5;
    }

    public void setP5(Double p5) {
        this.p5 = p5;
    }

    public Double getP6() {
        return p6;
    }

    public void setP6(Double p6) {
        this.p6 = p6;
    }

    public Double getP7() {
        return p7;
    }

    public void setP7(Double p7) {
        this.p7 = p7;
    }

    public Double getP8() {
        return p8;
    }

    public void setP8(Double p8) {
        this.p8 = p8;
    }

    public Double getP9() {
        return p9;
    }

    public void setP9(Double p9) {
        this.p9 = p9;
    }

    public Double getP10() {
        return p10;
    }

    public void setP10(Double p10) {
        this.p10 = p10;
    }

    public Double getP11() {
        return p11;
    }

    public void setP11(Double p11) {
        this.p11 = p11;
    }

    public Double getP12() {
        return p12;
    }

    public void setP12(Double p12) {
        this.p12 = p12;
    }

    public Double getP13() {
        return p13;
    }

    public void setP13(Double p13) {
        this.p13 = p13;
    }

    public Double getP14() {
        return p14;
    }

    public void setP14(Double p14) {
        this.p14 = p14;
    }

    public Double getP15() {
        return p15;
    }

    public void setP15(Double p15) {
        this.p15 = p15;
    }

    public Double getP16() {
        return p16;
    }

    public void setP16(Double p16) {
        this.p16 = p16;
    }

    public Double getP17() {
        return p17;
    }

    public void setP17(Double p17) {
        this.p17 = p17;
    }

    public Double getP18() {
        return p18;
    }

    public void setP18(Double p18) {
        this.p18 = p18;
    }

    public Double getP19() {
        return p19;
    }

    public void setP19(Double p19) {
        this.p19 = p19;
    }

    public Double getP20() {
        return p20;
    }

    public void setP20(Double p20) {
        this.p20 = p20;
    }

    public Double getP21() {
        return p21;
    }

    public void setP21(Double p21) {
        this.p21 = p21;
    }

    public Double getP22() {
        return p22;
    }

    public void setP22(Double p22) {
        this.p22 = p22;
    }

    public Double getP23() {
        return p23;
    }

    public void setP23(Double p23) {
        this.p23 = p23;
    }

    public Double getP24() {
        return p24;
    }

    public void setP24(Double p24) {
        this.p24 = p24;
    }

    public Double getP25() {
        return p25;
    }

    public void setP25(Double p25) {
        this.p25 = p25;
    }

    public Double getP26() {
        return p26;
    }

    public void setP26(Double p26) {
        this.p26 = p26;
    }

    public Double getP27() {
        return p27;
    }

    public void setP27(Double p27) {
        this.p27 = p27;
    }

    public Double getP28() {
        return p28;
    }

    public void setP28(Double p28) {
        this.p28 = p28;
    }

    public Double getP29() {
        return p29;
    }

    public void setP29(Double p29) {
        this.p29 = p29;
    }

    public Double getP30() {
        return p30;
    }

    public void setP30(Double p30) {
        this.p30 = p30;
    }

    public Double getP31() {
        return p31;
    }

    public void setP31(Double p31) {
        this.p31 = p31;
    }

    public Double getP32() {
        return p32;
    }

    public void setP32(Double p32) {
        this.p32 = p32;
    }

    public Double getP33() {
        return p33;
    }

    public void setP33(Double p33) {
        this.p33 = p33;
    }

    public Double getP34() {
        return p34;
    }

    public void setP34(Double p34) {
        this.p34 = p34;
    }

    public Double getP35() {
        return p35;
    }

    public void setP35(Double p35) {
        this.p35 = p35;
    }

    public Double getP36() {
        return p36;
    }

    public void setP36(Double p36) {
        this.p36 = p36;
    }

    public Double getP37() {
        return p37;
    }

    public void setP37(Double p37) {
        this.p37 = p37;
    }

    public Double getP38() {
        return p38;
    }

    public void setP38(Double p38) {
        this.p38 = p38;
    }

    public Double getP39() {
        return p39;
    }

    public void setP39(Double p39) {
        this.p39 = p39;
    }

    public Double getP40() {
        return p40;
    }

    public void setP40(Double p40) {
        this.p40 = p40;
    }

    public static Integer getDATALEN() {
        return DATALEN;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code=" + code +
                ", operation=" + operation +
                ", auditor=" + auditor +
                ", publisher=" + publisher +
                ", auditDate=" + auditDate +
                ", publishDate=" + publishDate +
                ", status=" + status +
                ", testDate=" + testDate +
                ", batchNumber='" + batchNumber + '\'' +
                ", judge=" + judge +
                ", number=" + number +
                ", p1=" + p1 +
                ", p2=" + p2 +
                ", p3=" + p3 +
                ", p4=" + p4 +
                ", p5=" + p5 +
                ", p6=" + p6 +
                ", p7=" + p7 +
                ", p8=" + p8 +
                ", p9=" + p9 +
                ", p10=" + p10 +
                ", p11=" + p11 +
                ", p12=" + p12 +
                ", p13=" + p13 +
                ", p14=" + p14 +
                ", p15=" + p15 +
                ", p16=" + p16 +
                ", p17=" + p17 +
                ", p18=" + p18 +
                ", p19=" + p19 +
                ", p20=" + p20 +
                ", p21=" + p21 +
                ", p22=" + p22 +
                ", p23=" + p23 +
                ", p24=" + p24 +
                ", p25=" + p25 +
                ", p26=" + p26 +
                ", p27=" + p27 +
                ", p28=" + p28 +
                ", p29=" + p29 +
                ", p30=" + p30 +
                ", p31=" + p31 +
                ", p32=" + p32 +
                ", p33=" + p33 +
                ", p34=" + p34 +
                ", p35=" + p35 +
                ", p36=" + p36 +
                ", p37=" + p37 +
                ", p38=" + p38 +
                ", p39=" + p39 +
                ", p40=" + p40 +
                '}';
    }
}
