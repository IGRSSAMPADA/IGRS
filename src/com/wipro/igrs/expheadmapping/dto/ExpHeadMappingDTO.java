/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ExpHeadMappingDTO.java
 * Author      :   Hend M. ismail
 * Description :   Represents the DTO Class for Exp. Head Mapping  Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  19th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.expheadmapping.dto;

import java.io.Serializable;



public class ExpHeadMappingDTO implements Serializable{
	public ExpHeadMappingDTO() {

	}
	
	private String majorHeadId;
	private String majorHeadName;
	
	private String subMajorHeadId;
	private String subMajorHeadName;
	
	private String minorHeadId;
	private String minorHeadName;
	
	private String schemeId;
	private String schemeName;
	
	private String segmentId;
	private String segmentName;
	
	private String objectId;
	private String objectName;
	
	
	private String detailedHeadId;
	private String detailedHeadName;
	
	private String droId;
	//private String droName;
	
	
	
	public String getMajorHeadId() {
		return majorHeadId;
	}
	public void setMajorHeadId(String majorHeadId) {
		this.majorHeadId = majorHeadId;
	}
	public String getSubMajorHeadId() {
		return subMajorHeadId;
	}
	public void setSubMajorHeadId(String subMajorHeadId) {
		this.subMajorHeadId = subMajorHeadId;
	}
	public String getMajorHeadName() {
		return majorHeadName;
	}
	public void setMajorHeadName(String majorHeadName) {
		this.majorHeadName = majorHeadName;
	}
	public String getSubMajorHeadName() {
		return subMajorHeadName;
	}
	public void setSubMajorHeadName(String subMajorHeadName) {
		this.subMajorHeadName = subMajorHeadName;
	}
	public String getMinorHeadId() {
		return minorHeadId;
	}
	public void setMinorHeadId(String minorHeadId) {
		this.minorHeadId = minorHeadId;
	}
	public String getMinorHeadName() {
		return minorHeadName;
	}
	public void setMinorHeadName(String minorHeadName) {
		this.minorHeadName = minorHeadName;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getSegmentId() {
		return segmentId;
	}
	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}
	public String getSegmentName() {
		return segmentName;
	}
	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getDetailedHeadId() {
		return detailedHeadId;
	}
	public void setDetailedHeadId(String detailedHeadId) {
		this.detailedHeadId = detailedHeadId;
	}
	public String getDetailedHeadName() {
		return detailedHeadName;
	}
	public void setDetailedHeadName(String detailedHeadName) {
		this.detailedHeadName = detailedHeadName;
	}
	public String getDroId() {
		return droId;
	}
	public void setDroId(String droId) {
		this.droId = droId;
	}











}