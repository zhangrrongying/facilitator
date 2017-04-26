package com.ec.facilitator.base.model.fac;

import java.util.Date;

/**
 * 项目供应商中间表Model
 * @author 张荣英
 * @date 2017年4月16日 下午1:49:21
 */
public class FacSupplierProjectRelativeModel{
	
	private Integer id;
	private Integer supplierId;
	private Integer projectId;
	private Date bidTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Date getBidTime() {
		return bidTime;
	}
	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}
}