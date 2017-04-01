package com.ec.facilitator.base.model.system;

/**
 * 组织机构Model
 * @author 张荣英
 * @date 2016年6月27日 下午2:43:54
 */

public class SysOrgModel{

	private Integer id;
	private String name;
	private Integer parentOrgId;
	private String code;
	private String companyCode;
	private Integer farmId;
	private Short status;
	private Integer rank;
	
	

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
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

	public Integer getParentOrgId() {
		return this.parentOrgId;
	}

	public void setParentOrgId(Integer parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Integer getFarmId() {
		return farmId;
	}

	public void setFarmId(Integer farmId) {
		this.farmId = farmId;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
}