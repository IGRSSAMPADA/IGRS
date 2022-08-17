package com.wipro.igrs.spotinspslabmaster.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.spotinspslabmaster.actionforms.InspectionActionForm;
import com.wipro.igrs.spotinspslabmaster.bd.InspectionBD;
import com.wipro.igrs.spotinspslabmaster.dto.InspectionDTO;

public class ApproveSpotInspectionAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		/* get the inspection form bean*/
		InspectionActionForm insForm=(InspectionActionForm)form;
		/* get parameter from the request*/
		String id=(String)request.getParameter("slabID");
        String language=(String)session.getAttribute("languageLocale");
		session.setAttribute("id", id);
		InspectionBD insBD=new InspectionBD();
		InspectionDTO insDTO=insBD.getInspectionById(id,language);
		
		/* setting the FormBean  from the retrieving DTO*/
		insForm.setSlabMaxRange(insDTO.getSlabMaxRange());
		insForm.setSlabMinRange(insDTO.getSlabMinRange());
		insForm.setCreatedBy(insDTO.getCreatedBy());
		insForm.setStatus(insDTO.getStatus());
		insForm.setCreatedDate(insDTO.getCreatedDate());
		insForm.setPercentage(insDTO.getPercentage());
		
		if("A".equalsIgnoreCase(insDTO.getStatus()))
		{
			insForm.setStatusdesc("Pending for approval");
		}
		//insForm.setMinSpotInsp(insDTO.getMinSpotInsp());
		insForm.setTimeFrom(insDTO.getTimeFrom());
		insForm.setTimeTo(insDTO.getTimeTo());
		insForm.setRemarks(insDTO.getRemarks());
		return mapping.findForward("approveinspectionmaster");
	}

}

