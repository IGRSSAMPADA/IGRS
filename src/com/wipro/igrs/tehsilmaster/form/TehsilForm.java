/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	TehsilForm.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	03/03/2008
 */
package com.wipro.igrs.tehsilmaster.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.tehsilmaster.dto.TehsilDTO;

public class TehsilForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String districtId;
	private String tehsilId;
	private String tehsilName;
	private String tehsilDesc;
	private String tehsilStatus;
	private String tehsilpageName;
	private ArrayList tehsilList;
	private String getTehsilId;
	private TehsilDTO dto;
	public String getTehsilName() {
		return tehsilName;
	}
	public ArrayList getTehsilList() {
		return tehsilList;
	}
	public void setTehsilList(ArrayList tehsilList) {
		this.tehsilList = tehsilList;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getTehsilDesc() {
		return tehsilDesc;
	}
	public void setTehsilDesc(String tehsilDesc) {
		this.tehsilDesc = tehsilDesc;
	}
	public String getTehsilpageName() {
		return tehsilpageName;
	}
	public void setTehsilpageName(String tehsilpageName) {
		this.tehsilpageName = tehsilpageName;
	}
	public String getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getTehsilStatus() {
		return tehsilStatus;
	}
	public void setTehsilStatus(String tehsilStatus) {
		this.tehsilStatus = tehsilStatus;
	}
	public TehsilDTO getDto() {
		return dto;
	}
	public void setDto(TehsilDTO dto) {
		this.dto = dto;
	}
	public String getGetTehsilId() {
		return getTehsilId;
	}
	public void setGetTehsilId(String getTehsilId) {
		this.getTehsilId = getTehsilId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

}
