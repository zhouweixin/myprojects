package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 默认密码
 * 
 * @author zhouweixin
 *
 */
@Entity
@Table(name = "basicinfo_default_password")
public class DefaultPassword {
	/**
	 * 编码
	 */
	@Id
	private Integer code = 0;
	/**
	 * 密码
	 */
	@NotBlank(message = "默认密码不可为空")
	private String password = "";

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "DefaultPassword [code=" + code + ", password=" + password + "]";
	}

}
