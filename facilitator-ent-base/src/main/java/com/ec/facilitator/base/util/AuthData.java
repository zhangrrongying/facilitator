package com.ec.facilitator.base.util;

import java.util.Date;
import java.util.List;

public class AuthData {
	public final static String AUTH_DATA_KEY = "auth_data_key";
	
	private int Id;
	private String UserName;
	private List<String> permissionCode;
	private boolean keepLogin;
	private Date updateTokenTime;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public List<String> getPermissionCode() {
		return permissionCode;
	}
	public void setPermissionCode(List<String> permissionCode) {
		this.permissionCode = permissionCode;
	}
	
	public boolean isKeepLogin() {
		return keepLogin;
	}
	public void setKeepLogin(boolean keepLogin) {
		this.keepLogin = keepLogin;
	}
	public Date getUpdateTokenTime() {
		return updateTokenTime;
	}
	public void setUpdateTokenTime(Date updateTokenTime) {
		this.updateTokenTime = updateTokenTime;
	}
	
}
