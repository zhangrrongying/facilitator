package com.ec.facilitator.base.model.system;

import java.util.Date;

import com.ec.facilitator.base.model.common.JQGridRequestModel;

/**
 * 角色Model
 * @author 张荣英
 * @date 2017年4月6日 下午9:40:05
 */
public class SysRoleModel extends JQGridRequestModel{

	private Integer id;
	private String name;
	private Date createDate;
	private String description;
	private Short status;
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
}