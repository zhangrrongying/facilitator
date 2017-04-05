package com.ec.facilitator.base.bal.common;

public class BizErrorException extends Exception {
	

	private static final long serialVersionUID = 1L;


	public BizErrorException(String reasonKey, String errorCode, String tag) {
		this.reasonKey = reasonKey;
		this.errorCode = errorCode;
		this.tag = tag;
	}
	
	/**
	 * 错误原因Key值
	 */
	private String reasonKey;
	
	/**
	 * 错误内容
	 */
	private String errorCode;
	/**
	 * 不同类型需要附加的tag信息；
	 */
	private String tag;
	
	
	public String getReasonKey() {
		return reasonKey;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public String getTag() {
		return tag;
	}
}
