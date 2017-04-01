package com.ec.facilitator.base.model.system;

/**
 * 角色功能中间表Model
 * @author 张荣英
 * @date 2016年6月27日 下午2:46:53
 */

public class SysRoleFuncModel {


	private Integer id;
	private Integer roleId;
	private Integer funcId;

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

	public Integer getFuncId() {
		return this.funcId;
	}

	public void setFuncId(Integer funcId) {
		this.funcId = funcId;
	}

}