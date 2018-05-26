package com.hnu.mes.domain;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zhouweixin
 * @Description: 领料申请表头
 * @Date: Created in 20:32 2018/5/2
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_picking_apply_header")
public class PickingApplyHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    // 出库单编号：7位，系统生成
    private String number;

    // 领料部门
    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "department_code", referencedColumnName = "code")
    private Department department;

    // 申请日期
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyDate;

    // 流程类型
    @ManyToOne(targetEntity = ProcessManage.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "process_manage_code", referencedColumnName = "code")
    private ProcessManage processManage;

    // 审核状态:0未提交;1待审核;2已审核
    private Integer auditStatus;

    // 领料状态:0待出库; 1已出库
    private Integer pickingStatus;

    // 仓库放行人
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_code", referencedColumnName = "code")
    private User user;

    // 出库时间
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pickingTime;

    // 领料申请表
    @OneToMany(targetEntity = PickingApply.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "picking_apply_header_code")
    private Set<PickingApply> pickingApplies = new HashSet<>();

    // 流程类型
    @ManyToOne(targetEntity = Process.class)
    @JoinColumn(name = "process_code", referencedColumnName = "code")
    private Process process;

    // 申请人
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "applicant_code", referencedColumnName = "code")
    private User applicant;

    // 当前审核人的编码
    private String curAuditorCode;

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
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

    public Integer getPickingStatus() {
        return pickingStatus;
    }

    public void setPickingStatus(Integer pickingStatus) {
        this.pickingStatus = pickingStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getPickingTime() {
        return pickingTime;
    }

    public void setPickingTime(Date pickingTime) {
        this.pickingTime = pickingTime;
    }

    public Set<PickingApply> getPickingApplies() {
        return pickingApplies;
    }

    public void setPickingApplies(Set<PickingApply> pickingApplies) {
        this.pickingApplies = pickingApplies;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public String getCurAuditorCode() {
        return curAuditorCode;
    }

    public void setCurAuditorCode(String curAuditorCode) {
        this.curAuditorCode = curAuditorCode;
    }

    @Override
    public String toString() {
        return "PickingApplyHeader{" +
                "code=" + code +
                ", number='" + number + '\'' +
                ", department=" + department +
                ", applyDate=" + applyDate +
                ", processManage=" + processManage +
                ", auditStatus=" + auditStatus +
                ", pickingStatus=" + pickingStatus +
                ", user=" + user +
                ", pickingTime=" + pickingTime +
                ", pickingApplies=" + pickingApplies +
                ", process=" + process +
                ", applicant=" + applicant +
                ", curAuditorCode='" + curAuditorCode + '\'' +
                '}';
    }
}
