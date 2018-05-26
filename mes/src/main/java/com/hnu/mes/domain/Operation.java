package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 操作
 * 
 * @author zhouweixin
 *
 */
@Entity
@Table(name = "permission_operation")
public class Operation {
	/** 编码 */
	@Id
	private Integer code;
	/** 名称 */
	private String name;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Operation [code=" + code + ", name=" + name + "]";
	}

}
