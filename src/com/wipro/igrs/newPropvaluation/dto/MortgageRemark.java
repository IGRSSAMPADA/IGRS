package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="mortgageRemark")
public class MortgageRemark implements Serializable{
	private String mRemark;
	private String mtype;
	@XmlElement(name="mRemark")
	public String getMRemark() {
		return mRemark;
	}
	public void setMRemark(String mRemark) {
		this.mRemark = mRemark;
	}
	@XmlElement(name="mtype")
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	private String mgrRemark;
	private String mgrType;
	public String getMgrRemark() {
		return mgrRemark;
	}
	public void setMgrRemark(String mgrRemark) {
		this.mgrRemark = mgrRemark;
	}
	public String getMgrType() {
		return mgrType;
	}
	public void setMgrType(String mgrType) {
		this.mgrType = mgrType;
	}
	
}
