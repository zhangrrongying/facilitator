package com.ec.facilitator.base.model.common;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "登录结果返回类")
public class LoginResultModel {
	
	private String authToken;
	private String authTokenSSL;
	private String name;
	
	@ApiModelProperty(value = "获取非SSL请求的AuthToken")
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	@ApiModelProperty(value = "认证后的授权令牌")
	public String getAuthTokenSSL() {
		return authTokenSSL;
	}
	public void setAuthTokenSSL(String authTokenSSL) {
		this.authTokenSSL = authTokenSSL;
	}
	
	@ApiModelProperty(value = "会员账户名")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
