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

public class UpdateTxnAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		
		PoiTxnForm txnForm=(PoiTxnForm)form ;
		PoiTxnBD poiBD=new PoiTxnBD();
		PoiTxnDTO poiDTO=new PoiTxnDTO();
		
		
		String inspDate=txnForm.getInspDate();
					
		String [] date=inspDate.split("/");

		
		int monthInt=Integer.parseInt(date[1]);

		switch (monthInt)
		{
			case 1:
				date[1]="JAN";
			break;
			
			case 2:
				date[1]="FEB";
			break;
			
			case 3:
				date[1]="MAR";
			break;
			
			case 4:
				date[1]="APR";
			break;
			
			case 5:
				date[1]="May";
			break;
			
			case 6:
				date[1]="JUN";
			break;
			
			case 7:
				date[1]="JUL";
			break;
			
			case 8:
				date[1]="aug";
			break;
			
			case 9:
				date[1]="SEP";
			break;
			
			case 10:
				date[1]="OCT";
			break;
			
			case 11:
				date[1]="NOV";
			break;
			
			case 12:
				date[1]="DEC";
			break;

		}
		
		

		poiDTO.setTxnID(txnForm.getTxnID());
		poiDTO.setInspDate(date[0]+"-"+date[1]+"-"+date[2]);
		
		
		poiDTO.setDispatchNo(txnForm.getDispatchNo());
		
		txnForm.setSearched(null);
		txnForm.setTxnID("");
		if(poiBD.updateTxnDtls(poiDTO))
		{
			return mapping.findForward("successpage");
		}
		else
		{
			ActionErrors errors=new ActionErrors();
			ActionError error = new ActionError("notupdated");
			errors.add("notupdated",error);
			saveErrors(request, errors);
			return mapping.findForward("poiPage");
		}
		
	}
}

