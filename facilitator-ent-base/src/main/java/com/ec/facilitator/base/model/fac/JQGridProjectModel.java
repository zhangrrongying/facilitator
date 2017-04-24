package com.ec.facilitator.base.model.fac;

import com.ec.facilitator.base.model.common.JQGridRequestModel;

public class JQGridProjectModel extends JQGridRequestModel{
	
	private String name;
	private Integer projectTypeId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(Integer projectTypeId) {
		this.projectTypeId = projectTypeId;
	}
}
