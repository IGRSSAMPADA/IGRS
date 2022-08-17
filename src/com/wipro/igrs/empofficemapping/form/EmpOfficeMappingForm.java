package com.wipro.igrs.empofficemapping.form;

import java.util.ArrayList;

import com.wipro.igrs.baseaction.form.BaseForm;

public class EmpOfficeMappingForm extends BaseForm {
	private String empId;
	private String empName;
	private String deptId;
	private String officeId;
	private String roleId;
	
	private ArrayList allDepts;
	private ArrayList allOffices;
	private ArrayList allRoles;
	
	

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public ArrayList getAllDepts() {
		return allDepts;
	}

	public void setAllDepts(ArrayList allDepts) {
		this.allDepts = allDepts;
	}

	public ArrayList getAllOffices() {
		return allOffices;
	}

	public void setAllOffices(ArrayList allOffices) {
		this.allOffices = allOffices;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public ArrayList getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(ArrayList allRoles) {
		this.allRoles = allRoles;
	}

}
