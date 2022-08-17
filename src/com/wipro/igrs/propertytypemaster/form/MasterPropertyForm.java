/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MasterPropertyForm.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	28/02/2008
 */
package com.wipro.igrs.propertytypemaster.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.propertytypemaster.dto.PropertyDTO;

public class MasterPropertyForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String property;
	private String description;
	private String propertyId;
	private int sno;
	private ArrayList propertyList;
	private String propertyid;
	private String propertyname;
	private String propertydesc;
	private String propertystatus;
	private PropertyDTO dto;
	private String proppageName;
	public String getProperty() {
		return property;
	}
	public PropertyDTO getDto() {
		return dto;
	}
	public void setDto(PropertyDTO dto) {
		this.dto = dto;
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
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getPropertyid() {
		return propertyid;
	}
	public void setPropertyid(String propertyid) {
		this.propertyid = propertyid;
	}
	public String getPropertyname() {
		return propertyname;
	}
	public void setPropertyname(String propertyname) {
		this.propertyname = propertyname;
	}
	public String getPropertydesc() {
		return propertydesc;
	}
	public void setPropertydesc(String propertydesc) {
		this.propertydesc = propertydesc;
	}
	public String getPropertystatus() {
		return propertystatus;
	}
	public void setPropertystatus(String propertystatus) {
		this.propertystatus = propertystatus;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public ArrayList getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(ArrayList propertyList) {
		this.propertyList = propertyList;
	}
	
	public String getProppageName() {
		return proppageName;
	}
	public void setProppageName(String proppageName) {
		this.proppageName = proppageName;
	}
}
