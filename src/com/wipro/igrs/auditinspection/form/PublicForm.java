/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.auditinspection.form;


import java.util.ArrayList;

import org.apache.struts.upload.FormFile;


import com.wipro.igrs.auditinspection.dto.POIAddObjectionDTO;
import com.wipro.igrs.auditinspection.dto.PartyDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PropertyDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PublicDTO;


import com.wipro.igrs.baseaction.form.BaseForm;

public class PublicForm extends BaseForm{

	
	PublicDTO publicInspectionDTO=new PublicDTO();
	

	
	POIAddObjectionDTO objectionDTO=new POIAddObjectionDTO();
	
	//Added by rachita 
private String othersFormRegDatePoi ;
PublicDTO publicDtoPoiOthers =new PublicDTO();
	 
	 public String getOthersFormRegDatePoi() {
	return othersFormRegDatePoi;
}

public void setOthersFormRegDatePoi(String othersFormRegDatePoi) {
	this.othersFormRegDatePoi = othersFormRegDatePoi;
}

public PublicDTO getPublicDtoPoiOthers() {
	return publicDtoPoiOthers;
}

public void setPublicDtoPoiOthers(PublicDTO publicDtoPoiOthers) {
	this.publicDtoPoiOthers = publicDtoPoiOthers;
}

	// ArrayList publicList=new ArrayList();
	private String actionType;
	private String addMore;
	private String docType;
	//added by shruti-28th nov 2013
	private String language;
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	//added by shruti
	PartyDetailsDTO partyDto=new PartyDetailsDTO();
	public PartyDetailsDTO getPartyDto() {
		return partyDto;
	}

	public void setPartyDto(PartyDetailsDTO partyDto) {
		this.partyDto = partyDto;
	}

	public PropertyDetailsDTO getPropertyDto() {
		return propertyDto;
	}

	public void setPropertyDto(PropertyDetailsDTO propertyDto) {
		this.propertyDto = propertyDto;
	}

	PropertyDetailsDTO propertyDto=new PropertyDetailsDTO();
	
	
	
	
	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	
	public PublicDTO getPublicInspectionDTO() {
		return publicInspectionDTO;
	}

	public void setPublicInspectionDTO(PublicDTO publicInspectionDTO) {
		this.publicInspectionDTO = publicInspectionDTO;
	}

	

	public POIAddObjectionDTO getObjectionDTO() {
		return objectionDTO;
	}

	public void setObjectionDTO(POIAddObjectionDTO objectionDTO) {
		this.objectionDTO = objectionDTO;
	}

	/**
     * @return the addMore
     */
    public String getAddMore() {
    	return addMore;
    }

	/**
     * @param addMore the addMore to set
     */
    public void setAddMore(String addMore) {
    	this.addMore = addMore;
    }

	/**
     * @return the docType
     */
    public String getDocType() {
    	return docType;
    }

	/**
     * @param docType the docType to set
     */
    public void setDocType(String docType) {
    	this.docType = docType;
    }

	
	
}