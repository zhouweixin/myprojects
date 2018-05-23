package com.xplusplus.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: zhouweixin
 * @Description: 合同类型:劳动合同, 劳务合同, 实习协议
 * @Date: Created in 上午10:25:24 2018年5月23日
 */
@Entity
public class ContractType {
	// 主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 名称
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
		return "ContractType [id=" + id + ", name=" + name + "]";
	}
}
