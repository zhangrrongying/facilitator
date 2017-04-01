package com.ec.facilitator.base.model.common;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "公共的获取布尔值的实体")
public class BooleanWithReasonModel extends BooleanResultModel{
	
	@ApiModelProperty(value = "原因")
	private String reason;

	@ApiModelProperty(value = "原因")
	public String getReason() {
		return reason;
	}
	@ApiModelProperty(value = "原因")
	public void setReason(String value) {
		this.reason = value;
	}
}
