package com.hnu.mes.domain;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: zhouweixin
 * @Description: 审核表
 * @Date: Created in 15:10 2018/5/5
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_picking_audit")
public class PickingAudit {
    // 编码
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    // 领料申请表头
    @ManyToOne(targetEntity = PickingApplyHeader.class)
    @JoinColumn(name = "picking_apply_header_code", referencedColumnName = "code")
    private PickingApplyHeader pickingApplyHeader;

    // 流程
    @ManyToOne(targetEntity = ProcessManage.class)
    @JoinColumn(name = "process_manage_code", referencedColumnName = "code")
    private ProcessManage processManage;

    // 审核结果
    private Integer auditResult;

    // 审核人
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "auditor_code", referencedColumnName = "code")
    private User auditor;

    // 审核时间
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;

    // 审核备注:审核意见
    private String note;

    // 流程类型:紧急, 正常
    @ManyToOne(targetEntity = Process.class)
    @JoinColumn(name = "process_code", referencedColumnName = "code")
    private Process process;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public PickingApplyHeader getPickingApplyHeader() {
        return pickingApplyHeader;
    }

    public void setPickingApplyHeader(PickingApplyHeader pickingApplyHeader) {
        this.pickingApplyHeader = pickingApplyHeader;
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

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return "PickingAudit{" +
                "code=" + code +
                ", pickingApplyHeader=" + pickingApplyHeader +
                ", processManage=" + processManage +
                ", auditResult=" + auditResult +
                ", auditor=" + auditor +
                ", auditTime=" + auditTime +
                ", note='" + note + '\'' +
                ", process=" + process +
                '}';
    }
}
