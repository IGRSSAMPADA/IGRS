package com.wipro.igrs.functiontorolemapping.dto;

import java.io.Serializable;



public class FunToRoleMappingDto implements Serializable
{
	private String funToRoleMappingid;
	private String functionId;
	private String roleId;
	private String activityId;
	private String moduleId;
	private String subModuleId;
	private String moduleName;
	private String roleName;
	private String subModuleName;
	private String activityName;
	private String functionName;
	
	
	
	public FunToRoleMappingDto() {
		
	}


	public String getFunctionId() {
		return functionId;
	}


	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}


	public String getRoleId() {
		return roleId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public String getActivityId() {
		return activityId;
	}


	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}


	public String getModuleId() {
		return moduleId;
	}


	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}


	public String getSubModuleId() {
		return subModuleId;
	}


	public void setSubModuleId(String subModuleId) {
		this.subModuleId = subModuleId;
	}


	public FunToRoleMappingDto(String functionId, String roleId,
			String activityId, String moduleId, String subModuleId) {
		super();
		this.functionId = functionId;
		this.roleId = roleId;
		this.activityId = activityId;
		this.moduleId = moduleId;
		this.subModuleId = subModuleId;
	}


	public String getFunToRoleMappingid() {
		return funToRoleMappingid;
	}


	public void setFunToRoleMappingid(String funToRoleMappingid) {
		this.funToRoleMappingid = funToRoleMappingid;
	}


	public String getModuleName() {
		return moduleName;
	}


	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public String getSubModuleName() {
		return subModuleName;
	}


	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}


	public String getActivityName() {
		return activityName;
	}


	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}


	public String getFunctionName() {
		return functionName;
	}


	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
}
