package com.xplusplus.security.domain;

import java.time.Period;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author: zhouweixin
 * @Description: 员工合同
 * @Date: Created in 上午10:20:33 2018年5月23日
 */
@Entity
public class UserContract {
	// 主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 员工
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	// 合同类型
	@ManyToOne(targetEntity = ContractType.class)
	@JoinColumn(name = "contract_type_id", referencedColumnName = "id")
	private ContractType contractType;

	// 合同开始日期
	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	// 合同结束日期
	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	// 合同周期
	private Period period;

	// 合同状态
	@ManyToOne(targetEntity = ContractStatus.class)
	@JoinColumn(name = "contract_status_id", referencedColumnName = "id")
	private ContractStatus contractStatus;

	// 合同内容
	@Lob
	@JoinColumn(columnDefinition = "MediumBlob")
	private String content;

	// 扫描件
	@Lob
	@JoinColumn(columnDefinition = "MediumBlob")
	private byte[] scanningCopy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public ContractStatus getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(ContractStatus contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public byte[] getScanningCopy() {
		return scanningCopy;
	}

	public void setScanningCopy(byte[] scanningCopy) {
		this.scanningCopy = scanningCopy;
	}

	@Override
	public String toString() {
		return "UserContract [id=" + id + ", user=" + user + ", contractType=" + contractType + ", startDate="
				+ startDate + ", endDate=" + endDate + ", period=" + period + ", contractStatus=" + contractStatus
				+ ", content=" + content + ", scanningCopy=" + Arrays.toString(scanningCopy) + "]";
	}

}
