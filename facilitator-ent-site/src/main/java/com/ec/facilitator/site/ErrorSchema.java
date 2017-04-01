package com.ec.facilitator.site;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "error实体类")
public class ErrorSchema {
	@ApiModelProperty(value = "error信息")
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

