package com.wipro.igrs.serviceProviderTop.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.serviceProviderTop.dto.DTO;

public class Form extends ActionForm{
	private static final long serialVersionUID = 1L;
	DTO dto = new DTO();
	ArrayList districtList;
	

	public ArrayList getDistrictList() {
		return districtList;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	public DTO getDto() {
		return dto;
	}

	public void setDto(DTO dto) {
		this.dto = dto;
	}
}
