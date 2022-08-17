package com.wipro.igrs.empofficemapping.dto;

import java.io.Serializable;

public class DeptDTO implements Serializable{
	private String deptId;
	private String deptName;
	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
