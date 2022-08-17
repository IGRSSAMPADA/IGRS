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
package com.wipro.igrs.caseMonitoring.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.caseMonitoring.dao.CaseMonHistoryDAO;
import com.wipro.igrs.caseMonitoring.dto.CaseMonHistoryDTO;
import com.wipro.igrs.caseMonitoring.form.CaseMonHistoryForm;

public class CaseMonHistoryBD {

	private Logger logger = (Logger) Logger.getLogger(CaseMonBD.class);
	CaseMonHistoryDAO caseDAO=new CaseMonHistoryDAO();
	
	public CaseMonHistoryBD() {
	}
	
	
	public ArrayList getCaseSectionList(String language)
	{
		ArrayList list=new ArrayList();
		ArrayList list1=new ArrayList();
		list=caseDAO.getCaseSectionList(language);
		if(list!=null && list.size()>0)
		{
			ArrayList temp=new ArrayList();
			
			for(int i=0;i<list.size();i++)
			{
				temp=(ArrayList)list.get(i);
				CaseMonHistoryDTO dto=new CaseMonHistoryDTO();
				dto.setSectionName((String)temp.get(0));
				dto.setSectionId((String)temp.get(1));
				list1.add(dto);
			}
		}
		return  list1;
	}
	
	public ArrayList getCaseStatusList(String language)
	{
		ArrayList list=new ArrayList();
		ArrayList list1=new ArrayList();
		list=caseDAO.getCaseStatusList(language);
		if(list!=null && list.size()>0)
		{
			ArrayList temp=new ArrayList();
			
			for(int i=0;i<list.size();i++)
			{
				temp=(ArrayList)list.get(i);
				CaseMonHistoryDTO dto=new CaseMonHistoryDTO();
				dto.setCaseStatusName((String)temp.get(0));
				dto.setCaseStatusId((String)temp.get(1));
				list1.add(dto);
			}
		}
		return  list1;
	}
	
	public ArrayList getCaseDivisionList(String language)
	{
		ArrayList list=new ArrayList();
		ArrayList list1=new ArrayList();
		list=caseDAO.getCaseDivisionList(language);
		if(list!=null && list.size()>0)
		{
			ArrayList temp=new ArrayList();
			for(int i=0;i<list.size();i++)
			{
				temp=(ArrayList)list.get(i);
				CaseMonHistoryDTO dto=new CaseMonHistoryDTO();
				dto.setDivisionName((String)temp.get(0));
				dto.setDivisionId((String)temp.get(1));
				list1.add(dto);
			}
		}
		return  list1;
	}
	
	public CaseMonHistoryDTO saveCaseData(CaseMonHistoryDTO dto, CaseMonHistoryForm caseForm)
	{
		dto=caseDAO.saveCaseData(dto,caseForm);
		return  dto;
	}
	
	public ArrayList searchCaseData(CaseMonHistoryDTO dto)
	{
		ArrayList list=new ArrayList();
		list=caseDAO.searchCaseData(dto);
		return  list;
	}
	
	public CaseMonHistoryDTO viewCaseData(CaseMonHistoryDTO dto,String language)
	{
		ArrayList list=new ArrayList();
		CaseMonHistoryDTO dto1=new CaseMonHistoryDTO();
		list=caseDAO.viewCaseData(dto);
		if(list!=null && list.size()>0)
		{
			ArrayList tempList=new ArrayList();
			for(int i=0;i<list.size();i++)
			{
				tempList=(ArrayList)list.get(i);
				dto1.setReferenceNo((String)tempList.get(0));
				dto1.setCaseType((String)tempList.get(1));
				dto1.setSectionId((String)tempList.get(2));
				dto1.setCaseNumber((String)tempList.get(3));
				dto1.setPartyDtls((String)tempList.get(4));
				dto1.setPropDtls((String)tempList.get(5));
				dto1.setStampAmt((String)tempList.get(6));
				dto1.setStampDuty((String)tempList.get(7));
				dto1.setRegFee((String)tempList.get(8));
				dto1.setRecStampDuty((String)tempList.get(9));
				dto1.setRecRegFee((String)tempList.get(10));
				dto1.setCaseStatusId((String)tempList.get(11));
				if("1".equalsIgnoreCase(dto1.getCaseStatusId()))
				{
					if("en".equalsIgnoreCase(language))
					{
					dto1.setCaseStatus("Pending");
					}
					else
					{
						dto1.setCaseStatus("अपूर्ण");
					}
				}
				else if("2".equalsIgnoreCase(dto1.getCaseStatusId()))
				{
					if("en".equalsIgnoreCase(language))
					{
					dto1.setCaseStatus("Close");
					}
					else
					{
						dto1.setCaseStatus("बंद");
					}
				}
				dto1.setDivisionId((String)tempList.get(12));
				dto1.setRrcAmt((String)tempList.get(13));
				dto1.setRemarks((String)tempList.get(14));
				dto1.setCreatedDate((String)tempList.get(15));
			}	
		}
	
		return  dto1;
	}
	
	public boolean updateCaseData(CaseMonHistoryDTO dto, CaseMonHistoryForm caseForm)
	{
		boolean flag=false;
		flag=caseDAO.updateCaseData(dto,caseForm);
		return  flag;
	}


	public ArrayList getuploadUrlList(CaseMonHistoryDTO caseDTO) {
		
		ArrayList list=new ArrayList();
		ArrayList list1=new ArrayList();
		list=caseDAO.getuploadUrlList(caseDTO);
		
		int x=0;
		
		if(list!=null && list.size()>0)
		{
			ArrayList temp=new ArrayList();
			for(int i=0;i<list.size();i++)
			{
			
					x++;
				
				
				temp=(ArrayList)list.get(i);
				CaseMonHistoryDTO dto=new CaseMonHistoryDTO();
				
				dto.setUploadSrNo((String)temp.get(0));
				dto.setUploadId(x);
				dto.setDocumentName((String)temp.get(1));
				
				String docPath = (String)temp.get(2)+"/"+(String)temp.get(1);
				
				dto.setUploadDocPath(docPath);
				dto.setDbaseUpload("D");
				list1.add(dto);
			}
		}
		return  list1;
	}


	public Boolean getremoveUpload(CaseMonHistoryDTO caseDTO) {
		Boolean flag=false;
		flag=caseDAO.getremoveUpload(caseDTO);
		return  flag;
	}
	
}// close BD Class
