package com.wipro.igrs.categorymaster.action;

import java.util.HashMap;

/**
 * ===========================================================================
 * File : UserDTO.java Description : Represents the user dto for user login
 * 
 * Author : Madan Mohan Created Date : Dec 11, 2007
 * 
 * ===========================================================================
 */
public class UserDTO extends BaseDTO {

	private HashMap funcPrivMap;
	private String changePasswordFlag;
	private String userCode;
	private String password;
	private String confirmPassword;
	private String sessionID;
	private String ipAddress;
	private String userId;
	private String globalId;
	private String roleCode;
	private String baseCountryCode;
	private String noOfAgentsAllowed;
	private String blockUnitsFrequency;
	private String blockUnitsNumber;
	private String startDate;
	private String endDate;
	private String userValue;
	private String employeeFlag;
	private String instanceResourceId;
	private String resourceId;

	public void setChangePasswordFlag(String changePasswordFlag) {
		this.changePasswordFlag = changePasswordFlag;
	}

	public String getChangePasswordFlag() {
		return changePasswordFlag;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}

	public String getGlobalId() {
		return globalId;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setBaseCountryCode(String baseCountryCode) {
		this.baseCountryCode = baseCountryCode;
	}

	public String getBaseCountryCode() {
		return baseCountryCode;
	}

	public void setNoOfAgentsAllowed(String noOfAgentsAllowed) {
		this.noOfAgentsAllowed = noOfAgentsAllowed;
	}

	public String getNoOfAgentsAllowed() {
		return noOfAgentsAllowed;
	}

	public void setBlockUnitsFrequency(String blockUnitsFrequency) {
		this.blockUnitsFrequency = blockUnitsFrequency;
	}

	public String getBlockUnitsFrequency() {
		return blockUnitsFrequency;
	}

	public void setBlockUnitsNumber(String blockUnitsNumber) {
		this.blockUnitsNumber = blockUnitsNumber;
	}

	public String getBlockUnitsNumber() {
		return blockUnitsNumber;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setUserValue(String userValue) {
		this.userValue = userValue;
	}

	public String getUserValue() {
		return userValue;
	}

	public void setFuncPrivMap(HashMap funcPrivMap) {
		this.funcPrivMap = funcPrivMap;
	}

	public HashMap getFuncPrivMap() {
		return funcPrivMap;
	}

	public void setEmployeeFlag(String employeeFlag) {
		this.employeeFlag = employeeFlag;
	}

	public String getEmployeeFlag() {
		return employeeFlag;
	}

	public void setInstanceResourceId(String instanceResourceId) {
		this.instanceResourceId = instanceResourceId;
	}

	public String getInstanceResourceId() {
		return instanceResourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceId() {
		return resourceId;
	}
}
