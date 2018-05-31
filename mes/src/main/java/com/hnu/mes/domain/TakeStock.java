package com.hnu.mes.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @Author: zhouweixin
 * @Description: 盘库表
 * @Date: Created in 下午2:45:17 2018年5月28日
 */
@Entity
@Table(name = "warehouse_take_stock")
public class TakeStock {
	// 主键:自增长
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;

	// 系统库存量
	@Column(precision = 2)
	private Double stockWeight;

	// 报损单
	@ManyToOne(targetEntity = LossEntryHeader.class)
	private LossEntryHeader lossEntryHeader;

	// 报损量
	@Column(precision = 2)
	private Double lossWeight;

	// 期末余额
	@Column(precision = 2)
	private Double restWeight;

	// 盘库时间
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date time;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Double getStockWeight() {
		return stockWeight;
	}

	public void setStockWeight(Double stockWeight) {
		this.stockWeight = stockWeight;
	}

	public LossEntryHeader getLossEntryHeader() {
		return lossEntryHeader;
	}

	public void setLossEntryHeader(LossEntryHeader lossEntryHeader) {
		this.lossEntryHeader = lossEntryHeader;
	}

	public Double getLossWeight() {
		return lossWeight;
	}

	public void setLossWeight(Double lossWeight) {
		this.lossWeight = lossWeight;
	}

	public Double getRestWeight() {
		return restWeight;
	}

	public void setRestWeight(Double restWeight) {
		this.restWeight = restWeight;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "TakeStock [code=" + code + ", stockWeight=" + stockWeight + ", lossEntryHeader=" + lossEntryHeader
				+ ", lossWeight=" + lossWeight + ", restWeight=" + restWeight + ", time=" + time + "]";
	}

}
