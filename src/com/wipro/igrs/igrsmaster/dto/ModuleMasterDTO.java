/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ModuleMasterDTO.java
 * Author      :   Imran Shaik
 * Description :   Represents the Master DTO Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		     18th Mar, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.igrsmaster.dto;

import java.util.ArrayList;
import java.io.Serializable;


public class ModuleMasterDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private ArrayList moduleList;
	private ArrayList deedViewList;
	private String description;
	private boolean status; 
	private String modulename;
	private boolean payment;
	private boolean intimation;
	private ArrayList subList;
	
	//added by shruti
	private String operatorName;
	private String operatorId;
	private String addFactorName;
	private String addFactorId;
	private String addFactorOprndValue;
	private String addComputation;
	private String propertyTypeName;
	private String propertyTypeId;
	private String operandValue;
	private String mapAll;
	private String normalVal;
	private String propTypeId;
	private String propTypeL1Id;
	private String propTypeL1Name;
	private String propTypeL2Id;
	private String propTypeL2Name;
	private String chkdPropTypeId;
	private String chkdPropTypeL1Id;
	private String chkdPropTypeL2Id;
	private String propid;
	private String propidl1;
	private String propidl2;
	private String userDefinedField;
	private String hdnpropids;
	private String hdnchkboxval;
	private ArrayList viewPropDetails;
	private String addFactorArea;
	private String areaBasedDiv;
	private String propTypeFlag;
	private String subPropTypeId;
	private String subPropTypeName;
	
	

	public String getPropTypeFlag() {
		return propTypeFlag;
	}

	public void setPropTypeFlag(String propTypeFlag) {
		this.propTypeFlag = propTypeFlag;
	}

	public String getAreaBasedDiv() {
		return areaBasedDiv;
	}

	public void setAreaBasedDiv(String areaBasedDiv) {
		this.areaBasedDiv = areaBasedDiv;
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

	
	public String getPropTypeId() {
		return propTypeId;
	}

	public void setPropTypeId(String propTypeId) {
		this.propTypeId = propTypeId;
	}

	
	
	public String getUserDefinedField() {
		return userDefinedField;
	}

	public void setUserDefinedField(String userDefinedField) {
		this.userDefinedField = userDefinedField;
	}

	public String getPropid() {
		return propid;
	}

	public void setPropid(String propid) {
		this.propid = propid;
	}

	public String getPropidl1() {
		return propidl1;
	}

	public void setPropidl1(String propidl1) {
		this.propidl1 = propidl1;
	}

	public String getPropidl2() {
		return propidl2;
	}

	public void setPropidl2(String propidl2) {
		this.propidl2 = propidl2;
	}

	public String getChkdPropTypeId() {
		return chkdPropTypeId;
	}

	public void setChkdPropTypeId(String chkdPropTypeId) {
		this.chkdPropTypeId = chkdPropTypeId;
	}

	public String getChkdPropTypeL1Id() {
		return chkdPropTypeL1Id;
	}

	public void setChkdPropTypeL1Id(String chkdPropTypeL1Id) {
		this.chkdPropTypeL1Id = chkdPropTypeL1Id;
	}

	public String getChkdPropTypeL2Id() {
		return chkdPropTypeL2Id;
	}

	public void setChkdPropTypeL2Id(String chkdPropTypeL2Id) {
		this.chkdPropTypeL2Id = chkdPropTypeL2Id;
	}

	
	
	public String getHdnchkboxval() {
		return hdnchkboxval;
	}

	public void setHdnchkboxval(String hdnchkboxval) {
		this.hdnchkboxval = hdnchkboxval;
	}

	public String getHdnpropids() {
		return hdnpropids;
	}

	public void setHdnpropids(String hdnpropids) {
		this.hdnpropids = hdnpropids;
	}

	public String getPropTypeL1Id() {
		return propTypeL1Id;
	}

	public void setPropTypeL1Id(String propTypeL1Id) {
		this.propTypeL1Id = propTypeL1Id;
	}

	public String getPropTypeL1Name() {
		return propTypeL1Name;
	}

	public void setPropTypeL1Name(String propTypeL1Name) {
		this.propTypeL1Name = propTypeL1Name;
	}

	public String getPropTypeL2Id() {
		return propTypeL2Id;
	}

	public void setPropTypeL2Id(String propTypeL2Id) {
		this.propTypeL2Id = propTypeL2Id;
	}

	
	
	//end of code
	
	public String getPropTypeL2Name() {
		return propTypeL2Name;
	}

	public void setPropTypeL2Name(String propTypeL2Name) {
		this.propTypeL2Name = propTypeL2Name;
	}

	public ArrayList getModuleList() {
		return moduleList;
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

	public String getAddFactorName() {
		return addFactorName;
	}

	public void setAddFactorName(String addFactorName) {
		this.addFactorName = addFactorName;
	}

	public String getAddFactorId() {
		return addFactorId;
	}

	public void setAddFactorId(String addFactorId) {
		this.addFactorId = addFactorId;
	}

	public String getAddFactorOprndValue() {
		return addFactorOprndValue;
	}

	public void setAddFactorOprndValue(String addFactorOprndValue) {
		this.addFactorOprndValue = addFactorOprndValue;
	}

	public String getAddComputation() {
		return addComputation;
	}

	public void setAddComputation(String addComputation) {
		this.addComputation = addComputation;
	}

	public String getPropertyTypeName() {
		return propertyTypeName;
	}

	public void setPropertyTypeName(String propertyTypeName) {
		this.propertyTypeName = propertyTypeName;
	}

	public String getPropertyTypeId() {
		return propertyTypeId;
	}

	public void setPropertyTypeId(String propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}

	public String getOperandValue() {
		return operandValue;
	}

	public void setOperandValue(String operandValue) {
		this.operandValue = operandValue;
	}

	public String getMapAll() {
		return mapAll;
	}

	public void setMapAll(String mapAll) {
		this.mapAll = mapAll;
	}

	public void setModuleList(ArrayList moduleList) {
		this.moduleList = moduleList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	
	public boolean isPayment() {
		return payment;
	}
	public void setPayment(boolean payment) {
		this.payment = payment;
	}
	public boolean isIntimation() {
		return intimation;
	}
	public void setIntimation(boolean intimation) {
		this.intimation = intimation;
	}

	public ArrayList getSubList() {
		return subList;
	}

	public void setSubList(ArrayList subList) {
		this.subList = subList;
	}

	public ArrayList getDeedViewList() {
		return deedViewList;
	}

	public void setDeedViewList(ArrayList deedViewList) {
		this.deedViewList = deedViewList;
	}
	
	//added by simran
	private String applicableBaseValue;
	private String descriptionHindi;
	private String deleteSubId;
	private String successCheck;
	private String statusChk;


	public String getApplicableBaseValue() {
		return applicableBaseValue;
	}

	public void setApplicableBaseValue(String applicableBaseValue) {
		this.applicableBaseValue = applicableBaseValue;
	}

	public String getSubPropTypeId() {
		return subPropTypeId;
	}

	public void setSubPropTypeId(String subPropTypeId) {
		this.subPropTypeId = subPropTypeId;
	}

	public String getSubPropTypeName() {
		return subPropTypeName;
	}

	public void setSubPropTypeName(String subPropTypeName) {
		this.subPropTypeName = subPropTypeName;
	}

	public String getDescriptionHindi() {
		return descriptionHindi;
	}

	public void setDescriptionHindi(String descriptionHindi) {
		this.descriptionHindi = descriptionHindi;
	}

	public String getDeleteSubId() {
		return deleteSubId;
	}

	public void setDeleteSubId(String deleteSubId) {
		this.deleteSubId = deleteSubId;
	}

	public String getSuccessCheck() {
		return successCheck;
	}

	public void setSuccessCheck(String successCheck) {
		this.successCheck = successCheck;
	}

	public String getStatusChk() {
		return statusChk;
	}

	public void setStatusChk(String statusChk) {
		this.statusChk = statusChk;
	}
	
	
}