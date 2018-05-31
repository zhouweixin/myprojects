package com.hnu.mes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @Author: zhouweixin
 * @Description: 报损单
 * @Date: Created in 下午6:48:11 2018年5月27日
 */
@Entity
@Table(name = "warehouse_loss_entry")
public class LossEntry {
	// 编码: 自增长
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;

	// 批号
	private String batchNumber;

	// 重量
	@Column(precision = 2)
	private Double weight;

	// 表头
	@ManyToOne(targetEntity = LossEntryHeader.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "loss_entry_header_code", referencedColumnName = "code")
	private LossEntryHeader lossEntryHeader;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@JsonIgnore
	public LossEntryHeader getLossEntryHeader() {
		return lossEntryHeader;
	}

	public void setLossEntryHeader(LossEntryHeader lossEntryHeader) {
		this.lossEntryHeader = lossEntryHeader;
	}
}
