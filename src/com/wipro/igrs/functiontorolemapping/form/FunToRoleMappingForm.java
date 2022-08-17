package com.wipro.igrs.functiontorolemapping.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class FunToRoleMappingForm extends org.apache.struts.action.ActionForm 
{
	
	private String funToRoleMappingid;
	private String functionId;
	private String roleId;
	private String activityId;
	private String moduleId;
	private String subModuleId;
	private ArrayList functions;
	private ArrayList activities;
	private ArrayList roles;
	private ArrayList modules;
	private ArrayList subModules;
	private String [] selected;
	
	
	public String[] getSelected() {
		return selected;
	}
	public void setSelected(String[] selected) {
		this.selected = selected;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
	}
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		
		return null;
	}
	
	public ArrayList getFunctions() {
		return functions;
	}
	public void setFunctions(ArrayList functions) {
		this.functions = functions;
	}
	public ArrayList getActivities() {
		return activities;
	}
	public void setActivities(ArrayList activities) {
		this.activities = activities;
	}
	public ArrayList getRoles() {
		return roles;
	}
	public void setRoles(ArrayList roles) {
		this.roles = roles;
	}
	public ArrayList getModules() {
		return modules;
	}
	public void setModules(ArrayList modules) {
		this.modules = modules;
	}
	public ArrayList getSubModules() {
		return subModules;
	}
	public void setSubModules(ArrayList subModules) {
		this.subModules = subModules;
	}
	public String getFunToRoleMappingid() {
		return funToRoleMappingid;
	}
	public void setFunToRoleMappingid(String funToRoleMappingid) {
		this.funToRoleMappingid = funToRoleMappingid;
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
	
	
	
}
