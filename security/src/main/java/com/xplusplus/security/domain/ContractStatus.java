package com.xplusplus.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: liweifeng
 * @Description: 合同状态: 待执行, 执行中, 解除, 正常结束
 * @Date: Created in 上午10:34:02 2018年5月23日
 */

@Entity
public class ContractStatus {
	// 主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 类型名称
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ContractStatus [id=" + id + ", name=" + name + "]";
	}

}
