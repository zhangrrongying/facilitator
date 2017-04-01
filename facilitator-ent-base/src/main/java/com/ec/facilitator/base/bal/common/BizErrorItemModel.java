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
	
	/**农场日志不存在*/
	public static final String FARM_ERROR_100005 = "100005";
	
	/**该日志不属于用户所在的农场*/
	public static final String FARM_ERROR_100006 = "100006";
	
	/**农场日志详情不能为空*/
	public static final String FARM_ERROR_100007 = "100007";
	
	/**农场地块不正确*/
	public static final String FARM_ERROR_100008 = "100008";
	
	/**没有查询到农场*/
	public static final String FARM_ERROR_100010 = "100010";
	/**重复Email*/
	public static final String FARM_ERROR_100011 = "100011";
	/**没有找到指定城市的天气情况*/
	public static final String WEATHER_GET_ERROR_100009 = "100009";
	
	/**不能新增大于当前日期的日志*/
	public static final String FARM_ERROR_100012 = "100012";
	
	//加工批次详情不能为空
	public static final String ORIGIN_ERROR_100013 = "100013";
	
	//采收批次编号不能为空
	public static final String ORIGIN_ERROR_100014 = "100014";
		
	//发货单编号不能为空
	public static final String ORIGIN_ERROR_100015 = "100015";
	
	//加工批次编号不能为空
	public static final String ORIGIN_ERROR_100016 = "100016";
	
	//发货单详情不能为空
	public static final String ORIGIN_ERROR_100017 = "100017";
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
