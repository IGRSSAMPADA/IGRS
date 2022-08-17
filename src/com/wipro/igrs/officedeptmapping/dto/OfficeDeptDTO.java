package com.wipro.igrs.officedeptmapping.dto;

import java.io.Serializable;

public class OfficeDeptDTO implements Serializable{
	
	private String officeId;
	private String deptId;
	private String officeDeptId;
	
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
	public String getOfficeDeptId() {
		return officeDeptId;
	}
	public void setOfficeDeptId(String officeDeptId) {
		this.officeDeptId = officeDeptId;
	}

}
