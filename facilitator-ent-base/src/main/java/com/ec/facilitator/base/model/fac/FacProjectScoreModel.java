package com.ec.facilitator.base.model.fac;

/**
 * 项目评分指标
 * @author 张荣英
 * @date 2017年4月16日 下午1:49:21
 */
public class FacProjectScoreModel{
	
	private Integer id;
	private String name;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}