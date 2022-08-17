/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech,Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   AddAction.java
 * Author      :   Yousry Ibrahem 
 * Description :   Represents the Adding Action for Adding new Inspection.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Yousry Ibrahem  12th August, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.spotinspslabmaster.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.spotinspslabmaster.actionforms.InspectionActionForm;
import com.wipro.igrs.spotinspslabmaster.bd.InspectionBD;
import com.wipro.igrs.spotinspslabmaster.dao.InspectionDAO;
import com.wipro.igrs.spotinspslabmaster.dto.InspectionDTO;

public class AddAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		/* get the inspection form bean */
		InspectionActionForm insForm = (InspectionActionForm) form;
		insForm.setSlabId("");
		InspectionBD insBD = new InspectionBD();
		InspectionDTO insDTO = new InspectionDTO();
		String pageTitle=request.getParameter("pageTitle");
		String forwardPage="";
		String language = (String)session.getAttribute("languageLocale");
		insForm.setLanguage(language);
		if(pageTitle!=null&&(pageTitle.equalsIgnoreCase("create")||pageTitle.equalsIgnoreCase("")))
    	{
			insDTO.setSlabMinRange("");
			insDTO.setSlabMaxRange("");
			insDTO.setPercentage("");
			insDTO.setTimeFrom("");
			insDTO.setTimeTo("");
			insDTO.setRemarks("");	
			insForm.setTimeFrom("");
			insForm.setTimeTo("");
			insForm.setRemarks("");
			request.setAttribute("status",null);
			forwardPage="createinspectionmaster";
			
    	}
		/* setting the DTO from the FormBean */
		else{
		InspectionDAO insDAO=new InspectionDAO();
		ArrayList maxSlab = insDAO.getMaxSalbRange();
		ArrayList maxSlabRange = (ArrayList) maxSlab.get(0);
		String strMxslabRange = (String) maxSlabRange.get(0);
		
		String frmMaxSlab=insForm.getSlabMaxRange();
		String frmMinSlab=insForm.getSlabMinRange();
		int dbMaxSlab=Integer.parseInt(strMxslabRange);
		int inputMaximumSlab=Integer.parseInt(frmMaxSlab);
		int inputMinimumSlab=Integer.parseInt(frmMinSlab);
		
/*	if(inputMinimumSlab<=dbMaxSlab)			
		{
			request.setAttribute("status", "This range is already defined for details please refer to view page.");
			forwardPage="createinspectionmaster";
		}
		else if(inputMinimumSlab>dbMaxSlab+1)
		{
			
				request.setAttribute("status", "Minimum slab range should be greater than previously created Maximum slab range.");
				forwardPage="createinspectionmaster";
		}*/
		if(inputMinimumSlab==inputMaximumSlab || inputMinimumSlab>inputMaximumSlab)
		{
			
				request.setAttribute("status", "Maximum Slab range should be greater than Minimum slab range.");
				forwardPage="createinspectionmaster";
		}
			else
			{		
				ArrayList slab = insDAO.getSlabTxnId();
				ArrayList slabTxn = (ArrayList) slab.get(0);
				String slabId = (String) slabTxn.get(0);
				if(slabId!=null)
				{
					insForm.setSlabId(slabId);
					
				}
				//insDAO.addInspection(insDTO);
				insDTO.setSlabID(slabId); // this ID will be concatenated with
											// generated numbers from DB sequence
				insDTO.setSlabMaxRange(insForm.getSlabMaxRange());
				insDTO.setSlabMinRange(insForm.getSlabMinRange());
				//insDTO.setMinSpotInsp(insForm.getMinSpotInsp());
				insDTO.setCreatedBy((String) session
						.getAttribute("UserId"));
				insDTO.setModifiedBy((String) session.getAttribute(
						"UserId"));
				insDTO.setStatus("A");

				//invoking Business Delegate Method that Adds new Inspection 
				insBD.add(insDTO);
				forwardPage="successcreate";
				
			}
		}
		//return mapping.findForward("successcreate");
		return mapping.findForward(forwardPage);
	}
		
	

}