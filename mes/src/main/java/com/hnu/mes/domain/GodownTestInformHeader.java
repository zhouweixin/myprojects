package com.hnu.mes.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description: 样品入库及检验通知表头表
 * @Date: Created in 22:40 2018/5/1
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_godown_test_inform_header")
public class GodownTestInformHeader {
    // 编码
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    // 入库单表头
    @OneToOne(targetEntity = GodownEntryHeader.class)
    @JoinColumn(name = "godown_entry_header_code", referencedColumnName = "code")
    private GodownEntryHeader godownEntryHeader;

    // 原料类型
    @ManyToOne(targetEntity = RawType.class)
    @JoinColumn(name = "raw_type_code", referencedColumnName = "code")
    private RawType rawType;

    // 发货厂家
    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name = "supplier_code", referencedColumnName = "code")
    private Supplier supplier;

    // 到货日期
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    // 创建时间
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    // 受文部门
    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "department_code", referencedColumnName = "code")
    private Department department;

    // 创建人
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "create_user_code", referencedColumnName = "code")
    private User createUser;

    // 领取状态:0待领取;1已领取
    private Integer status;

    // 领取人
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "receiptor_code", referencedColumnName = "code")
    private User receiptor;

    // 领取时间
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receiveTime;

    // 化验通知单
    @OneToMany(targetEntity = GodownTestInform.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "inform_header_code")
    private List<GodownTestInform> godownTestInforms = new ArrayList<GodownTestInform>();

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public GodownEntryHeader getGodownEntryHeader() {
        return godownEntryHeader;
    }

    public void setGodownEntryHeader(GodownEntryHeader godownEntryHeader) {
        this.godownEntryHeader = godownEntryHeader;
    }

    public RawType getRawType() {
        return rawType;
    }

    public void setRawType(RawType rawType) {
        this.rawType = rawType;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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

    public User getReceiptor() {
        return receiptor;
    }

    public void setReceiptor(User receiptor) {
        this.receiptor = receiptor;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public List<GodownTestInform> getGodownTestInforms() {
        return godownTestInforms;
    }

    public void setGodownTestInforms(List<GodownTestInform> godownTestInforms) {
        this.godownTestInforms = godownTestInforms;
    }

    @Override
    public String toString() {
        return "GodownTestInformHeader{" +
                "code=" + code +
                ", godownEntryHeader=" + godownEntryHeader +
                ", rawType=" + rawType +
                ", supplier=" + supplier +
                ", date=" + date +
                ", createTime=" + createTime +
                ", department=" + department +
                ", createUser=" + createUser +
                ", status=" + status +
                ", receiptor=" + receiptor +
                ", receiveTime=" + receiveTime +
                ", godownTestInforms=" + godownTestInforms +
                '}';
    }
}
