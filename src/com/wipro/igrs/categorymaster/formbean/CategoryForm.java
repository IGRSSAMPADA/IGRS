/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ActivityForm.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the FormBean Class for Activity Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  17th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.categorymaster.formbean;

import java.util.ArrayList;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.categorymaster.dto.CategoryDTO;



public class CategoryForm extends ActionForm {

	private String categoryId;
	private String categoryName;
	private String categoryStatus;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String [] selectedCat;
	
	ArrayList categoryList;
	
	public ArrayList getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(ArrayList categoryList) {
		this.categoryList = categoryList;
	}
	/**
	 * @return Returns the activityId.
	 */
	public String getCategoryId() {
		return categoryId;
	}
	/**
	 * @param activityId
	 *            The activityId to set.
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return Returns the activityName.
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param activityName
	 *            The activityName to set.
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return Returns the activityStatus.
	 */
	public String getCategoryStatus() {
		return categoryStatus;
	}
	/**
	 * @param activityStatus
	 *            The activityStatus to set.
	 */
	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	/**
	 * @return Returns the pageName.
	 */	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String[] getSelectedCat() {
		return selectedCat;
	}
	public void setSelectedCat(String[] selectedCat) {
		this.selectedCat = selectedCat;
	}
	
	
}