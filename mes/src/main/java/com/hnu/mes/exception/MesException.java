package com.hnu.mes.exception;

/**
 * MesException mes异常
 * 
 * @author zhouweixin
 *
 */
public class MesException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Integer code;

	public MesException(EnumException exceptionsEnum) {
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
