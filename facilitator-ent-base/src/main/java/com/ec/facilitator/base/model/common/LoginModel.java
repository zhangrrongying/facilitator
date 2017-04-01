package com.ec.facilitator.base.model.common;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "LoginModel", description="登录条件实体类")
public class LoginModel {
	private String loginName;
	private String password;
	
	public String getLoginName() {
		return loginName;
	}
	@ApiModelProperty(value = "登录名(用户名/eamil/phone)", required = true)
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getPassword() {
		return password;
	}
	@ApiModelProperty(value = "登录密码")
	public void setPassword(String password) {
		this.password = password;
	}

}
