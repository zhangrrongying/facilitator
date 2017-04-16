package com.ec.facilitator.base.model.fac;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * 供应商Nodel
 * @author 张荣英
 * @date 2017年4月16日 下午2:10:31
 */
public class FacSupplierModel{
	
	private Integer id;
	//供应商名称
	private String name;
	//信用代码
	private String serialNum;
	//企业简称
	private String linkName;
	//企业法人
	private String corporation;
	//企业法人电话
	private Integer corporationPhone;
	//注册地址
	private String registerAddress;
	//办公地址
	private String  workAddress;
	//注册资本
	private BigDecimal registeredCapital;
	//联系人
	private String  linkMan;
	//联系电话
	private Integer  linkPhone;
	//座机
	private String  deskPhone;
	//邮箱
	private String  email;
	//资质证书名称
	private String  certificationName;
	//资质证书等级
	private String  certificationLevel;
	//技术负责人
	private Integer  leader;
	//技术人员
	private String  artisan;
	//营业执照
	private String  licenseImgs;
	//资质证书Img
	private String  certificateImgs;
	//身份证
	private String  identityCardImgs;
	//企业简介
	private String  Description;
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
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getCorporation() {
		return corporation;
	}
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	public Integer getCorporationPhone() {
		return corporationPhone;
	}
	public void setCorporationPhone(Integer corporationPhone) {
		this.corporationPhone = corporationPhone;
	}
	public String getRegisterAddress() {
		return registerAddress;
	}
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}
	public String getWorkAddress() {
		return workAddress;
	}
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	public BigDecimal getRegisteredCapital() {
		return registeredCapital;
	}
	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public Integer getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(Integer linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getDeskPhone() {
		return deskPhone;
	}
	public void setDeskPhone(String deskPhone) {
		this.deskPhone = deskPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCertificationName() {
		return certificationName;
	}
	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}
	public String getCertificationLevel() {
		return certificationLevel;
	}
	public void setCertificationLevel(String certificationLevel) {
		this.certificationLevel = certificationLevel;
	}
	public Integer getLeader() {
		return leader;
	}
	public void setLeader(Integer leader) {
		this.leader = leader;
	}
	public String getArtisan() {
		return artisan;
	}
	public void setArtisan(String artisan) {
		this.artisan = artisan;
	}
	public String getLicenseImgs() {
		return licenseImgs;
	}
	public void setLicenseImgs(String licenseImgs) {
		this.licenseImgs = licenseImgs;
	}
	public String getCertificateImgs() {
		return certificateImgs;
	}
	public void setCertificateImgs(String certificateImgs) {
		this.certificateImgs = certificateImgs;
	}
	public String getIdentityCardImgs() {
		return identityCardImgs;
	}
	public void setIdentityCardImgs(String identityCardImgs) {
		this.identityCardImgs = identityCardImgs;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
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
}