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
	ADD_FAILED_DEPARTMENT_NOT_EXIST(6, "新增失败, 部门不存在"),
	ADD_FAILED_ID_ERROR(7, "新增失败, 工号编码错误"),
	ADD_FAILED_SHORT_NAME_NOT_LAWER(8, "新增失败, 部门简称只能是唯一的2位字母"),
	ADD_FAILED_PHONE_NOT_LAWER(9, "新增失败, 手机号码只能是11位数字"),
	UPDATE_FAILED_JOB_NATURE_NOT_EXIST(10, "更新失败, 工作性质不存在"),
	UPDATE_FAILED_DEPARTMENT_NOT_EXIST(11, "更新失败, 部门不存在"),
	UPDATE_FAILED_PHONE_NOT_LAWER(12, "新增失败, 手机号码只能是11位数字"),
	ADD_FAILED_USER_NOT_EXIST(13, "新增失败, 用户不存在"),
	UPDATE_FAILED_USER_NOT_EXIST(14, "更新失败, 用户不存在"),
	ADD_FAILED_USER_ARCHIVE_EXIST(15, "新增失败, 用户的档案已存在"),
	ADD_FAILED_PROJECT_NOT_EXIST(16, "新增失败, 项目不存在"),
	ADD_FAILED_OPERATOR_NOT_EXIST(17, "新增失败, 经办人不存在"),
	UPDATE_FAILED_PASSWORD_NULL(18, "更新失败, 密码不可为空"),
	UPDATE_FAILED_PASSWORD_NOT_EQUALS(19, "更新失败, 旧密码不相同"),

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
