/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   FnActivityDTO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DTO Class for Rolefunctionactivitymapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  18th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.functionactivitymappingmaster.dto;
import java.util.ArrayList;
import com.wipro.igrs.functionactivitymappingmaster.dto.FnActivityDTO;
//import com.wipro.igrs.Log4J.*;


public class FnActivityDTO{
	 public FnActivityDTO(){
    	//LoggerMsg.info("we are in FnActivityDTO");
    
    }
	 
		private String activeFlag;
		private String status;
		private String roleId;
		private String paymentFlag;
		private String moduleId;
		private String submoduleId;
		private String functionId;
		private String activityId;
		private String radioButton;
		private String pageName;
		private String name;
		private String value;
	    ArrayList functionList;
	    ArrayList roleList;
	    ArrayList activityList;
	    ArrayList moduleList;
	    ArrayList submoduleList;
	    ArrayList fnactivityList;
	    
	    
	    private String checkedactivitylist;
	    private String uncheckedactivitylist;
	    
	    private String moduleName;
	    private String subModuleName;
	    private String functionName;
	    private String activityName;
	    
	    
	    public String getModuleName() {
			return moduleName;
		}
		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}
		public String getSubModuleName() {
			return subModuleName;
		}
		public void setSubModuleName(String subModuleName) {
			this.subModuleName = subModuleName;
		}
		public String getFunctionName() {
			return functionName;
		}
		public void setFunctionName(String functionName) {
			this.functionName = functionName;
		}
		public String getActivityName() {
			return activityName;
		}
		public void setActivityName(String activityName) {
			this.activityName = activityName;
		}

		
	    
	    
	    
	   
		/**
		 * @return Returns the name.
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name The name to set.
		 */ 
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * @return Returns the value.
		 */
		public String getValue() {
			return value;
		}
		
		/**
		 * @param value The value to set.
		 */ 
		public void setValue(String value) {
			this.value = value;
		}
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
		 * @return Returns the functionList.
		 */
		public ArrayList getFunctionList() {
			return functionList;
		}
		/**
		 * @param functionList The functionList to set.
		 */
		public void setFunctionList(ArrayList functionList) {
			this.functionList = functionList;
		}
		/**
		 * @return Returns the roleList.
		 */
		public ArrayList getRoleList() {
			return roleList;
		}
		/**
		 * @param roleList The roleList to set.
		 */
		public void setRoleList(ArrayList roleList) {
			this.roleList = roleList;
		}
		/**
		 * @return Returns the activityList.
		 */
		public ArrayList getActivityList() {
			return activityList;
		}
		/**
		 * @param activityList The activityList to set.
		 */
		public void setActivityList(ArrayList activityList) {
			this.activityList = activityList;
		}
		/**
		 * @return Returns the moduleList.
		 */
		public ArrayList getModuleList() {
			return moduleList;
		}
		/**
		 * @param moduleList The moduleList to set.
		 */
		public void setModuleList(ArrayList moduleList) {
			this.moduleList = moduleList;
		}
		/**
		 * @return Returns the submoduleList.
		 */
		public ArrayList getSubmoduleList() {
			return submoduleList;
		}
		/**
		 * @param submoduleList The submoduleList to set.
		 */
		public void setSubmoduleList(ArrayList submoduleList) {
			this.submoduleList = submoduleList;
		}
		/**
		 * @return Returns the fnactivityList.
		 */
		public ArrayList getFnactivityList() {
			return fnactivityList;
		}
		/**
		 * @param fnactivityList The fnactivityList to set.
		 */
		public void setFnactivityList(ArrayList fnactivityList) {
			this.fnactivityList = fnactivityList;
		}
		public void setRadioButton(String radioButton) {
			this.radioButton = radioButton;
		}
		public String getRadioButton() {
			return radioButton;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getStatus() {
			return status;
		}
		public void setCheckedactivitylist(String checkedactivitylist) {
			this.checkedactivitylist = checkedactivitylist;
		}
		public String getCheckedactivitylist() {
			return checkedactivitylist;
		}
		public void setUncheckedactivitylist(String uncheckedactivitylist) {
			this.uncheckedactivitylist = uncheckedactivitylist;
		}
		public String getUncheckedactivitylist() {
			return uncheckedactivitylist;
		}
		
}