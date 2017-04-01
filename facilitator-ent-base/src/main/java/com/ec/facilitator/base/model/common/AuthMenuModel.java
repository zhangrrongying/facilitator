package com.ec.facilitator.base.model.common;

import java.util.List;

/**
 * 功能菜单
 * @author ryan
 * @date 2016年1月19日 下午2:11:10
 */
public class AuthMenuModel {

	private int id;
	private int parentId;
	private String name;
	private String funcKey;
	private String icon;
	private List<AuthMenuModel> childMenu;
	
	public List<AuthMenuModel> getChildMenu() {
		return childMenu;
	}
	public void setChildMenu(List<AuthMenuModel> childFunc) {
		this.childMenu = childFunc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFuncKey() {
		return funcKey;
	}
	public void setFuncKey(String funcKey) {
		this.funcKey = funcKey;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
