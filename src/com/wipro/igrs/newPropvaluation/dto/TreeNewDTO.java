package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="tree")

public class TreeNewDTO implements Serializable{
	private String treeName;
	private String treeCount;
	@XmlElement
	public String getTreeName() {
		return treeName;
	}
	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}
	@XmlElement
	public String getTreeCount() {
		return treeCount;
	}
	public void setTreeCount(String treeCount) {
		this.treeCount = treeCount;
	}
	
}
