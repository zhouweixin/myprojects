package com.hnu.mes.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author: zhouweixin
 * @Description: 报损单表头
 * @Date: Created in 下午5:36:20 2018年5月27日
 */
@Entity
@Table(name = "warehouse_loss_entry_header")
public class LossEntryHeader {
    // 编码: 自增长
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    // 库存编码
    private Long materialsTotalCode;

    // 物料类型
    @ManyToOne(targetEntity = RawType.class)
    @JoinColumn(name = "raw_type_code", referencedColumnName = "code")
    private RawType rawType;

    // 库存量
    @Column(precision = 2)
    private Double weight;

    // 报损量
    @Column(precision = 2)
    private Double lossWeight;

    // 报损时间
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    // 报损人
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_code", referencedColumnName = "code")
    private User user;

    // 流程管理
    @ManyToOne(targetEntity = ProcessManage.class)
    @JoinColumn(name = "process_manage_code", referencedColumnName = "code")
    private ProcessManage processManage;

    // 审核状态:0不需要审核;1待审核;2已通过;3未通过
    private Integer auditStatus = 0;

    // 报损单详情
    @OneToMany(targetEntity = ProductSend.class)
    @JoinColumn(name = "loss_entry_header_code")
    private Set<LossEntry> lossEntries = new HashSet<LossEntry>();

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Set<LossEntry> getLossEntries() {
        return lossEntries;
    }

    public void setLossEntries(Set<LossEntry> lossEntries) {
        this.lossEntries = lossEntries;
    }

    public Long getMaterialsTotalCode() {
        return materialsTotalCode;
    }

    public void setMaterialsTotalCode(Long materialsTotalCode) {
        this.materialsTotalCode = materialsTotalCode;
    }

    public Double getLossWeight() {
        return lossWeight;
    }

    public void setLossWeight(Double lossWeight) {
        this.lossWeight = lossWeight;
    }

    @Override
    public String toString() {
        return "LossEntryHeader{" +
                "code=" + code +
                ", materialsTotalCode=" + materialsTotalCode +
                ", rawType=" + rawType +
                ", weight=" + weight +
                ", lossWeight=" + lossWeight +
                ", time=" + time +
                ", user=" + user +
                ", processManage=" + processManage +
                ", auditStatus=" + auditStatus +
                ", lossEntries=" + lossEntries +
                '}';
    }
}
