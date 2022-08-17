/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	PropertyDTO.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	28/02/2008
 */
package com.wipro.igrs.propertytypemaster.dto;

import java.util.ArrayList;

public class PropertyDTO {
	private ArrayList propertyList;
	private String propertyid;
	private String propertyname;
	private String propertydesc;
	private String propertystatus;
	private String proppageName;
	private int sno;
	public PropertyDTO() {	   
	    }
	public String getProppageName() {
		return proppageName;
	}
	public void setProppageName(String proppageName) {
		this.proppageName = proppageName;
	}
	public ArrayList getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(ArrayList propertyList) {
		this.propertyList = propertyList;
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
	
}
