package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;

public class TreeDTO implements Serializable{
	private String sagunTree;
	private String saalTree;
	private String fruitTree;
	private String less45Tree;
	public String getSagunTree() {
		return sagunTree;
	}
	public void setSagunTree(String sagunTree) {
		this.sagunTree = sagunTree;
	}
	public String getSaalTree() {
		return saalTree;
	}
	public void setSaalTree(String saalTree) {
		this.saalTree = saalTree;
	}
	public String getFruitTree() {
		return fruitTree;
	}
	public void setFruitTree(String fruitTree) {
		this.fruitTree = fruitTree;
	}
	public String getLess45Tree() {
		return less45Tree;
	}
	public void setLess45Tree(String less45Tree) {
		this.less45Tree = less45Tree;
	}
}
