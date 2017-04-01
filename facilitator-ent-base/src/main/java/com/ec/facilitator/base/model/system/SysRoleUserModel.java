package com.ec.facilitator.base.model.system;

/**
 * 用户角色中间表Model
 * @author 张荣英
 * @date 2016年6月27日 下午2:47:32
 */

public class SysRoleUserModel {

	private Integer id;
	private Integer roleId;
	private Integer userId;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}