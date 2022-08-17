/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   FnActivityForm.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the FormBean Class for Rolefunctionactivitymapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  18th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.functionactivitymappingmaster.form;
import java.util.ArrayList;

import org.apache.struts.action.ActionForm;
import com.wipro.igrs.functionactivitymappingmaster.dto.FnActivityDTO;


public class FnActivityForm extends ActionForm {
	
	private String functionId;
	private String activityId;
	private String activeFlag;
	private String roleId;
	private String paymentFlag;
	private String moduleId;
	private String submoduleId;
	private String pageName;
	private String actionname;
	
	
	private String disclamer;
	private ArrayList moduleList;
	private ArrayList submoduleList;
	private ArrayList functionList;
	private ArrayList activityList;
	private ArrayList roleList;
	
	private String[] selectedActVal;
	
	public String[] getSelectedActVal() {
		return selectedActVal;
	}
	public void setSelectedActVal(String[] selectedActVal) {
		this.selectedActVal = selectedActVal;
	}
	public ArrayList getRoleList() {
		return roleList;
	}
	public void setRoleList(ArrayList roleList) {
		this.roleList = roleList;
	}
	public ArrayList getModuleList() {
		return moduleList;
	}
	public void setModuleList(ArrayList moduleList) {
		this.moduleList = moduleList;
	}
	public ArrayList getSubmoduleList() {
		return submoduleList;
	}
	public void setSubmoduleList(ArrayList submoduleList) {
		this.submoduleList = submoduleList;
	}
	public ArrayList getFunctionList() {
		return functionList;
	}
	public void setFunctionList(ArrayList functionList) {
		this.functionList = functionList;
	}
	public ArrayList getActivityList() {
		return activityList;
	}
	public void setActivityList(ArrayList activityList) {
		this.activityList = activityList;
	}
	FnActivityDTO dto=new FnActivityDTO();


	/**
	 * @return Returns the functionId.
	 */
	public String getFunctionId() {
		return functionId;
	}
	/**
	 * @param functionId The functionId to set.
	 */

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	/**
	 * @return Returns the activityId.
	 */
	public String getActivityId() {
		return activityId;
	}
	/**
	 * @param activityId The activityId to set.
	 */

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	/**
	 * @return Returns the activeFlag.
	 */

	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag The activeFlag to set.
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	/**
	 * @return Returns the roleId.
	 */

	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId The roleId to set.
	 */

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return Returns the paymentFlag.
	 */

	public String getPaymentFlag() {
		return paymentFlag;
	}
	/**
	 * @param paymentFlag The paymentFlag to set.
	 */

	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}
	/**
	 * @return Returns the moduleId.
	 */

	public String getModuleId() {
		return moduleId;
	}
	/**
	 * @param moduleId The moduleId to set.
	 */

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @return Returns the submoduleId.
	 */
	public String getSubmoduleId() {
		return submoduleId;
	}
	/**
	 * @param submoduleId The submoduleId to set.
	 */

	public void setSubmoduleId(String submoduleId) {
		this.submoduleId = submoduleId;
	}
	/**
	 * @return Returns the pageName.
	 */

	public String getPageName() {
		return pageName;
	}
	/**
	 * @param pageName The pageName to set.
	 */

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	/**
	 * @return Returns the dto.
	 */
	public FnActivityDTO getDto() {
		return dto;
	}
	/**
	 * @param dto The dto to set.
	 */

	public void setDto(FnActivityDTO dto) {
		this.dto = dto;
	}
	public void setActionname(String actionname) {
		this.actionname = actionname;
	}
	public String getActionname() {
		return actionname;
	}
	public void setDisclamer(String disclamer) {
		this.disclamer = disclamer;
	}
	public String getDisclamer() {
		return disclamer;
	} 
	
    
}