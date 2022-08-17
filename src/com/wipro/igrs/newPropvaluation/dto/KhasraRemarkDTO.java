package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="khasraRemarks")
public class KhasraRemarkDTO implements Serializable{
	private ArrayList<SourceOfIrrigationList> sourceofIrrigations;
	private ArrayList<NewTreeList> trees;
	private ArrayList<RemarkList> remarks;
	@XmlElement
	public ArrayList<SourceOfIrrigationList> getSourceofIrrigations() {
		return sourceofIrrigations;
	}
	public void setSourceofIrrigations(ArrayList<SourceOfIrrigationList> sourceofIrrigations) {
		this.sourceofIrrigations = sourceofIrrigations;
	}
	@XmlElement(name="trees")
	public ArrayList<NewTreeList> getTrees() {
		return trees;
	}
	public void setTrees(ArrayList<NewTreeList> trees) {
		this.trees = trees;
	}
	@XmlElement(name="remarks")
	public ArrayList<RemarkList> getRemarks() {
		return remarks;
	}
	public void setRemarks(ArrayList<RemarkList> remarks) {
		this.remarks = remarks;
	}
	
}
