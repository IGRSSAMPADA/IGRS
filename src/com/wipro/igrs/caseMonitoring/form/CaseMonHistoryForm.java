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
* 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.caseMonitoring.form;


import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.caseMonitoring.dto.CaseMonHistoryDTO;
import com.wipro.igrs.newreginit.dto.CommonDTO;



public class CaseMonHistoryForm extends ActionForm {
    private CaseMonHistoryDTO caseDTO = new CaseMonHistoryDTO();
    private String formName = "";
    private String actionName = "";
    private ArrayList uploadList = new ArrayList();
    private ArrayList urlList = new ArrayList();
	public CaseMonHistoryDTO getCaseDTO() {
		return caseDTO;
	}
	public void setCaseDTO(CaseMonHistoryDTO caseDTO) {
		this.caseDTO = caseDTO;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public void setUploadList(ArrayList uploadList) {
		this.uploadList = uploadList;
	}
	public ArrayList getUploadList() {
		return uploadList;
	}
	public void setUrlList(ArrayList urlList) {
		this.urlList = urlList;
	}
	public ArrayList getUrlList() {
		return urlList;
	}


 
   
}
