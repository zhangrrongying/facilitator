package com.ec.facilitator.base.model.fac;

/**
 * 供应商技术人员Model
 * @author 张荣英
 * @date 2017年4月16日 下午1:49:21
 */
public class FacSupplierArtisanModel{
	
	private Integer id;
	private String name;
	private Integer phone;
	private String certificateImgs;
	
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
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	public String getCertificateImgs() {
		return certificateImgs;
	}
	public void setCertificateImgs(String certificateImgs) {
		this.certificateImgs = certificateImgs;
	}
}