package com.wipro.igrs.adminConfig.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.adminConfig.dto.CategoryMasterDTO;

public class PersonCatgryForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String categoryPageName;
	private String actionValue;

	private String categoryName;
	private String categoryStatus;

	private CategoryMasterDTO categorydto;

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public String getCategoryPageName() {
		return categoryPageName;
	}

	public void setCategoryPageName(String categoryPageName) {
		this.categoryPageName = categoryPageName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public CategoryMasterDTO getCategorydto() {
		return categorydto;
	}

	public void setCategorydto(CategoryMasterDTO categorydto) {
		this.categorydto = categorydto;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
