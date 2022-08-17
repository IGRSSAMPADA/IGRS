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


package com.wipro.igrs.auditinspection.action;


import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.auditinspection.bd.PoiTxnBD;
import com.wipro.igrs.auditinspection.dto.PoiTxnDTO;
import com.wipro.igrs.auditinspection.form.PoiTxnForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class PoiTxnAction extends BaseAction{


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		PoiTxnForm txnForm=(PoiTxnForm)form ;
		PoiTxnBD poiBD=new PoiTxnBD();
		PoiTxnDTO poiDTO;
		
		//added by shruti
		String language=(String)session.getAttribute("languageLocale");
		txnForm.setLanguage(language);
		//end

			String txnID=txnForm.getTxnID();
			
			poiDTO=poiBD.getTxnDtlsByID(txnID);
			
			if(poiDTO!=null)
			{
				txnForm.setTxnID(poiDTO.getTxnID());
				txnForm.setPoName(poiDTO.getPoName());
				txnForm.setPoAddress(poiDTO.getPoAddress());
				txnForm.setCreatedBy(poiDTO.getCreatedBy());
				txnForm.setCreatedDate(poiDTO.getCreatedDate());
				txnForm.setUpdatedBy(poiDTO.getUpdatedBy());
				txnForm.setUpdatedDate(poiDTO.getUpdatedDate());
				txnForm.setDispatchDate(poiDTO.getDispatchDate());
				txnForm.setInspFromDate(poiDTO.getInspFromDate());
				txnForm.setInspToDate(poiDTO.getInspToDate());
				
				txnForm.setInspDate(poiDTO.getInspDate());
				txnForm.setDispatchNo(poiDTO.getDispatchNo());
				
				txnForm.setSearched("searched");
				
				return mapping.findForward("poiPage");
			}
			else
			{
				ActionErrors errors=new ActionErrors();
				ActionError error = new ActionError("noresult");
				errors.add("noresult",error);
				saveErrors(request, errors);
				
				txnForm.setSearched(null);
				
				return mapping.findForward("poiPage");
			}
			
	}
}		
