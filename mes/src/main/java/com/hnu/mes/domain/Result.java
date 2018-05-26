package com.hnu.mes.domain;

/**
 * http请求返回的最外层对象
 * 
 * @author zhouweixin
 *
 */
public class Result<T> {
	/** 编码 */
	private Integer code;

	/** 信息 */
	private String message;

	/** 数据 */
	private T data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
	
	
}
