package com.ec.facilitator.base.model.fac;

/**
 * 项目供应商中间表Model
 * @author 张荣英
 * @date 2017年4月16日 下午1:49:21
 */
public class FacSupplierProjectRelativeModel{
	
	private Integer id;
	private Integer supplierId;
	private Integer projectId;
	
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
}