package com.ec.facilitator.base.model.system;


/**
 * 系统权限Model
 * @author 张荣英
 * @date 2016年6月27日 下午2:42:18
 */

public class SysAuthFuncModel{

	private Integer id;
	private Integer parentKeyId;
	private String name;
	private String key;
	private Byte type;
	private String url;
	private String desc;
	private String icon;
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

	public Integer getParentKeyId() {
		return this.parentKeyId;
	}

	public void setParentKeyId(Integer parentKeyId) {
		this.parentKeyId = parentKeyId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Byte getType() {
		return this.type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}