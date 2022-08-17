/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	PropertyL1DTO.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	26/02/2008
 */
package com.wipro.igrs.propertyL1master.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class PropertyL1DTO implements Serializable{
	private ArrayList propertyidList;
	private ArrayList propertyL1List;
	private String name;
	private String value;
	private String propertyL1id;
	private String propertyL1name;
	private String propertyL1desc;
	private String propertyL1status;
	private String propertyid;
	public PropertyL1DTO() {	   
	    }
	public ArrayList getPropertyidList() {
		return propertyidList;
	}
	public void setPropertyidList(ArrayList propertyidList) {
		this.propertyidList = propertyidList;
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
	public ArrayList getPropertyL1List() {
		return propertyL1List;
	}
	public void setPropertyL1List(ArrayList propertyL1List) {
		this.propertyL1List = propertyL1List;
	}
	public String getPropertyL1id() {
		return propertyL1id;
	}
	public void setPropertyL1id(String propertyL1id) {
		this.propertyL1id = propertyL1id;
	}
	
	public String getPropertyL1name() {
		return propertyL1name;
	}
	public void setPropertyL1name(String propertyL1name) {
		this.propertyL1name = propertyL1name;
	}
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
	public void setPropertyid(String propertyid) {
		this.propertyid = propertyid;
	}
	 
}
