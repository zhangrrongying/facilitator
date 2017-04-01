package com.ec.facilitator.base.model.common;

import java.util.List;


/**
 * JQGrid Response类
 * @author 张荣英
 * @date 2016年6月30日 上午11:53:56
 */
public class JQGridResponseModel<T> {
	//总页数
	private int total;  
	//当前页
    private int page; 
    //本次查询的记录数
    private int records; 
    private List<T> rows;
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	} 
}
