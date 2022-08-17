/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ExpHeadMappingActionForm.java
 * Author      :   Hend M. ismail
 * Description :   Represents the FormBean Class for Exp. Head Mapping  Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  18th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.expheadmapping.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;



public class ExpHeadMappingActionForm extends ActionForm {

	
	private String majorHeadId;
	private String subMajorHeadId;
	private String minorHeadId;
	private String schemeId;
	private String segmentId;
	private String objectId;
	private String detailedHeadId;
	private String droId;
	
	
	ArrayList expHeadList;
	ArrayList majorOptions;
	ArrayList subMajorOptions;
	ArrayList minorOptions;
	ArrayList schemeOptions;
	ArrayList segmentOptions;
	ArrayList objectOptions;
	ArrayList  detailedHeadOptions;
	ArrayList droOptions;
	
	String pageName;
	String[] selected;
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
	public String getMinorHeadId() {
		return minorHeadId;
	}
	public void setMinorHeadId(String minorHeadId) {
		this.minorHeadId = minorHeadId;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getSegmentId() {
		return segmentId;
	}
	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getDroId() {
		return droId;
	}
	public void setDroId(String droId) {
		this.droId = droId;
	}
	public ArrayList getExpHeadList() {
		return expHeadList;
	}
	public void setExpHeadList(ArrayList expHeadList) {
		this.expHeadList = expHeadList;
	}
	public ArrayList getMajorOptions() {
		return majorOptions;
	}
	public void setMajorOptions(ArrayList majorOptions) {
		this.majorOptions = majorOptions;
	}
	public ArrayList getSubMajorOptions() {
		return subMajorOptions;
	}
	public void setSubMajorOptions(ArrayList subMajorOptions) {
		this.subMajorOptions = subMajorOptions;
	}
	public ArrayList getMinorOptions() {
		return minorOptions;
	}
	public void setMinorOptions(ArrayList minorOptions) {
		this.minorOptions = minorOptions;
	}

	public ArrayList getObjectOptions() {
		return objectOptions;
	}
	public void setObjectOptions(ArrayList objectOptions) {
		this.objectOptions = objectOptions;
	}

	public ArrayList getDroOptions() {
		return droOptions;
	}
	public void setDroOptions(ArrayList droOptions) {
		this.droOptions = droOptions;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String[] getSelected() {
		return selected;
	}
	public void setSelected(String[] selected) {
		this.selected = selected;
	}
	public ArrayList getSchemeOptions() {
		return schemeOptions;
	}
	public void setSchemeOptions(ArrayList schemeOptions) {
		this.schemeOptions = schemeOptions;
	}
	public ArrayList getSegmentOptions() {
		return segmentOptions;
	}
	public void setSegmentOptions(ArrayList segmentOptions) {
		this.segmentOptions = segmentOptions;
	}
	public String getDetailedHeadId() {
		return detailedHeadId;
	}
	public void setDetailedHeadId(String detailedHeadId) {
		this.detailedHeadId = detailedHeadId;
	}
	public ArrayList getDetailedHeadOptions() {
		return detailedHeadOptions;
	}
	public void setDetailedHeadOptions(ArrayList detailedHeadOptions) {
		this.detailedHeadOptions = detailedHeadOptions;
	}
	







}