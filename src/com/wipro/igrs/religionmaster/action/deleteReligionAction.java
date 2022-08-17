package com.wipro.igrs.religionmaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.religionmaster.bd.ReligionBD;
import com.wipro.igrs.religionmaster.dto.ReligionDTO;
import com.wipro.igrs.religionmaster.form.ReligionForm;

public class deleteReligionAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		ReligionDTO dto = new ReligionDTO();
		ReligionBD religionbd = new ReligionBD();
		ArrayList religionList = religionbd.getReligionList();
		ReligionForm religionForm = (ReligionForm) form;
		//session = request.getSession();
		String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		/* Update religion master */
		
		String [] selected=religionForm.getSelected();
		for(int i=0;i<selected.length;i++)
		{
			String religionId=selected[i];
			
			dto=religionbd.getReligionId(religionId);
			
			dto.setReligionStatus("R");
			
			religionbd.updateReligionMaster(dto,roleId,funId,userId);
		}
		
		
		
		
		
		return mapping.findForward("religionAction");
		
	}

}
