/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	PropertyL2DTO.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	27/02/2008
 */
package com.wipro.igrs.propertyL2master.dto;

import java.util.ArrayList;

import com.wipro.igrs.propertyL2master.dto.PropertyL2DTO;

public class PropertyL2DTO implements java.io.Serializable {
	private ArrayList propertyL1idList;
	private ArrayList propertyL2List;
	private String propertyL2id;
	private String propertyL2name;
	private String propertyL2desc;
	private String propertyL2status;
	private String propertyL1id;
	private String name;
	private String value;
	private String pageNameL2;
	public PropertyL2DTO() {	   
	    }
	public String getPropertyL1id() {
		return propertyL1id;
	}
	public void setPropertyL1id(String propertyL1id) {
		this.propertyL1id = propertyL1id;
	}
	public ArrayList getPropertyL1idList() {
		return propertyL1idList;
	}
	public void setPropertyL1idList(ArrayList propertyL1idList) {
		this.propertyL1idList = propertyL1idList;
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
	public ArrayList getPropertyL2List() {
		return propertyL2List;
	}
	public void setPropertyL2List(ArrayList propertyL2List) {
		this.propertyL2List = propertyL2List;
	}
	public String getPropertyL2id() {
		return propertyL2id;
	}
	public void setPropertyL2id(String propertyL2id) {
		this.propertyL2id = propertyL2id;
	}
	public String getPropertyL2name() {
		return propertyL2name;
	}
	public void setPropertyL2name(String propertyL2name) {
		this.propertyL2name = propertyL2name;
	}
	public String getPropertyL2desc() {
		return propertyL2desc;
	}
	public void setPropertyL2desc(String propertyL2desc) {
		this.propertyL2desc = propertyL2desc;
	}
	public String getPropertyL2status() {
		return propertyL2status;
	}
	public void setPropertyL2status(String propertyL2status) {
		this.propertyL2status = propertyL2status;
	}
	public String getPageNameL2() {
		return pageNameL2;
	}
	public void setPageNameL2(String pageNameL2) {
		this.pageNameL2 = pageNameL2;
	}

}
