package com.xplusplus.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: zhouweixin
 * @Description: 民族
 * @Date: Created in 下午6:29:20 2018年5月22日
 */
@Entity
public class Nation {
	// 主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 名称
	@NotNull(message = "民族的名称不能为空")
	@NotBlank(message = "民族的名称不能为空")
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
		return "Nation [id=" + id + ", name=" + name + "]";
	}

}
