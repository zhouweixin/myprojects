package com.xplusplus.security.exception;

/**
 * 所有异常类型
 * 
 * @author zhouweixin
 *
 */
public enum EnumExceptions {
	UNKOWN_ERROR(-1, "未知错误"),
	SUCCESS(0, "操作成功"),
	ADD_FAILED_DUPLICATE(1, "新增失败, 主键已存在"),
	UPDATE_FAILED_NOT_EXIST(2, "更新失败, 不存在"),
	DELETE_FAILED_NOT_EXIST(3, "删除失败, 不存在"),
	REQUEST_METHOD(4, "请求方法不匹配"),
	ARGU_MISMATCH_EXCEPTION(5, "参数类型不匹配错误, 请检查"),

	;

	/** 编码 */
	private Integer code;
	/** 信息 */
	private String message;

	/**
	 * 构造函数
	 * 
	 * @param code
	 * @param message
	 */
	private EnumExceptions(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

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
}
