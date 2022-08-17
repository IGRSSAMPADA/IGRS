package com.wipro.igrs.empreportmgr.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class EmpReportMgrForm extends org.apache.struts.action.ActionForm 
{

	private String empId;
	private String empSupervisorName;
	private String empSupervisorRole;
	private String empSupervisorId;
	private String role;
	private ArrayList roles;
	private ArrayList managers;
	
	
	

	public ArrayList getRoles() {
		return roles;
	}

	public void setRoles(ArrayList roles) {
		this.roles = roles;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		//super.reset(mapping, request);
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpSupervisorName() {
		return empSupervisorName;
	}

	public void setEmpSupervisorName(String empSupervisorName) {
		this.empSupervisorName = empSupervisorName;
	}

	public String getEmpSupervisorRole() {
		return empSupervisorRole;
	}

	public void setEmpSupervisorRole(String empSupervisorRole) {
		this.empSupervisorRole = empSupervisorRole;
	}

	public String getEmpSupervisorId() {
		return empSupervisorId;
	}

	public void setEmpSupervisorId(String empSupervisorId) {
		this.empSupervisorId = empSupervisorId;
	}

	public ArrayList getManagers() {
		return managers;
	}

	public void setManagers(ArrayList managers) {
		this.managers = managers;
	}

	
	
}
