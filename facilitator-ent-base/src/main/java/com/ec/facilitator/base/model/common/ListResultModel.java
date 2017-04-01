/**   
 * @Title: PagerModel.java
 * @Package com.sunyuki.ec.group.base.model.common
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuchun  
 * @date 2016年1月25日 下午5:05:36
 * @version V1.0   
 */
package com.ec.facilitator.base.model.common;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author 张荣英
 * @date 2016年4月14日 下午4:43:05
 */
public class ListResultModel<T> {

	@ApiModelProperty(value = "总数")
	private int totalSize;
	@ApiModelProperty(value = "结果集")
	private List<T> rows;
	@ApiModelProperty(value = "每页显示多少条")
	private int limit = 10;
	@ApiModelProperty(value = "分页起始位")
	private int startIndex = 0;
	
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
}
