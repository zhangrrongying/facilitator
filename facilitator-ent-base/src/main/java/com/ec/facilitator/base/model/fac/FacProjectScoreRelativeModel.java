package com.ec.facilitator.base.model.fac;

import java.math.BigDecimal;

/**
 * 项目评分详情
 * @author 张荣英
 * @date 2017年4月16日 下午1:49:21
 */
public class FacProjectScoreRelativeModel{
	
	private Integer id;
	private Integer projectId;
	private Integer projectScoreId;
	private BigDecimal score;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getProjectScoreId() {
		return projectScoreId;
	}
	public void setProjectScoreId(Integer projectScoreId) {
		this.projectScoreId = projectScoreId;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
}