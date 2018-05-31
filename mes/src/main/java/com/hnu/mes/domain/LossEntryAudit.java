package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午6:51:08 2018年5月27日
 */
@Entity
@Table(name = "warehouse_loss_entry_audit")
public class LossEntryAudit {
	// 编码: 自增长
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;

	// 报损单表头
	@ManyToOne(targetEntity = LossEntryHeader.class)
	@JoinColumn(name = "loss_entry_header_code", referencedColumnName = "code")
	private LossEntryHeader lossEntryHeader;

	// 流程
	@ManyToOne(targetEntity = ProcessManage.class)
	@JoinColumn(name = "process_manage_code", referencedColumnName = "code")
	private ProcessManage processManage;

	// 审核结果:0不通过;1通过
	private Integer auditResult;

	// 审核人
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "auditor_code", referencedColumnName = "code")
	private User auditor;

	// 审核时间
	private Date auditTime;

	// 备注
	private String note;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public LossEntryHeader getLossEntryHeader() {
		return lossEntryHeader;
	}

	public void setLossEntryHeader(LossEntryHeader lossEntryHeader) {
		this.lossEntryHeader = lossEntryHeader;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	@Override
	public String toString() {
		return "LossEntryAudit{" +
				"code=" + code +
				", lossEntryHeader=" + lossEntryHeader +
				", processManage=" + processManage +
				", auditResult=" + auditResult +
				", auditor=" + auditor +
				", auditTime=" + auditTime +
				", note='" + note + '\'' +
				'}';
	}
}
