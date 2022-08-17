/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MasterPropertyL2Form.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	27/02/2008
 */
package com.wipro.igrs.propertyL2master.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.propertyL2master.dto.PropertyL2DTO;

public class MasterPropertyL2Form extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String property;
	private String description;
	private String propertyL1id;
	private String propertyL2id;
	private String propertyL2name;
	private String propertyL2desc;
	private String propertyL2status;
	private String pageNameL2;
	private ArrayList propertyL2List;
	private PropertyL2DTO dto;
	public String getPageNameL2() {
		return pageNameL2;
	}
	public void setPageNameL2(String pageNameL2) {
		this.pageNameL2 = pageNameL2;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPropertyL1id() {
		return propertyL1id;
	}
	public void setPropertyL1id(String propertyL2id) {
		this.propertyL1id = propertyL2id;
	}
	public PropertyL2DTO getDto() {
		return dto;
	}
	public void setDto(PropertyL2DTO dto) {
		this.dto = dto;
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
	public ArrayList getPropertyL2List() {
		return propertyL2List;
	}
	public void setPropertyL2List(ArrayList propertyL2List) {
		this.propertyL2List = propertyL2List;
	}
}
