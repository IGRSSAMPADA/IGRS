package com.wipro.igrs.empmgmt.dto;

import java.io.Serializable;

public class DeptTrainingDTO implements Serializable{

	   private String trainingNo; 
		private String trainingName;
		private String trainingLevel;
		private String organizingAuthority;
		private String orgainizingBody; 
		private String placeOfTraining;
		private String financialYear;
		private String trainingStartDate; 
		private String trainingEndDate;
		private String authorisingAuthority; 
		private String authorizationDate; 
		private String trainingCost; 
		private String trainingResult; 
		private String trainingComments;
		private String employeeId;
		public String getAuthorisingAuthority() {
			return authorisingAuthority;
		}
		public void setAuthorisingAuthority(String authorisingAuthority) {
			this.authorisingAuthority = authorisingAuthority;
		}
		public String getAuthorizationDate() {
			return authorizationDate;
		}
		public void setAuthorizationDate(String authorizationDate) {
			this.authorizationDate = authorizationDate;
		}
		public String getFinancialYear() {
			return financialYear;
		}
		public void setFinancialYear(String financialYear) {
			this.financialYear = financialYear;
		}
		public String getOrgainizingBody() {
			return orgainizingBody;
		}
		public void setOrgainizingBody(String orgainizingBody) {
			this.orgainizingBody = orgainizingBody;
		}
		public String getOrganizingAuthority() {
			return organizingAuthority;
		}
		public void setOrganizingAuthority(String organizingAuthority) {
			this.organizingAuthority = organizingAuthority;
		}
		public String getPlaceOfTraining() {
			return placeOfTraining;
		}
		public void setPlaceOfTraining(String placeOfTraining) {
			this.placeOfTraining = placeOfTraining;
		}
		public String getTrainingComments() {
			return trainingComments;
		}
		public void setTrainingComments(String trainingComments) {
			this.trainingComments = trainingComments;
		}
		public String getTrainingCost() {
			return trainingCost;
		}
		public void setTrainingCost(String trainingCost) {
			this.trainingCost = trainingCost;
		}
		public String getTrainingEndDate() {
			return trainingEndDate;
		}
		public void setTrainingEndDate(String trainingEndDate) {
			this.trainingEndDate = trainingEndDate;
		}
		public String getTrainingLevel() {
			return trainingLevel;
		}
		public void setTrainingLevel(String trainingLevel) {
			this.trainingLevel = trainingLevel;
		}
		public String getTrainingName() {
			return trainingName;
		}
		public void setTrainingName(String trainingName) {
			this.trainingName = trainingName;
		}
		public String getTrainingNo() {
			return trainingNo;
		}
		public void setTrainingNo(String trainingNo) {
			this.trainingNo = trainingNo;
		}
		public String getTrainingResult() {
			return trainingResult;
		}
		public void setTrainingResult(String trainingResult) {
			this.trainingResult = trainingResult;
		}
		public String getTrainingStartDate() {
			return trainingStartDate;
		}
		public void setTrainingStartDate(String trainingStartDate) {
			this.trainingStartDate = trainingStartDate;
		}
		public String getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}
		
	}

	
	

