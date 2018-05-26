package com.hnu.mes.domain;

import java.io.Serializable;

/**
 * Operation和Pag的复合主键
 * 
 * @author zhouweixin
 *
 */
public class OperationPagePrimaryKey implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 用户工号 */
	private Integer operationCode;
	/** 角色工号 */
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OperationPagePrimaryKey [operationCode=" + operationCode + ", pageCode=" + pageCode + "]";
	}

}
