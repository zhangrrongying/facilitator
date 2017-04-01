package com.ec.facilitator.base.model.common;

import java.util.List;
import java.util.Map;

public class TreeModel {
	private String text;
	private Integer id;
	private String tags;
	private List<TreeModel> nodes;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public List<TreeModel> getNodes() {
		return nodes;
	}
	public void setNodes(List<TreeModel> nodes) {
		this.nodes = nodes;
	}
	
	
	
}
