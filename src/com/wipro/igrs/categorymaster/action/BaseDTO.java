package com.wipro.igrs.categorymaster.action;

import java.io.Serializable;

/**
 * ===========================================================================
 * File           :   BaseDTO.java
 * Description    :   Represents the base dto for user login

 * Author         :   Madan  Mohan
 * Created Date   :   Dec 11, 2007

 * ===========================================================================
 */
/**
 * ===========================================================================
 * File : BaseDTO.java Description : Represents the base dto for user login
 * 
 * Author : Madan Mohan Created Date : Dec 11, 2007
 * 
 * ===========================================================================
 */
public class

BaseDTO implements Serializable {

	private String activeFlag;
	private String mode;
	private String actionType;
	private String selectedCheckBox;
	private String createdDate;
	private String createdBy;
	private String updatedDate;
	private String updatedBy;
	private String indexId;
	private String resourceId;

	/**
	 * @param activeFlag
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * @return
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param actionType
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return
	 */
	public String getActionType() {
		return actionType;
	}

	public void setSelectedCheckBox(String selectedCheckBox) {
		this.selectedCheckBox = selectedCheckBox;
	}

	public String getSelectedCheckBox() {
		return selectedCheckBox;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceId() {
		return resourceId;
	}
}
