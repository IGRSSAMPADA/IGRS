package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="trees")
public class NewTreeList implements Serializable {
	private ArrayList<TreeNewDTO> tree;
	@XmlElement(name="tree")
	public ArrayList<TreeNewDTO> getTree() {
		return tree;
	}

	public void setTree(ArrayList<TreeNewDTO> tree) {
		this.tree = tree;
	}
	
}
