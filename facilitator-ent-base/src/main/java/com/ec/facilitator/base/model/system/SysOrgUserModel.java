package com.ec.facilitator.base.model.system;

/**
 * 用户组织机构中间表Model
 * @author 张荣英
 * @date 2016年6月27日 下午2:45:18
 */

public class SysOrgUserModel{

	private Integer id;
	private Integer orgId;
	private Integer userId;


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}