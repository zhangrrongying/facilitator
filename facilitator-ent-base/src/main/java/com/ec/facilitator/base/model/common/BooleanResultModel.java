package com.ec.facilitator.base.model.common;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "公共的返回布尔值的实体")
public class BooleanResultModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(value = "结果")
	private Boolean result;
	@ApiModelProperty(value = "消息")
	private String msg;
	@ApiModelProperty(value = "主键ID")
	private Integer id;

	@ApiModelProperty(value = "结果")
	public Boolean getResult() {
		return result;
	}
	@ApiModelProperty(value = "结果")
	public void setResult(Boolean result) {
		this.result = result;
	}
	@ApiModelProperty(value = "消息")
	public String getMsg() {
		return msg;
	}
	@ApiModelProperty(value = "消息")
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@ApiModelProperty(value = "主键ID")
	public Integer getId() {
		return id;
	}
	@ApiModelProperty(value = "主键ID")
	public void setId(Integer id) {
		this.id = id;
	}
}
