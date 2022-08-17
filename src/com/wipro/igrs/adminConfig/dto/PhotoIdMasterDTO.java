/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	PhotoIdMasterDTO.java
 * Author		:	vengamamba
 * Date			: 	21/06/2008
 */

package com.wipro.igrs.adminConfig.dto;

import java.util.ArrayList;

public class PhotoIdMasterDTO {
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	private String photoIdName;

	private String photoIdStatus;
	private String photoId;

	private ArrayList photoIdList;

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

	public String getPhotoIdName() {
		return photoIdName;
	}

	public void setPhotoIdName(String photoIdName) {
		this.photoIdName = photoIdName;
	}

	public String getPhotoIdStatus() {
		return photoIdStatus;
	}

	public void setPhotoIdStatus(String photoIdStatus) {
		this.photoIdStatus = photoIdStatus;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public ArrayList getPhotoIdList() {
		return photoIdList;
	}

	public void setPhotoIdList(ArrayList photoIdList) {
		this.photoIdList = photoIdList;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
