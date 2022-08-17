/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	CasteMasterDTO.java
 * Author		:	vengamamba
 * Date			: 	18/06/2008
 */

package com.wipro.igrs.adminConfig.dto;

import java.util.ArrayList;

public class CasteMasterDTO {
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	private String casteName;
	private String category;
	private String casteStatus;
	private String casteId;

	private ArrayList casteList;
	private ArrayList categoryList;

	public ArrayList getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList categoryList) {
		this.categoryList = categoryList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCasteName() {
		return casteName;
	}

	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCasteStatus() {
		return casteStatus;
	}

	public void setCasteStatus(String casteStatus) {
		this.casteStatus = casteStatus;
	}

	public String getCasteId() {
		return casteId;
	}

	public void setCasteId(String casteId) {
		this.casteId = casteId;
	}

	public ArrayList getCasteList() {
		return casteList;
	}

	public void setCasteList(ArrayList casteList) {
		this.casteList = casteList;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
