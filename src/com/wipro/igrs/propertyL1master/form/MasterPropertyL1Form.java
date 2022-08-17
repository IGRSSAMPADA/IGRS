/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MasterPropertyL1Form.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	26/02/2008
 */
package com.wipro.igrs.propertyL1master.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.propertyL1master.dto.PropertyL1DTO;

public class MasterPropertyL1Form extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String propertyL1name;
	private String propertyL1description;
	private String propertyid;
	private String propertyL1id;
	private ArrayList propertyL1List;
	private PropertyL1DTO dto;
	private String pageNameL1;
	private String propertyL1desc;
	private String propertyL1status;
	public String getPropertyL1desc() {
		return propertyL1desc;
	}
	public void setPropertyL1desc(String propertyL1desc) {
		this.propertyL1desc = propertyL1desc;
	}
	public String getPropertyL1status() {
		return propertyL1status;
	}
	public void setPropertyL1status(String propertyL1status) {
		this.propertyL1status = propertyL1status;
	}
	public String getPropertyid() {
		return propertyid;
	}
	public String getPropertyL1id() {
		return propertyL1id;
	}
	public void setPropertyL1id(String propertyL1id) {
		this.propertyL1id = propertyL1id;
	}
	public void setPropertyid(String propertyid) {
		this.propertyid = propertyid;
	}
	
	public String getPropertyL1name() {
		return propertyL1name;
	}
	public void setPropertyL1name(String propertyL1name) {
		this.propertyL1name = propertyL1name;
	}
	public String getPropertyL1description() {
		return propertyL1description;
	}
	public void setPropertyL1description(String propertyL1description) {
		this.propertyL1description = propertyL1description;
	}
	public PropertyL1DTO getDto() {
		return dto;
	}
	public void setDto(PropertyL1DTO dto) {
		this.dto = dto;
	}
	public String getPageNameL1() {
		return pageNameL1;
	}
	public void setPageNameL1(String pageNameL1) {
		this.pageNameL1 = pageNameL1;
	}
	public ArrayList getPropertyL1List() {
		return propertyL1List;
	}
	public void setPropertyL1List(ArrayList propertyL1List) {
		this.propertyL1List = propertyL1List;
	}
	
}
