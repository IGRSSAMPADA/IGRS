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



public class PreUpdateRGroupMappingAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		RGroupmappingBD bd=new RGroupmappingBD();
		RGroupmappingDTO dto=new RGroupmappingDTO();
		RGroupmappingForm rGroupmappingForm = (RGroupmappingForm) form;
		
		
		ArrayList roleDtos = bd.getRoleList();
		ArrayList groupDtos =bd.getRolegroupList();
		String mappingId=request.getParameter("id");
		//dto=bd.getMapping(mappingId);
		//System.out.println("map id :"+mappingId);
		//System.out.println(dto.getRolegroupId()+" # "+dto.getRoleId());
		rGroupmappingForm.setRolegroupId(dto.getRolegroupId());
		rGroupmappingForm.setRoleId(dto.getRoleId());
		//rGroupmappingForm.setRoleDTOs(roleDtos);
		//rGroupmappingForm.setGroupDTOs(groupDtos);
		session.setAttribute("id", mappingId);
		
		//rGroupmappingForm.setOldRole(dto.getRoleId());
		//rGroupmappingForm.setOldGroup(dto.getRolegroupId());
		
		//System.out.println("##data##"+((RoleDTO)roleDtos.get(0)).getRoleName());
		
		return mapping.findForward("updatergroupmappingmaster");
	}

	
}
