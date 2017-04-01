package com.ec.facilitator.base.model.system;

import java.util.List;

public class ListParamMap<T> {
	private List<T> moveNodes;
	private int[] delNodes;
	
	public List<T> getMoveNodes() {
		return moveNodes;
	}
	public void setMoveNodes(List<T> moveNodes) {
		this.moveNodes = moveNodes;
	}
	public int[] getDelNodes() {
		return delNodes;
	}
	public void setDelNodes(int[] delNodes) {
		this.delNodes = delNodes;
	}
	

	
	
	
}
