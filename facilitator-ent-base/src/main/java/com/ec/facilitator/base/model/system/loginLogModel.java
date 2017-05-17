package com.ec.facilitator.base.model.system;

import java.util.Date;

import com.ec.facilitator.base.model.common.JQGridRequestModel;

/**
 * 登录日志
 * @author 张荣英
 * @date 2017年5月12日 上午10:15:26
 */
public class loginLogModel extends JQGridRequestModel{

	private Integer id;
	private Integer userId;
	private Date loginTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}