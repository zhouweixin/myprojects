package com.xplusplus.security.exception;

/**
 * SecurityException mes异常
 * 
 * @author zhouweixin
 *
 */
public class SecurityException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Integer code;

	public SecurityException(EnumException exceptionsEnum) {
		super(exceptionsEnum.getMessage());

		this.code = exceptionsEnum.getCode();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
