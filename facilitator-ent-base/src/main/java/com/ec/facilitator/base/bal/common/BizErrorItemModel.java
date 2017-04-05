package com.ec.facilitator.base.bal.common;

/**
 * 业务error具体错误信息.
 */
public class BizErrorItemModel {

	/**登录用户不存在*/
	public static final String LOGIN_ERROR_100001="100001";
	
	/**登录用户名或者密码为空*/
	public static final String LOGIN_ERROR_100002 = "100002";
	
	/**登录app业务异常*/
	public static final String LOGIN_ERROR_100003 = "100003";
	
	/**登录密码错误*/
	public static final String LOGIN_ERROR_100004 = "100004";
	
	/** 错误码. */
	private String errorCode;

	/** 错误原因. */
	private String reason;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
