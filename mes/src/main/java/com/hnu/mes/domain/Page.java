package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 页面
 * 
 * @author zhouweixin
 *
 */
@Entity
@Table(name = "permission_page")
public class Page {
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
		return "Page [code=" + code + ", name=" + name + "]";
	}

}
