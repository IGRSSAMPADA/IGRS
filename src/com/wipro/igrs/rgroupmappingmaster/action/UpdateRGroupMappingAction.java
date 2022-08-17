package com.wipro.igrs.rgroupmappingmaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;

import com.wipro.igrs.rgroupmappingmaster.bd.RGroupmappingBD;
import com.wipro.igrs.rgroupmappingmaster.dto.RGroupmappingDTO;
import com.wipro.igrs.rgroupmappingmaster.exception.RGroupMappingAlreadyExistsException;
import com.wipro.igrs.rgroupmappingmaster.form.RGroupmappingForm;



public class UpdateRGroupMappingAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		RGroupmappingForm rGroupmappingForm = (RGroupmappingForm) form;
		
		ActionErrors errors=new ActionErrors();
		
		RGroupmappingDTO dto = new RGroupmappingDTO();
		RGroupmappingBD rGroupmappingBD = new RGroupmappingBD();
		
		
		/* update RGroupMapping master */
		
		dto.setRolegroupId(rGroupmappingForm.getRolegroupId());
		dto.setRoleId(rGroupmappingForm.getRoleId());
		//dto.setId(session.getAttribute("id").toString());
		 //System.out.println("##updated id data##"+dto.getId());
		
		RGroupmappingDTO oldDTO=new RGroupmappingDTO();
		//oldDTO.setRolegroupId(rGroupmappingForm.getOldGroup());
		//oldDTO.setRoleId(rGroupmappingForm.getOldRole());
		
	/*	try
		{
			
			//rGroupmappingBD.updateRGroupMapping(dto,oldDTO);
		}
		catch(RGroupMappingAlreadyExistsException e)
		{
			errors.add("mappingAlreadyExist",new ActionError("error.mappingAlreadyExist"));
			saveErrors(request, errors);
			 return mapping.findForward("updatergroupmappingmaster");
		}*/
		//System.out.println("add2");
		
		return mapping.findForward("successupdate");
	}

	
}
