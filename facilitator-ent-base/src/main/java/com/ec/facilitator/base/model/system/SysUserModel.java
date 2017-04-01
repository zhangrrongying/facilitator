package com.ec.facilitator.base.model.system;

import com.ec.facilitator.base.model.common.JQGridRequestModel;

/**
 * 用户Model
 * @author 张荣英
 * @date 2016年6月27日 下午2:48:21
 */

public class SysUserModel extends JQGridRequestModel{
	
	public static final short POWER_ORG = 1;
	public static final short POWER_COMPANY = 2;

	private Integer id;
	private String name;
	private String userName;
	private String email;
	private String phone;
	private String password;
	private String companyCode;
	private String companyName;
	private String roleName;
	private Integer roleId;
	private int[] orgIdArr;
	private String	orgId;
	private String orgName;
	private Short status;
	private Short power;
	private String farmName;
	
	
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int[] getOrgIdArr() {
		return orgIdArr;
	}

	public void setOrgIdArr(int[] orgIdArr) {
		this.orgIdArr = orgIdArr;
	}


	
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getPower() {
		return power;
	}

	public void setPower(Short power) {
		this.power = power;
	}

	public String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}
}