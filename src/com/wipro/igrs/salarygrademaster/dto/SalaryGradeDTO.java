package com.wipro.igrs.salarygrademaster.dto;

public class SalaryGradeDTO {
	
	private String salCompGradeId;
	private String gradeId;
	private String componentId;
	private String gradeName;
	private String componentValue;
	private String empId;
	
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getComponentValue() {
		return componentValue;
	}
	public void setComponentValue(String componentValue) {
		this.componentValue = componentValue;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getSalCompGradeId() {
		return salCompGradeId;
	}
	public void setSalCompGradeId(String salCompGradeId) {
		this.salCompGradeId = salCompGradeId;
	}
	
	

}
