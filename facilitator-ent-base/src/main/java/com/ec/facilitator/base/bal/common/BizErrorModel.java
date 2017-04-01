package com.ec.facilitator.base.bal.common;

import java.util.List;

/**
 * 业务error.
 */
public class BizErrorModel {
	
	/** 登录相关错误*/
	public static final String LOGIN_ERROR = "app.login.error";
	
	/** 登录相关错误*/
	public static final String FARM_ERROR = "app.farm.error";
	
	/** 天气相关错误*/
	public static final String WEATHER_GET_ERROR = "app.weather.error";
	
	/** 农场溯源相关错误*/
	public static final String ORIGIN_ERROR = "app.origin.error";
	
	/**  错误信息*/
	private String reason;
	
	/**  具体错误信息*/
	private List<BizErrorItemModel> errors;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<BizErrorItemModel> getErrors() {
		return errors;
	}

	public void setErrors(List<BizErrorItemModel> errors) {
		this.errors = errors;
	}
	
}
