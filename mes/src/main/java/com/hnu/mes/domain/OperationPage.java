package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 操作页面
 * 
 * @author zhouweixin
 *
 */
@Entity
@Table(name = "permission_operation_page")
@IdClass(OperationPagePrimaryKey.class)
public class OperationPage {
	/** 操作编码 */
	@Id
	private Integer operationCode;
	/** 页面编码 */
	@Id
	private Integer pageCode;

	public Integer getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(Integer operationCode) {
		this.operationCode = operationCode;
	}

	public Integer getPageCode() {
		return pageCode;
	}

	public void setPageCode(Integer pageCode) {
		this.pageCode = pageCode;
	}

	@Override
	public String toString() {
		return "OperationPage [operationCode=" + operationCode + ", pageCode=" + pageCode + "]";
	}

}
