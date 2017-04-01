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
	 * 100000-余额不够；
	 * 100001-优惠卷不存在或者已经使用；
	 * 100002-订单所选赠品超限；
	 * 100003-订单所需库存操库；
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
