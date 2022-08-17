package com.wipro.igrs.empreportmgr.dto;

public class empReportMgrDto 
{
	private String empId;
	private String empSupervisorName;
	private String empSupervisorRole;
	private String empSupervisorId;
	
	
	public empReportMgrDto() {
		
	}
	public empReportMgrDto(String empId, String empSupervisorName,
			String empSupervisorRole, String empSupervisorId) {
		super();
		this.empId = empId;
		this.empSupervisorName = empSupervisorName;
		this.empSupervisorRole = empSupervisorRole;
		this.empSupervisorId = empSupervisorId;
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
	
	
}
