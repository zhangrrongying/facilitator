package com.ec.facilitator.base.model.common;

/**
 * JQGrid PostDate类
 * @author 张荣英
 * @date 2017年4月7日 下午9:57:37
 */
public class JQGridRequestModel {
	protected Boolean _search;  
	protected Long nd;  
	protected int page;  
	protected int rows;  
	protected String sidx;  
	protected String sord;
    
	public Boolean get_search() {
		return _search;
	}
	public void set_search(Boolean _search) {
		this._search = _search;
	}
	public Long getNd() {
		return nd;
	}
	public void setNd(Long nd) {
		this.nd = nd;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
}
