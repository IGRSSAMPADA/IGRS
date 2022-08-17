package com.wipro.igrs.empmgmt.dto;

import java.io.Serializable;

public class DeptExamDTO implements Serializable{
	private String employeeId;
	private String deptTxnNo;
	private String nameOfExam;
	private String examDate;
	private String examsOrganizingAuthority;
	private String placeOfExam;
	private String result;
	private String resultDate;
	private String examsComments;
	private String createdBy;
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getDeptTxnNo() {
		return deptTxnNo;
	}
	public void setDeptTxnNo(String deptTxnNo) {
		this.deptTxnNo = deptTxnNo;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public String getExamsComments() {
		return examsComments;
	}
	public void setExamsComments(String examsComments) {
		this.examsComments = examsComments;
	}
	public String getExamsOrganizingAuthority() {
		return examsOrganizingAuthority;
	}
	public void setExamsOrganizingAuthority(String examsOrganizingAuthority) {
		this.examsOrganizingAuthority = examsOrganizingAuthority;
	}
	public String getNameOfExam() {
		return nameOfExam;
	}
	public void setNameOfExam(String nameOfExam) {
		this.nameOfExam = nameOfExam;
	}
	public String getPlaceOfExam() {
		return placeOfExam;
	}
	public void setPlaceOfExam(String placeOfExam) {
		this.placeOfExam = placeOfExam;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResultDate() {
		return resultDate;
	}
	public void setResultDate(String resultDate) {
		this.resultDate = resultDate;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
}
