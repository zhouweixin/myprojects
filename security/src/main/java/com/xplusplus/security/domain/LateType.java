package com.xplusplus.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: zhouweixin
 * @Description: 迟到类型表
 * @Date: Created in 下午2:13:16 2018年5月27日
 */
@Entity
public class LateType {
	// 主键: 自增长
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 名称
	private String name;

	// 迟到的分钟数
	private Integer minute;

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

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	@Override
	public String toString() {
		return "LateType [id=" + id + ", name=" + name + ", minute=" + minute + "]";
	}

}
