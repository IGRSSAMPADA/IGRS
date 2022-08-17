package com.wipro.igrs.spotinspslabmaster.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.spotinspslabmaster.actionforms.InspectionActionForm;
import com.wipro.igrs.spotinspslabmaster.bd.InspectionBD;
import com.wipro.igrs.spotinspslabmaster.dto.InspectionDTO;
import com.wipro.igrs.baseaction.action.BaseAction;
public class FinalApproveInspection extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		/* get the inspection form bean */
		
		InspectionActionForm insForm = (InspectionActionForm) form;
		String slabID = (String) session.getAttribute("id");

		InspectionBD insBD = new InspectionBD();
		InspectionDTO insDTO = new InspectionDTO();
		insForm.setSuccMsg("");
		/* setting the DTO from the FormBean */
		insDTO.setSlabID(slabID);
		insDTO.setSlabMaxRange(insForm.getSlabMaxRange());
		insDTO.setSlabMinRange(insForm.getSlabMinRange());
		insDTO.setCreatedBy(insForm.getCreatedBy());
		insDTO.setModifiedBy((String) session.getAttribute(
				"UserId"));
		insDTO.setCreatedDate(insForm.getCreatedDate());
		insDTO.setPercentage(insForm.getPercentage());
		insDTO.setStatus(insForm.getStatus());
		insDTO.setMinSpotInsp(insForm.getMinSpotInsp());
		insDTO.setTimeFrom(insForm.getTimeFrom());
		insDTO.setTimeTo(insForm.getTimeTo());
		insDTO.setRemarks(insForm.getRemarks());
		insDTO.setApprovalRemarks(insForm.getApprovalRemarks());
		insDTO.setApprovalStatus(insForm.getApprovalStatus());
		/* invoking Business Delegate Method that updates one Inspection */
		String retMsg=insBD.approveINspection(insDTO);
		if("succ".equalsIgnoreCase(retMsg))
		{
			if("S".equalsIgnoreCase(insDTO.getApprovalStatus()))
			{
		
				insForm.setSuccMsg("Approved by DR.");
			}
			else
			{
				insForm.setSuccMsg("Rejected by DR.");
			}
		}
		return mapping.findForward("finalapprovesuccess");
	}

}