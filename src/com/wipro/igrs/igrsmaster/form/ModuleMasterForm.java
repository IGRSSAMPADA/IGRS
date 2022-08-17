/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ModuleMasterForm.java
 * Author      :   Imran Shaik
 * Description :   Represents the Master DTO Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		     18th Mar, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.igrsmaster.form;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.igrsmaster.dto.ModuleMasterDTO;


public class ModuleMasterForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String description;
	private boolean status;
	private String deedStatus;
	private String deedCategory;
	private String deedreq;
	private String deedlinkprop;
	private String deedRor;
	private String pageTitle;
	private ModuleMasterDTO dto =new ModuleMasterDTO();
	private String modulename;
	private boolean payment;
	private boolean intimation;
	private String parentId;
	private String subname;
	private String createdDate;
	private ArrayList deedList=new ArrayList();
	//added by shruti
	private ArrayList operatorList=new ArrayList();
	private ArrayList addFactorList=new ArrayList();
	private ArrayList propertyTypeList=new ArrayList();
	private ArrayList propTypeL1List=new ArrayList();
	private ArrayList propTypeL2List=new ArrayList();
	private String actionName;
	private String operatorName;
	private String operatorId;
	private String operandValue; 
	private ArrayList propDetails=new ArrayList();
	private String mapAll;
	private String hdnPropDetails;
	private String normalVal; 
	private ArrayList viewPropDetails=new ArrayList();
	private String addFactorOprndValue;
	private String addFactorArea;
	private String areaBasedDiv;
	private String language;
	
	public String getAreaBasedDiv() {
		return areaBasedDiv;
	}

	public void setAreaBasedDiv(String areaBasedDiv) {
		this.areaBasedDiv = areaBasedDiv;
	}

	public String getAddFactorOprndValue() {
		return addFactorOprndValue;
	}

	public void setAddFactorOprndValue(String addFactorOprndValue) {
		this.addFactorOprndValue = addFactorOprndValue;
	}

	public String getAddFactorArea() {
		return addFactorArea;
	}

	public void setAddFactorArea(String addFactorArea) {
		this.addFactorArea = addFactorArea;
	}

	public ArrayList getViewPropDetails() {
		return viewPropDetails;
	}

	public void setViewPropDetails(ArrayList viewPropDetails) {
		this.viewPropDetails = viewPropDetails;
	}

	public String getNormalVal() {
		return normalVal;
	}

	public void setNormalVal(String normalVal) {
		this.normalVal = normalVal;
	}

	private String userDefinedField;
	
	
	public String getUserDefinedField() {
		return userDefinedField;
	}

	public void setUserDefinedField(String userDefinedField) {
		this.userDefinedField = userDefinedField;
	}

	public String getHdnPropDetails() {
		return hdnPropDetails;
	}

	public void setHdnPropDetails(String hdnPropDetails) {
		this.hdnPropDetails = hdnPropDetails;
	}

	public String getMapAll() {
		return mapAll;
	}

	public void setMapAll(String mapAll) {
		this.mapAll = mapAll;
	}

	public ArrayList getPropDetails() {
		return propDetails;
	}

	public void setPropDetails(ArrayList propDetails) {
		this.propDetails = propDetails;
	}

	public ArrayList getPropTypeL1List() {
		return propTypeL1List;
	}

	public void setPropTypeL1List(ArrayList propTypeL1List) {
		this.propTypeL1List = propTypeL1List;
	}

	public ArrayList getPropTypeL2List() {
		return propTypeL2List;
	}

	public void setPropTypeL2List(ArrayList propTypeL2List) {
		this.propTypeL2List = propTypeL2List;
	}

	
	
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public ArrayList getPropertyTypeList() {
		return propertyTypeList;
	}

	public void setPropertyTypeList(ArrayList propertyTypeList) {
		this.propertyTypeList = propertyTypeList;
	}


	
	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperandValue() {
		return operandValue;
	}

	public ArrayList getAddFactorList() {
		return addFactorList;
	}

	public void setAddFactorList(ArrayList addFactorList) {
		this.addFactorList = addFactorList;
	}

	public void setOperandValue(String operandValue) {
		this.operandValue = operandValue;
	}

	public ArrayList getOperatorList() {
		return operatorList;
	}

	public void setOperatorList(ArrayList operatorList) {
		this.operatorList = operatorList;
	}

	
	
	
	
	public ArrayList getDeedList() {
		return deedList;
	}

	public void setDeedList(ArrayList deedList) {
		this.deedList = deedList;
	}

	/**
	 * 
	 * @return parentId
	 */
	public String getParentId() {
		return parentId;
	}
	
	/**
	 * 
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 
	 * @return modulename
	 */
	public String getModulename() {
		return modulename;
	}
	
	/**
	 * 
	 * @param modulename
	 */
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	
	/**
	 * 
	 * @return pageTitle
	 */
	public String getPageTitle() {
		return pageTitle;
	}
	
	/**
	 * 
	 * @param pageTitle
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return ModuleMasterDTO
	 */
	public ModuleMasterDTO getDto() {
		return dto;
	}
	
	/**
	 * 
	 * @param ModuleMasterDTO
	 */
	public void setDto(ModuleMasterDTO dto) {
		this.dto = dto;
	}
	
	/**
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 
	 * @return status
	 */
	public boolean isStatus() {
		return status;
	}
	
	/**
	 * 
	 * @param status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * 
	 * @return payment
	 */
	public boolean isPayment() {
		return payment;
	}
	
	/**
	 * 
	 * @param payment
	 */
	public void setPayment(boolean payment) {
		this.payment = payment;
	}
	
	/**
	 * 
	 * @return intimation
	 */
	public boolean isIntimation() {
		return intimation;
	}
	
	/**
	 * 
	 * @param intimation
	 */
	public void setIntimation(boolean intimation) {
		this.intimation = intimation;
	}
	
	/**
	 * 
	 * @return subname
	 */
	public String getSubname() {
		return subname;
	}
	
	/**
	 * 
	 * @param subname
	 */
	public void setSubname(String subname) {
		this.subname = subname;
	}
	
	/**
     * Method validate
     * @param ActionMapping mapping
     * @param HttpServletRequest request
     * @return ActionErrors
     */

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
    	super.validate(mapping, request);
        ActionErrors errors = new ActionErrors();
        if(getName() == null || getName().equals("")){
           // errors.add("name", new ActionError("error.requiredfield"));
        }
        
        return errors;
    }

	public String getDeedCategory() {
		return deedCategory;
	}

	public void setDeedCategory(String deedCategory) {
		this.deedCategory = deedCategory;
	}

	public String getDeedreq() {
		return deedreq;
	}

	public void setDeedreq(String deedreq) {
		this.deedreq = deedreq;
	}

	public String getDeedlinkprop() {
		return deedlinkprop;
	}

	public void setDeedlinkprop(String deedlinkprop) {
		this.deedlinkprop = deedlinkprop;
	}

	public String getDeedRor() {
		return deedRor;
	}

	public void setDeedRor(String deedRor) {
		this.deedRor = deedRor;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDeedStatus() {
		return deedStatus;
	}

	public void setDeedStatus(String deedStatus) {
		this.deedStatus = deedStatus;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}
	
	//added by simran for subclauses 
	
	private String descriptionHindi;
	private String applicableBaseValue;
	private String compBasedOn; 
	private String propertyId; 
	private String subId;

	public String getDescriptionHindi() {
		return descriptionHindi;
	}

	public void setDescriptionHindi(String descriptionHindi) {
		this.descriptionHindi = descriptionHindi;
	}

	public String getApplicableBaseValue() {
		return applicableBaseValue;
	}

	public void setApplicableBaseValue(String applicableBaseValue) {
		this.applicableBaseValue = applicableBaseValue;
	}

	public String getCompBasedOn() {
		return compBasedOn;
	}

	public void setCompBasedOn(String compBasedOn) {
		this.compBasedOn = compBasedOn;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}
	
	
	
}