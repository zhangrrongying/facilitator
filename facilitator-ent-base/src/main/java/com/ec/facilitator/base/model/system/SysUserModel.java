package com.ec.facilitator.base.model.system;

import com.ec.facilitator.base.model.common.JQGridRequestModel;

/**
 * 用户Model
 * @author 张荣英
 * @date 2017年4月6日 下午9:11:15
 */
public class SysUserModel extends JQGridRequestModel{
	
	private Integer id;
	private String name;
	private String userName;
	private String email;
	private String phone;
	private String password;
	private String roleName;
	private Integer roleId;
	private Short status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
}