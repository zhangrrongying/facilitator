package com.ec.facilitator.base.model.fac;

/**
 * 项目Model
 * @author 张荣英
 * @date 2017年4月16日 下午1:49:21
 */
public class FacProjectBidRequestModel{
	
	private Integer projectId;
	private Integer bidType;
	
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getBidType() {
		return bidType;
	}
	public void setBidType(Integer bidType) {
		this.bidType = bidType;
	}
}