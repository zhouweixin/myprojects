package com.hnu.mes.exception;

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
	DELETE_FAILED(2, "删除失败, 不存在"),
	UPDATE_FAILED_AUDIT(3, "审核失败, 已审核"),
	UPDATE_FAILED_PUBLISH(4, "发布失败, 已发布"),
	REQUEST_METHOD(5, "请求方法不匹配"),
	SORT_FIELD(6, "排序字段名错误, 可省略"),
	STRING_TO_NUM_FALIED(7, "字符串转数字错误"),
	ARGU_MISMATCH_EXCEPTION(8, "参数类型不匹配错误, 请检查"),
	NEW_PASSWORD_NULL(9, "新密码不能为空"),
	TWICE_PASSWORD_DIFF(10, "两次密码不相同"),
	CODE_OR_PASSWORD_BLANK(11, "登录失败, 工号或密码为空"),
	CODE_OR_PASSWORD_ERROR(12, "登录失败, 工号或密码错误"),
	ENABLE_IC_FALSE(13, "登录失败, IC卡未开启登录功能"),
	INTE_CIRC_CARD_MAY_NO(14, "登录失败, IC卡可能不存在"),
	INTE_CIRC_CARD_BLANK(15, "IC卡号为空"),
	OLD_PASSWORD_FALSE(16, "修改失败, 可能是原密码错误"),
	CODE_DUPLICATE(17, "新增失败, 编码重复"),
	UPDATE_FAILED_NOT_EXIST(18, "更新失败, 不存在"),
	UPLOAD_FAILED(19, "上传失败，文件不存在"),
	UPLOAD_FAILED_UNKNOWN_ERROR(20, "上传失败，未知错误"),
	UPLOAD_FAILED_FILE_EMPTY(21, "上传失败，文件是空的"),
	FILE_FORMAT_ERROR(22, "文件格式错误, 请确保上传Excel2007版本以上, 扩展名为.xlsx"),
	STATUS_NOT_EXIST(23, "状态不存在"),
	AUDITOR_NOT_EXIST(24, "审核失败, 审核人不存在"),
	PUBLISHER_NOT_EXIST(25, "发布失败, 发布人不存在"),
	CUR_LEADER_NOT_EXIST(26, "添加失败, 当前负责人不存在"),
	INDICATOR_NOT_EXIST(27, "添加失败, 指标不存在"),
	UPDATE_FAILED_NOT_AUDIT(28, "发布失败, 未审核"),
    DEPARTMENT_NOT_EXISTS(29,"您输入的部门不存在"),
	PRODUCTLINE_NOT_EXISTS(30, "您输入的生产线不存在"),
	ARCHIVE_NOT_EXISTS(31, "您输入的档案不存在"),
	APPLICATIONPERSON_NOT_EXISTS(32, "您输入的申请人不存在"),
	DELETE_FAILED_SUB_NOT_NULL(33, "删除失败， 子菜单非空"),
	SHIFT_FAILED_NOT_EXISTS(34, "移动失败， 菜单不全存在"),
	UPDATE_ROLES_FAILED_USER_NOT_EXISTS(35, "更新角色失败， 用户不存在"),
	ADD_FAILED_SUPPLIER_NOT_EXISTS(36, "新增失败, 供应商不存在"),
	ADD_FAILED_SENDER_NOT_EXISTS(37, "新增失败, 发货人不存在"),
	ADD_FAILED_SUPPLIER_TYPE_NOT_EXISTS(38, "新增失败, 发货人不存在"),
	UPDATE_FAILED_STATUS_NOT_LEGAL(39, "更新失败, 不合法"),
    UPDATE_FAILED_USER_NOT_EXISTS(40, "更新失败, 用户不存在"),
    UPDATE_FAILED_RAW_TYPE_NOT_EXISTS(41, "更新失败, 原料类型不存在"),
    UPDATE_FAILED_DEPARTMENT_NOT_EXISTS(42, "更新失败, 发文部门不存在"),
	DELETE_FAILED_REF_KEY_EXISTS(43, "删除失败, 有外键存在"),
    SUBMIT_FAILED_DEPARTMENT_NULL_OR_NOT_EXIST(44, "领料申请提交失败, 领料部门为空或不存在"),
    SUBMIT_FAILED_USER_NULL_OR_NOT_EXIST(45, "领料申请提交失败, 申请人为空或不存在"),
    SUBMIT_FAILED_PROCESS_MANAGE_NULL_OR_NOT_EXIST(46, "领料申请提交失败, 流程为空或不存在"),
	FIND_FAILED_RAW_TYPE_NOT_EXIST(47, "查询失败, 原料类型不存在"),
	FIND_FAILED_TABLE_NAME_NOT_EXIST(48, "查询失败, 原料表名不存在"),
	AUDIT_FAILED_PROCESS_MANAGE_NOT_EXIST(49, "审核失败, 审核流程不存在"),
	AUDIT_FAILED_PICKING_APPLY_NOT_EXIST(50, "审核失败, 领料申请表不存在"),
	AUDIT_FAILED_APPLIER_NOT_EXIST(51, "审核失败, 领料申请人不存在"),
	DELETE_FAILED_SYSTEM_NOT_ALLOW(52, "删除失败, 系统值不允许删除"),
	UPDATE_FAILED_SYSTEM_NOT_ALLOW(53, "更新失败, 系统值不允许更新"),
	UPDATE_FAILED_SUBMIT_NOT_ALLOW(54, "更新失败, 已提交不允许更新"),
	UPDATE_FAILED_PICKING_NOT_ALLOW(55, "更新失败, 已领料不允许更新"),
    ADD_FAILED_PROD_DEPARTMENT_NOT_EXIST(56, "新增失败, 制造部门不存在"),
    ADD_FAILED_PAYER_NOT_EXIST(57, "新增失败, 制造部门不存在"),
    NOT_UNIQUE_NUMBER(58, "7位唯一的编号生成失败"),
    WAREHOUSE_KEEPER_NOT_EXIST(59, "错误, 仓库管理员不存在"),
    FAILED_APPLICANT_NOT_EXIST(60, "失败, 申请人不存在"),
    FAILED_GODOWN_ENTRY_NOT_EXIST(61, "失败, 入库单不存在"),
    FAILED_DEPARTMENT_NOT_EXIST(62, "失败, 受文部门不存在"),
    FAILED_USER_NOT_EXIST(63, "失败, 当前用户不存在"),
    IQC_NOT_EXIST(64, "错误, IQC不存在"),
	UPDATE_FAILED_GODOWNER_NOT_EXIST(65, "更新失败, 入库人不存在"),
	UPDATE_FAILED_PRODUCT_GODOWN_HEADER_NOT_EXIST(66, "更新失败, 产品入库单不存在"),
	DELETE_FAILED_SUBMIT_NOT_ALLOW(67, "删除失败, 已提交不允许删除"),
	UPDATE_FAILED_PRODUCT_SEND_HEADER_NOT_EXIST(68, "更新失败, 产品出库单不存在"),
	UPDATE_FAILED_AUDITOR_NOT_EXIST_OR_AUDIT(69, "更新失败, 该审核人不存在或者已经审核"),
	UPDATE_FAILED_SENDER_NOT_EXIST(70, "更新失败, 出库人不存在"),
	AUDITOR_NOT_CUR_AUDITOR(71, "更新失败, 不是当前审核人"),
	AUDITOR_FAILED_END_AUDIT(72, "审核失败, 已结束"),
	AUDIT_STATUS_NOT_LAWER(73, "审核失败, 审核状态不合法"),
	NEXT_AUDITOR_NOT_EXIST(74, "审核失败, 下一个审核人不存在"),
	FIND_ERROE_PICKING_NOT_EXIST(75, "查询失败, 申请单不存在"),
	PROCESS_TYPE_NOT_EXIST(76, "查询失败, 申请单的流程不存在"),


    ;


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
