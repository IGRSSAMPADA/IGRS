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
import com.wipro.igrs.groupmaster.dto.GroupDTO;

import com.wipro.igrs.rgroupmappingmaster.bd.RGroupmappingBD;
import com.wipro.igrs.rgroupmappingmaster.dto.RGroupmappingDTO;
import com.wipro.igrs.rgroupmappingmaster.dto.RoleDTO;
import com.wipro.igrs.rgroupmappingmaster.exception.RGroupMappingAlreadyExistsException;
import com.wipro.igrs.rgroupmappingmaster.form.RGroupmappingForm;



public class PreCreateRGroupMappingAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		RGroupmappingBD bd=new RGroupmappingBD();
		RGroupmappingForm rGroupmappingForm = (RGroupmappingForm) form;
		
		ArrayList roleDtos = bd.getRoleList();
		ArrayList groupDtos =bd.getRolegroupList();
		//System.out.println("##data##"+((RoleDTO)roleDtos.get(0)).getRoleName());
	//	rGroupmappingForm.setRoleDTOs(roleDtos);
		//rGroupmappingForm.setGroupDTOs(groupDtos);
		//System.out.println("##data 2##"+((RoleDTO)rGroupmappingForm.getRoleDTOs().get(0)).getRoleName());
		
		
		//System.out.println("##data##"+((RoleDTO)roleDtos.get(0)).getRoleName());
		
		return mapping.findForward("creatergroupmappingmaster");
	}

	
}
