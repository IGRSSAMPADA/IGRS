package com.wipro.igrs.empofficemapping.dto;

import java.io.Serializable;

public class EmpOfficeMappingDTO implements Serializable {
	
	private String empOfficeMappingId;
	private String empId;
	private String empName;
	private String officeId;
	private String officeName;
	private String roleId;
	private String roleName;
	private String deptId;
	private String deptName;
	
	public String getEmpOfficeMappingId() {
		return empOfficeMappingId;
	}
	public void setEmpOfficeMappingId(String empOfficeMappingId) {
		this.empOfficeMappingId = empOfficeMappingId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
}
