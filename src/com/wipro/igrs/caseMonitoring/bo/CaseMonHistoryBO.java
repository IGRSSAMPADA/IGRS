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
package com.wipro.igrs.caseMonitoring.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.caseMonitoring.bd.CaseMonHistoryBD;
import com.wipro.igrs.caseMonitoring.dto.CaseMonHistoryDTO;
import com.wipro.igrs.caseMonitoring.form.CaseMonHistoryForm;

public class CaseMonHistoryBO {
	public CaseMonHistoryBO() {
	}

	CaseMonHistoryBD caseBD=new CaseMonHistoryBD();

	private Logger logger = (Logger) Logger.getLogger(CaseMonBO.class);

	public ArrayList getCaseSectionList(String language)
	{
		ArrayList list=new ArrayList();
		list=caseBD.getCaseSectionList(language);
		return list;
		
	}
	
	public ArrayList getCaseStatusList(String language)
	{
		ArrayList list=new ArrayList();
		list=caseBD.getCaseStatusList(language);
		return list;
		
	}
	public ArrayList getCaseDivisionList(String language)
	{
		ArrayList list=new ArrayList();
		list=caseBD.getCaseDivisionList(language);
		return list;
	}
	
	public CaseMonHistoryDTO saveCaseData(CaseMonHistoryDTO dto, CaseMonHistoryForm caseForm)
	{
		dto=caseBD.saveCaseData(dto,caseForm);
		return  dto;
	}
	public ArrayList searchCaseData(CaseMonHistoryDTO dto)
	{
		ArrayList list=new ArrayList();
		list=caseBD.searchCaseData(dto);
		return  list;
	}
	public CaseMonHistoryDTO viewCaseData(CaseMonHistoryDTO dto,String language)
	{
		return (caseBD.viewCaseData(dto,language));
	}
	
	public boolean updateCaseData(CaseMonHistoryDTO dto, CaseMonHistoryForm caseForm)
	{
		boolean flag=false;
		flag=caseBD.updateCaseData(dto,caseForm);
		return  flag;
	}

	public ArrayList getuploadUrlList(CaseMonHistoryDTO caseDTO) {
		ArrayList list=new ArrayList();
		list=caseBD.getuploadUrlList(caseDTO);
		return  list;
	}

	public Boolean getremoveUpload(CaseMonHistoryDTO caseDTO) {
		Boolean flag=false;
		flag=caseBD.getremoveUpload(caseDTO);
		return  flag;
	}
	
}// Bo Close
