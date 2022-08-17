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



public class DeleteRGroupMappingAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		RGroupmappingBD bd=new RGroupmappingBD();
		RGroupmappingForm rGroupmappingForm = (RGroupmappingForm) form;
		String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");

		/*String [] selected=rGroupmappingForm.getSelected();
		for(int i=0;i<selected.length;i++)
		{
			String mappingId=selected[i];

			bd.deleteRGroupMapping(mappingId);
		}
		*/
		

		
		return mapping.findForward("listRGroupmappingAction");
	}

	
}
