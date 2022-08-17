package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="remarks")
public class RemarkList implements Serializable{
	private ArrayList<RmkList> remarks;
	@XmlElement(name="remarks")
	public ArrayList<RmkList> getRemarks() {
		return remarks;
	}

	public void setRemarks(ArrayList<RmkList> remarks) {
		this.remarks = remarks;
	}
	
}
