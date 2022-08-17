package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="mortgageRemarks")
public class MortgageRemarkList implements Serializable{
	private ArrayList<MortgageRemark> mortgageRemark;
	@XmlElement(name="mortgageRemark")
	public ArrayList<MortgageRemark> getMortgageRemark() {
		return mortgageRemark;
	}

	public void setMortgageRemark(ArrayList<MortgageRemark> mortgageRemark) {
		this.mortgageRemark = mortgageRemark;
	}
	
}
