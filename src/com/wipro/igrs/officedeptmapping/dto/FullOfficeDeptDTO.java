package com.wipro.igrs.officedeptmapping.dto;

import java.io.Serializable;

public class FullOfficeDeptDTO implements Serializable{
	
	
	private String officeDeptId;
	private String officeId;
	private String deptId;
	private String officeName;
	private String deptName;
	
	
	
	public String getOfficeDeptId() {
		return officeDeptId;
	}
	public void setOfficeDeptId(String officeDeptId) {
		this.officeDeptId = officeDeptId;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	

}
