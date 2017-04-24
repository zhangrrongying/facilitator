package com.ec.facilitator.base.model.fac;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * 项目Model
 * @author 张荣英
 * @date 2017年4月16日 下午1:49:21
 */
public class FacProjectModel{
	
	private Integer id;
	private String name;
	private Integer projectTypeId;
	private String serialNum;
	private BigDecimal investment;
	private String description;
	private Integer createUser;
	private Date createTime;
	
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
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getProjectTypeId() {
		return projectTypeId;
	}
	public void setProjectTypeId(Integer projectTypeId) {
		this.projectTypeId = projectTypeId;
	}
	public BigDecimal getInvestment() {
		return investment;
	}
	public void setInvestment(BigDecimal investment) {
		this.investment = investment;
	}
}