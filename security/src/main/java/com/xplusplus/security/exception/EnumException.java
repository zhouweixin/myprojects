package com.xplusplus.security.exception;

/**
 * 所有异常类型
 * 
 * @author zhouweixin
 *
 */
public enum EnumException {
	UNKOWN_ERROR(-1, "未知错误"), 
	SUCCESS(0, "成功"),
	FILE_SIZE_EXCEED_MAXIMUM(1, "文件大小超过10M"),
	DELETE_FAILED_NOT_EXIST(2, "删除失败, 不存在"),
	UPDATE_FAILED_AUDIT(3, "审核失败, 已审核"),
	UPDATE_FAILED_PUBLISH(4, "发布失败, 已发布"),
	REQUEST_METHOD(5, "请求方法不匹配"),
	SORT_FIELD(6, "排序字段名错误, 可省略"),
	STRING_TO_NUM_FALIED(7, "字符串转数字错误"),
	ARGU_MISMATCH_EXCEPTION(8, "参数类型不匹配错误, 请检查"),

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
	private EnumException(Integer code, String message) {
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
