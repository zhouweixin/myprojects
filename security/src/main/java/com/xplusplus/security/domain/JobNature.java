package com.xplusplus.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午7:33:44 2018年5月23日
 */
@Entity
public class JobNature {
	// 主键: 自增长
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
		return "JobNature [id=" + id + ", name=" + name + "]";
	}

}
